package com.example.demo.private_endpoint.modules.cage_module.controllers;

import java.util.List;

import com.example.demo.private_endpoint.DTOs.PutCage;
import org.springframework.web.bind.annotation.*;

import com.example.demo.private_endpoint.DTOs.AsignCageAnimalData;
import com.example.demo.private_endpoint.DTOs.CageInput;
import com.example.demo.private_endpoint.modules.cage_module.services.CageService;
import com.example.demo.private_endpoint.views.CageView;
import com.example.demo.private_endpoint.views.Message;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequestMapping("/cage")
@RestController
@RequiredArgsConstructor
public class CageController {

    private final CageService cageService;

    @PostMapping(path = "/create")
    public Message saveCage(@RequestBody CageInput request) {
        return this.cageService.saveCage(request);
    }

    @GetMapping(path = "{id}/asig")
    public List<CageView> findCagesInCompany(@PathVariable long id) {
        return this.cageService.findAllCageInCompany(id);
    }

    @GetMapping(path = "{id}/select")
    public CageView findCageById(@PathVariable long id) {
        return this.cageService.findCageById(id);
    }

    @PutMapping(path = "{id}/update")
    public Message updateCage(@PathVariable long id, @RequestBody PutCage request) {
        return this.cageService.updateCageById(request, id);
    }

    @PatchMapping(path = "{id}/animal/set")
    public Message setAnimal(@RequestBody AsignCageAnimalData animal, @PathVariable long id) {
        return this.cageService.asignCageAnimal(animal, id);
    }

    @PatchMapping(path = "{id_cage}/cage/{id_concentrate}/concentrate/asig/{amount}")
    public Message setConcentrate(@PathVariable long id_cage, @PathVariable long id_concentrate, @PathVariable long amount) {
        return this.cageService.asigConcentrate(id_concentrate, id_cage, amount);
    }
}
