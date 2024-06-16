package com.isa.PrivateClinicContracts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.PrivateClinicContracts.dto.ContractDto;
import com.isa.PrivateClinicContracts.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contracts")
public class ContractController {
    private final ContractService contractService;

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody ContractDto contractDto) throws JsonProcessingException {
        contractService.create(contractDto);
        return ResponseEntity.ok().build();
    }
}
