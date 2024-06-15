package com.isa.PrivateClinicContracts.service.impl;

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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public void create(ContractDto contractDto) {
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

        contractRepository.save(newContract);
    }
}
