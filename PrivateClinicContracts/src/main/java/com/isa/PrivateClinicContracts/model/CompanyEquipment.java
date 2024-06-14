package com.isa.PrivateClinicContracts.model;

import com.isa.PrivateClinicContracts.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "company_equipment",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id", "equipment_id"})})
public class CompanyEquipment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @Column(nullable = false)
    private int quantity;
}
