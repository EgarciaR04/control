package com.example.demo.private_endpoint.modules.animal_module.controllers;

import com.example.demo.private_endpoint.DTOs.DTOConcentrate;
import com.example.demo.private_endpoint.modules.animal_module.services.ConcentrateService;
import com.example.demo.private_endpoint.views.ConcentrateView;
import com.example.demo.private_endpoint.views.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/concentrate")
@RestController
@RequiredArgsConstructor
public class ConcentrateController {

    private final ConcentrateService concentrateService;

    @GetMapping(path = "/all/company/{id_asig}")
    public List<ConcentrateView> findAllConcentratesInCompany(@PathVariable long id_asig) { return this.concentrateService.getAllConcentrateInCompany(id_asig);}

    @PostMapping(path = "/create/{id_asig}")
    public Message createConcentrate(@RequestBody DTOConcentrate request, @PathVariable long id_asig) {
        return  this.concentrateService.createConcentrate(request, id_asig);
    }

    @PutMapping(path = "/update/{id_concentrate}")
    public Message updateConcentrate(@PathVariable long id_concentrate, @RequestBody DTOConcentrate request) {
        return this.concentrateService.putConcentrate(request, id_concentrate);
    }
}
