package com.isa.PrivateClinicContracts.service.impl;

import com.isa.PrivateClinicContracts.dto.CompanyDto;
import com.isa.PrivateClinicContracts.dto.EquipmentDto;
import com.isa.PrivateClinicContracts.model.Company;
import com.isa.PrivateClinicContracts.repository.CompanyRepository;
import com.isa.PrivateClinicContracts.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    @Override
    public List<CompanyDto> getAll() {
        List<Company> companies = companyRepository.findAll();

        List<CompanyDto> companyDtos = companies.stream().map(company -> {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setId(company.getId());
            companyDto.setName(company.getName());
            companyDto.setAddress(company.getAddress());

            List<EquipmentDto> equipmentDtos = company.getCompanyEquipmentList().stream().map(equipment -> {
                EquipmentDto dto = new EquipmentDto();
                dto.setId(equipment.getEquipment().getId());
                dto.setName(equipment.getEquipment().getName());
                dto.setType(equipment.getEquipment().getType());
                dto.setDescription(equipment.getEquipment().getDescription());
                dto.setPrice(equipment.getEquipment().getPrice());
                dto.setQuantity(equipment.getQuantity());
                return dto;
            }).collect(Collectors.toList());

            companyDto.setEquipments(equipmentDtos);
            return companyDto;
        }).collect(Collectors.toList());

        return companyDtos;
    }
}
