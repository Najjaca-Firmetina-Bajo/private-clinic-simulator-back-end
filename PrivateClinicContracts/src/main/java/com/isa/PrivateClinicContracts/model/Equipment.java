package com.isa.PrivateClinicContracts.model;

import com.isa.PrivateClinicContracts.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "equipment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double price;
    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CompanyEquipment> companyEquipmentList;
}
