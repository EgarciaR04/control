package com.example.demo.private_endpoint.modules.animal_module.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.private_endpoint.inputs.AnimalDiedData;
import com.example.demo.private_endpoint.modules.animal_module.services.AnimalInOutService;
import com.example.demo.private_endpoint.views.AnimalInOutView;
import com.example.demo.private_endpoint.views.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/report/cage")
@RequiredArgsConstructor
public class AnimalInOutController {

    private final AnimalInOutService animalInOutService;

    @PostMapping(path = "/register/{id_asig}/user/{id_cage}/cage")
    public Message animalDied(@RequestBody AnimalDiedData animal_data, @PathVariable long id_asig,
            @PathVariable long id_cage) {
        return this.animalInOutService.animalDiedRegister(animal_data, id_cage, id_asig);
    }

    @GetMapping(path = "/search/{id_asig}/company")
    public List<AnimalInOutView> reportInOutAnimal(@PathVariable long id_asig) {
        return this.animalInOutService.reportAnimalInOut(id_asig);
    }

}
