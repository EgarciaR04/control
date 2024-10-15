package com.example.demo.private_endpoint.modules.animal_module.controllers;

import com.example.demo.private_endpoint.modules.animal_module.services.AnimalFoodInOutService;
import com.example.demo.private_endpoint.views.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/report/food/cage/register")
@RequiredArgsConstructor
public class AnimalFoodInOutController {
    private final AnimalFoodInOutService animalFoodInOutService;

    @PostMapping(path = "/add/{id_asig}/user/{id_cage}/cage/{amount}")
    public Message addConcentrateInCage(@PathVariable long id_asig, @PathVariable long id_cage, @PathVariable float amount) {
        return this.animalFoodInOutService.addAnimalFoodInCage(id_cage,id_asig,amount);
    }

    @PostMapping(path = "/remove/{id_asig}/user/{id_cage}/cage/{amount}")
    public Message deleteAnimalFoodInCage(@PathVariable long id_asig, @PathVariable long id_cage, @PathVariable float amount) {
        return this.animalFoodInOutService.removeAnimalFoodInCage(id_cage, id_asig, amount);
    }
}
