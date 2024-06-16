package com.isa.PrivateClinicContracts.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.PrivateClinicContracts.config.RabbitMQConfig;
import com.isa.PrivateClinicContracts.dto.ContractDto;
import com.isa.PrivateClinicContracts.model.Company;
import com.isa.PrivateClinicContracts.model.Contract;
import com.isa.PrivateClinicContracts.model.User;
import com.isa.PrivateClinicContracts.model.enumeration.ContractStatus;
import com.isa.PrivateClinicContracts.repository.CompanyRepository;
import com.isa.PrivateClinicContracts.repository.ContractRepository;
import com.isa.PrivateClinicContracts.repository.UserRepository;
import com.isa.PrivateClinicContracts.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final RabbitTemplate rabbitTemplate;
    private final ContractRepository contractRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private final CacheManager cacheManager;

    @Override
    @CachePut(value = "contractDto", key = "#contractDto.userId")
    public void create(ContractDto contractDto) throws JsonProcessingException {
        Company company = companyRepository.findById(contractDto.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));
        User user = userRepository.findById(contractDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Contract newContract = Contract.builder()
                .user(user)
                .company(company)
                .equipmentId(contractDto.getEquipmentId())
                .quantity(contractDto.getQuantity())
                .pickupDate(contractDto.getPickupDate())
                .status(ContractStatus.VALID)
                .build();

        Optional<Contract> existingContractOptional = contractRepository.findByUserIdAndCompanyIdAndStatus(
                user.getId(),
                company.getId(),
                ContractStatus.VALID
        );

        if (existingContractOptional.isPresent()) {
            Contract existingContract = existingContractOptional.get();
            existingContract.setStatus(ContractStatus.INVALID);
            contractRepository.save(existingContract);
        }

        Contract savedContract = contractRepository.save(newContract);

        String contractDtoJson = objectMapper.writeValueAsString(contractDto);
        addToCache(savedContract.getId(), contractDtoJson);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, contractDtoJson);
    }


    private void addToCache(Long id, String savedContract) {
        cacheManager.getCache("contractDto").put(id, savedContract);
    }

    @RabbitListener(queues = RabbitMQConfig.DELIVER_QUEUE_NAME)
    public void deliver(long id) {
        Contract contract = contractRepository.findById(id).orElse(null);
        if (contract == null) {
            return;
        }

        if (contract.getStatus().equals(ContractStatus.VALID)) {
            contract.setStatus(ContractStatus.DELIVERED);
            contractRepository.save(contract);
        }
    }

    @RabbitListener(queues = RabbitMQConfig.EXPIRE_QUEUE_NAME)
    public void expire(long id) {
        Contract contract = contractRepository.findById(id).orElse(null);
        if (contract == null) {
            return;
        }

        if (contract.getStatus().equals(ContractStatus.VALID)) {
            contract.setStatus(ContractStatus.EXPIRED);
            contractRepository.save(contract);
        }
    }

    @RabbitListener(queues = RabbitMQConfig.CANCEL_QUEUE_NAME)
    public void cancel(long id) {
        Contract contract = contractRepository.findById(id).orElse(null);
        if (contract == null) {
            return;
        }

        if (contract.getStatus().equals(ContractStatus.VALID)) {
            contract.setStatus(ContractStatus.CANCELLED);
            contractRepository.save(contract);
        }
    }


}
