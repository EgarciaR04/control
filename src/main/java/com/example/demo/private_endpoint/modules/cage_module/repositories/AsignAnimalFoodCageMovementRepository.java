package com.example.demo.private_endpoint.modules.cage_module.repositories;

import com.example.demo.private_endpoint.modules.cage_module.models.AsigAnimalCageMovement;
import com.example.demo.private_endpoint.modules.cage_module.models.AsigAnimalFoodCageMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignAnimalFoodCageMovementRepository extends JpaRepository<AsigAnimalFoodCageMovement, Long> {
    @Query("SELECT asFCM FROM AsigAnimalFoodCageMovement asFCM WHERE asFCM.animalfood_movement.user.company.id = :id_company")
    List<AsigAnimalFoodCageMovement> findReportsAnimalsFoodCageMovementByCompanyId(@Param("id_company") long id_company);
}
