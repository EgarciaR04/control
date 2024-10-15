package com.example.demo.private_endpoint.modules.animal_module.controllers;

import java.util.List;

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

//    @PostMapping(path = "/register/die/{id_asig}/user/{id_cage}/cage")
//    public Message animalDied(@RequestBody AnimalMovementData animal_data, @PathVariable long id_asig,
//            @PathVariable long id_cage) {
//        return this.animalInOutService.animalDiedRegister(animal_data, id_cage, id_asig);
//    }

    @PostMapping(path = "/register/remove/{id_asig}/user/{id_cage}/cage/{type_of_movement}")
    public Message removeAnimal(@RequestBody AnimalMovementData animal_input, @PathVariable long id_asig,
            @PathVariable long id_cage, @PathVariable long type_of_movement) {
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

    @PostMapping(path = "register/remove/all/{id_asig}/user/{id_cage}")
    public Message removeAllAnimals(@RequestBody List<AnimalMovementData> animal_input, @PathVariable long id_asig,
            @PathVariable long id_cage) {
        return this.animalInOutService.removeAllAnimalRegister(animal_input, id_cage, id_asig);
    }
}
