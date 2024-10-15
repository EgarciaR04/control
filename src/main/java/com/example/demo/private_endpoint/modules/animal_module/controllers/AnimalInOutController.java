package com.example.demo.private_endpoint.modules.animal_module.controllers;

import java.util.List;

import com.example.demo.private_endpoint.modules.cage_module.repositories.CageRepository;
import org.springframework.web.bind.annotation.*;

import com.example.demo.private_endpoint.DTOs.AnimalMovementData;
import com.example.demo.private_endpoint.modules.animal_module.services.AnimalInOutService;
import com.example.demo.private_endpoint.views.CageAnimalMovementView;
import com.example.demo.private_endpoint.views.Message;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/report/cage")
@RequiredArgsConstructor
public class AnimalInOutController {

    private final AnimalInOutService animalInOutService;
    private final CageRepository cageRepository;

//    @PostMapping(path = "/register/die/{id_asig}/user/{id_cage}/cage")
//    public Message animalDied(@RequestBody AnimalMovementData animal_data, @PathVariable long id_asig,
//            @PathVariable long id_cage) {
//        return this.animalInOutService.animalDiedRegister(animal_data, id_cage, id_asig);
//    }

    @PostMapping(path = "/register/remove/{id_asig}/user/{id_cage}/cage/{type_of_movement}")
    public Message removeAnimal(@RequestBody AnimalMovementData animal_input, @PathVariable long id_asig,
            @PathVariable long id_cage, @PathVariable long type_of_movement) {
        if (cageRepository.findById(id_cage).get().getFeedAnimal().getAnimal_amount() <= 0) {
            return new Message("No puedes eliminar mas animales");
        }

        if (type_of_movement == 1) {
            return this.animalInOutService.removeAnAnimalRegister(animal_input, id_cage, id_asig);
        }
        else if (type_of_movement == 2) {
            return this.animalInOutService.animalDiedRegister(animal_input, id_cage, id_asig);
        }
        Message message = new Message("");
        message.setMessage("Movimiento no identificado");
        return message;
    }

    @PostMapping(path = "/set/animals/{id_asig}/user/{id_cage}/cage/{amount}/amount")
    public Message addAnimals(@PathVariable long id_asig, @PathVariable long id_cage, @PathVariable int amount){
        return this.animalInOutService.setAnAnimalInCage(id_cage, id_asig, amount);
    }
}
