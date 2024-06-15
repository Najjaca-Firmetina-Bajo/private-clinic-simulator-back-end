package com.isa.PrivateClinicContracts.controller;

import com.isa.PrivateClinicContracts.dto.CompanyDto;
import com.isa.PrivateClinicContracts.model.Company;
import com.isa.PrivateClinicContracts.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("")
    public ResponseEntity<List<CompanyDto>> getAll() {
        return ResponseEntity.ok(companyService.getAll());
    }
}
