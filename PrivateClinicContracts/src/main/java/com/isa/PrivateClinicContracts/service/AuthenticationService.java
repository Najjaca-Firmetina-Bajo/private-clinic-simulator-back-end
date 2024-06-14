package com.isa.PrivateClinicContracts.service;

import com.isa.PrivateClinicContracts.dto.CredentialsDto;
import com.isa.PrivateClinicContracts.dto.JWTTokenDto;

public interface AuthenticationService {

    JWTTokenDto authenticate(CredentialsDto request);
}
