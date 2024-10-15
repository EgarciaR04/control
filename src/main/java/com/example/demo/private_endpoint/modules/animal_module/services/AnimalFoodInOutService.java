package com.example.demo.private_endpoint.modules.animal_module.services;

import com.example.demo.private_endpoint.modules.animal_module.models.AnimalFoodInOut;
import com.example.demo.private_endpoint.modules.animal_module.models.options.AnimalFoodInOutOptions;
import com.example.demo.private_endpoint.modules.cage_module.models.AsigAnimalFoodCageMovement;
import com.example.demo.private_endpoint.modules.cage_module.models.Cage;
import com.example.demo.private_endpoint.modules.cage_module.repositories.AsigAnimalCageMovementRepository;
import com.example.demo.private_endpoint.modules.cage_module.repositories.AsignAnimalFoodCageMovementRepository;
import com.example.demo.private_endpoint.modules.cage_module.repositories.CageRepository;
import com.example.demo.private_endpoint.modules.cage_module.services.CageService;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.AsignedRepository;
import com.example.demo.private_endpoint.views.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnimalFoodInOutService {
    private final CageService cageService;

    private final CageRepository cageRepository;
    private final AsignedRepository asignedRepository;
    private final AsignAnimalFoodCageMovementRepository asignAnimalFoodCageMovementRepository;

    public Message addAnimalFoodInCage(long id_cage, long id_asig, float amount) {
        Cage cage_used = cageRepository.findById(id_cage).get();
        AnimalFoodInOut animalfood = new AnimalFoodInOut();
        AsigAnimalFoodCageMovement asig_cage_movement = new AsigAnimalFoodCageMovement();
        UserAsigned user = asignedRepository.findAsignedById(id_asig);

        animalfood.setDateTime(LocalDateTime.now());
        animalfood.setType(AnimalFoodInOutOptions.INGRESO);
        animalfood.setAmount(amount);
        animalfood.setUser(user);

        asig_cage_movement.setAnimalfood_movement(animalfood);
        asig_cage_movement.setCage(cage_used);

        asignAnimalFoodCageMovementRepository.save(asig_cage_movement);


        return this.cageService.addAnimalFood(id_cage, amount);
    }

    public Message removeAnimalFoodInCage(long id_cage, long id_asig, float amount){
        Cage cage_used = cageRepository.findById(id_cage).get();
        AnimalFoodInOut animalfood = new AnimalFoodInOut();
        AsigAnimalFoodCageMovement asig_cage_movement = new AsigAnimalFoodCageMovement();
        UserAsigned user = asignedRepository.findAsignedById(id_asig);

        animalfood.setDateTime(LocalDateTime.now());
        animalfood.setType(AnimalFoodInOutOptions.USO);
        animalfood.setAmount(amount);
        animalfood.setUser(user);

        asig_cage_movement.setCage(cage_used);
        asig_cage_movement.setAnimalfood_movement(animalfood);

        asignAnimalFoodCageMovementRepository.save(asig_cage_movement);


        return this.cageService.removeAnimalFood(id_cage, amount);
    }
}
