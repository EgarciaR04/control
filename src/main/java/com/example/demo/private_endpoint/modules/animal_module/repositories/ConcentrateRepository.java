package com.example.demo.private_endpoint.modules.animal_module.repositories;

import com.example.demo.private_endpoint.modules.animal_module.models.Concentrate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConcentrateRepository extends JpaRepository<Concentrate, Long> {
    @Query("SELECT c FROM Concentrate c WHERE c.user.company.id = :id_user")
    List<Concentrate> findAllConcentratesInCompany(@Param("id_user") long id_user);
}
