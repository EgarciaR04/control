package com.example.demo.private_endpoint.modules.companymodule.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.private_endpoint.modules.companymodule.Models.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT c FROM Company c WHERE c.id = :input")
    Company findCompanyCustom(@Param("input") long input);

    @Query("SELECT ua.company FROM UserAsigned ua WHERE ua.id = :asig")
    Optional<Company> findCompanyByUserAsigned(@Param("asig") long asig);

}
