package com.isa.PrivateClinicContracts.repository;

import com.isa.PrivateClinicContracts.model.Contract;
import com.isa.PrivateClinicContracts.model.enumeration.ContractStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findByUserIdAndCompanyIdAndStatus(Long userId, Long companyId, ContractStatus status);
}
