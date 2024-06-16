package com.isa.PrivateClinicContracts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.PrivateClinicContracts.dto.ContractDto;

public interface ContractService {
    void create(ContractDto contractDto) throws JsonProcessingException;
}
