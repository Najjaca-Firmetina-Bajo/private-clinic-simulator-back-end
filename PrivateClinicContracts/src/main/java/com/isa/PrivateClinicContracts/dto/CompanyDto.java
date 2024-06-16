package com.isa.PrivateClinicContracts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    @Schema(description = "Company ID")
    private long id;
    @Schema(description = "Company name")
    private String name;
    @Schema(description = "Company address")
    private String address;
    @Schema(description = "Company equipments")
    private List<EquipmentDto> equipments;
}
