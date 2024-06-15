package com.isa.PrivateClinicContracts.baseentity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "mySeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity entity)) return false;
        return getId() == entity.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
