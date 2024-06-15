package com.isa.PrivateClinicContracts.model.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

public enum ContractStatus {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    CREATED,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    DELIVERED,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    EXPIRED,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    REJECTED
}
