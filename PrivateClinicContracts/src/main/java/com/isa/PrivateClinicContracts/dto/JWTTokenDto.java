package com.isa.PrivateClinicContracts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTTokenDto {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty
    private Long id;
}