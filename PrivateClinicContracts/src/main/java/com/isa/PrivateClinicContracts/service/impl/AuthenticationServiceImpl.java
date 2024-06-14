package com.isa.PrivateClinicContracts.service.impl;

import com.isa.PrivateClinicContracts.model.User;
import com.isa.PrivateClinicContracts.repository.UserRepository;
import com.isa.PrivateClinicContracts.dto.CredentialsDto;
import com.isa.PrivateClinicContracts.dto.JWTTokenDto;
import com.isa.PrivateClinicContracts.security.authentication.JwtService;
import com.isa.PrivateClinicContracts.security.token.JwtToken;
import com.isa.PrivateClinicContracts.security.token.JwtTokenRepository;
import com.isa.PrivateClinicContracts.security.token.TokenType;
import com.isa.PrivateClinicContracts.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenRepository jwtTokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JWTTokenDto authenticate(CredentialsDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var jwtToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return JWTTokenDto.builder()
                .accessToken(jwtToken)
                .id(user.getId())
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = JwtToken.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        jwtTokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = jwtTokenRepository.findAllValidTokenByUser(Math.toIntExact(user.getId()));
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        jwtTokenRepository.saveAll(validUserTokens);
    }
}