package com.example.demo.private_endpoint.modules.animal_module.repositories;

import com.example.demo.private_endpoint.modules.animal_module.models.AnimalFoodInOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalFoodInOutRepository extends JpaRepository<AnimalFoodInOut, Long> {
}
