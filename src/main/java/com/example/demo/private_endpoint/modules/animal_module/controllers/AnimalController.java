package com.example.demo.private_endpoint.modules.animal_module.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.private_endpoint.modules.animal_module.models.Animal;
import com.example.demo.private_endpoint.modules.animal_module.services.AnimalService;
import com.example.demo.private_endpoint.views.AnimalView;
import com.example.demo.private_endpoint.views.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService as;

    @PostMapping(path = "/create")
    public Message saveAnimal(@RequestBody Animal request) {
        return this.as.saveAnimal(request);
    }

    @GetMapping(path = "{id}/select")
    public AnimalView findAnimalById(@PathVariable long id) {
        return this.as.findAnimalById(id);
    }

    @GetMapping(path = "{id}/asig")
    public List<AnimalView> findAnimalsInCompany(@PathVariable long id) {
        return this.as.findAnimalsInCompany(id);
    }

    @PutMapping(path = "{id}/update")
    public AnimalView updateAnimal(@RequestBody Animal request, @PathVariable long id) {
        return this.as.updateAnimal(request, id);
    }
}
