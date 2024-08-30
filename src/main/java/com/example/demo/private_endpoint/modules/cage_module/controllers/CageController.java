package com.example.demo.private_endpoint.modules.cage_module.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.private_endpoint.inputs.AsignCageAnimalData;
import com.example.demo.private_endpoint.modules.cage_module.models.Cage;
import com.example.demo.private_endpoint.modules.cage_module.services.CageService;
import com.example.demo.private_endpoint.views.CageView;
import com.example.demo.private_endpoint.views.Message;

import lombok.RequiredArgsConstructor;

@RequestMapping("/cage")
@RestController
@RequiredArgsConstructor
public class CageController {

    private final CageService cageService;

    @PostMapping(path = "/create")
    public Message saveCage(@RequestBody Cage request) {
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
    public CageView updateCage(@PathVariable long id, @RequestBody Cage request) {
        return this.cageService.updateCageById(request, id);
    }

    @PatchMapping(path = "{id}/animal/set")
    public Message setAnimal(@RequestBody AsignCageAnimalData animal, @PathVariable long id) {
        return this.cageService.asignCageAnimal(animal, id);
    }
}
