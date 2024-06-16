package com.isa.PrivateClinicContracts.repository;

import com.isa.PrivateClinicContracts.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
