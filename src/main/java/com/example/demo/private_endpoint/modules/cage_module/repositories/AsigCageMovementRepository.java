package com.example.demo.private_endpoint.modules.cage_module.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.private_endpoint.modules.cage_module.models.AsigCageMovement;

public interface AsigCageMovementRepository extends JpaRepository<AsigCageMovement, Long> {
    @Query("SELECT asCM FROM AsigCageMovement asCM WHERE asCM.animal_movement.user.company.id = :id_asig")
    List<AsigCageMovement> findReportCageMovementByCompanyId(@Param("id_asig") long id_asig);

}
