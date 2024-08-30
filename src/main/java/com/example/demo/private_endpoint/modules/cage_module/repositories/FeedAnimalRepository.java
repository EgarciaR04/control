package com.example.demo.private_endpoint.modules.cage_module.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.private_endpoint.modules.cage_module.models.FeedAnimal;

public interface FeedAnimalRepository extends JpaRepository<FeedAnimal, Long> {

}
