package com.isa.PrivateClinicContracts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.PrivateClinicContracts.dto.ContractDto;
import com.isa.PrivateClinicContracts.model.Contract;

public interface ContractService {
    void create(ContractDto contractDto) throws JsonProcessingException;
}
