package com.example.demo.private_endpoint.modules.cage_module.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.private_endpoint.modules.cage_module.models.AsigAnimalCageMovement;

public interface AsigAnimalCageMovementRepository extends JpaRepository<AsigAnimalCageMovement, Long> {
    @Query("SELECT asCM FROM AsigAnimalCageMovement asCM WHERE asCM.animal_movement.user.company.id = :id_asig")
    List<AsigAnimalCageMovement> findReportCageMovementByCompanyId(@Param("id_asig") long id_asig);

}
