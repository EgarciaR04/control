package com.example.demo.private_endpoint.modules.animal_module.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.private_endpoint.modules.animal_module.models.Animal;

public interface AnimalRespository extends JpaRepository<Animal, Long> {
    @Query("SELECT a FROM Animal a WHERE a.id = :id_animal")
    Animal findAnimal(@Param("id_animal") long id_animal);

    @Query("SELECT a FROM Animal a WHERE a.user.id = :id_user")
    List<Animal> findAnimalInCompany(@Param("id_user") long id_user);

}
