package com.example.demo.private_endpoint.modules.cage_module.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.private_endpoint.modules.cage_module.models.Cage;

public interface CageRepository extends JpaRepository<Cage, Long> {
    @Query("SELECT c FROM Cage c WHERE c.user.company.id = :id_asig")
    List<Cage> findAllCagesInCompany(@Param("id_asig") long id_asig);
}
