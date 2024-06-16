package com.isa.PrivateClinicContracts.model;

import com.isa.PrivateClinicContracts.baseentity.BaseEntity;
import com.isa.PrivateClinicContracts.model.enumeration.ContractStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contrancts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Contract extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "equipment_id", nullable = false)
    private long equipmentId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "pickup_date", nullable = false)
    private LocalDateTime pickupDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractStatus status;
}
