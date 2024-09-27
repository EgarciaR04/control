package com.example.demo.private_endpoint.modules.animal_module.controllers;

import java.util.List;

import com.example.demo.private_endpoint.DTOs.AnimalDTO;
import org.springframework.web.bind.annotation.*;

import com.example.demo.private_endpoint.modules.animal_module.models.Animal;
import com.example.demo.private_endpoint.modules.animal_module.services.AnimalService;
import com.example.demo.private_endpoint.views.AnimalView;
import com.example.demo.private_endpoint.views.Message;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService as;

    @PostMapping(path = "/create/{id_user}")
    public Message saveAnimal(@RequestBody AnimalDTO request, @PathVariable long id_user) {
        return this.as.saveAnimal(request, id_user);
    }

    @GetMapping(path = "{id}/select")
    public AnimalView findAnimalById(@PathVariable long id) {
        return this.as.findAnimalById(id);
    }

    @GetMapping(path = "{id}/asig")
    public List<AnimalView> findAnimalsInCompany(@PathVariable long id) {
        return this.as.findAnimalsInCompany(id);
    }

    @PutMapping(path = "{animal_id}/update")
    public Message updateAnimal(@RequestBody AnimalDTO request, @PathVariable long animal_id) {
        return this.as.updateAnimal(request, animal_id);
    }
}
