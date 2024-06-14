package com.isa.PrivateClinicContracts.controller;

import com.isa.PrivateClinicContracts.dto.CredentialsDto;
import com.isa.PrivateClinicContracts.dto.JWTTokenDto;
import com.isa.PrivateClinicContracts.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JWTTokenDto> login(@RequestBody CredentialsDto request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
