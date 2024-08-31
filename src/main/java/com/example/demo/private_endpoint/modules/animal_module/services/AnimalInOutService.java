package com.example.demo.private_endpoint.modules.animal_module.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.example.demo.private_endpoint.inputs.AnimalMovementData;
import com.example.demo.private_endpoint.modules.animal_module.models.AnimalInOut;
import com.example.demo.private_endpoint.modules.animal_module.models.options.AnimalInOutOptions;
import com.example.demo.private_endpoint.modules.animal_module.repositories.AnimalInOutRepository;
import com.example.demo.private_endpoint.modules.animal_module.repositories.AnimalRespository;
import com.example.demo.private_endpoint.modules.cage_module.models.AsigCageMovement;
import com.example.demo.private_endpoint.modules.cage_module.models.Cage;
import com.example.demo.private_endpoint.modules.cage_module.repositories.AsigCageMovementRepository;
import com.example.demo.private_endpoint.modules.cage_module.repositories.CageRepository;
import com.example.demo.private_endpoint.modules.cage_module.repositories.FeedAnimalRepository;
import com.example.demo.private_endpoint.modules.cage_module.repositories.FeedConcentrateRepository;
import com.example.demo.private_endpoint.modules.cage_module.services.CageService;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.AsignedRepository;
import com.example.demo.private_endpoint.views.AnimalInOutView;
import com.example.demo.private_endpoint.views.CageMovementView;
import com.example.demo.private_endpoint.views.Message;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalInOutService {

    private final AnimalInOutRepository animalInOutRepository;
    private final AsignedRepository asR;
    private final CageRepository cR;
    private final AsigCageMovementRepository asCMR;

    // usados para el registro de la muerte
    private final CageRepository cageR;
    private final FeedAnimalRepository fAnimalR;
    private final AnimalRespository aR;
    private final FeedConcentrateRepository fcR;

    // dar de baja por muerte
    public Message animalDiedRegister(AnimalMovementData animal_input, long id_cage, long id_user) {

        Cage cage_used = cR.findById(id_cage).get();
        AnimalInOut animal = new AnimalInOut();
        AsigCageMovement asig_cage_movement = new AsigCageMovement();

        // datos dados por el usuario
        animal.setAge(animal_input.getAge());
        animal.setWeight(animal_input.getWeight());

        // datos asignados automaticamente
        animal.setMovement_date(LocalDateTime.now());
        animal.setOption(AnimalInOutOptions.Muerte);

        UserAsigned user = asR.findAsignedById(id_user);
        animal.setUser(user);

        asig_cage_movement.setAnimal_movement(animal);
        asig_cage_movement.setCage(cage_used);

        asCMR.save(asig_cage_movement);

        // dar de baja al animal en el corral correspondiente
        CageService cage_animal_died = new CageService(cageR, asR, aR, fAnimalR, fcR);

        Message message = cage_animal_died.animalDied(id_cage);

        return message;
    }

    // dar de baja por salida
    public Message removeAnAnimalRegister(AnimalMovementData animal_input, long id_cage, long id_user) {
        AnimalInOut animal_data = new AnimalInOut();
        Cage cage_used = cR.findById(id_cage).get();
        AsigCageMovement asig_cage_movement = new AsigCageMovement();

        animal_data.setAge(animal_input.getAge());
        animal_data.setWeight(animal_input.getWeight());

        // asignados automaticamente
        animal_data.setMovement_date(LocalDateTime.now());
        animal_data.setOption(AnimalInOutOptions.Salida);
        animal_data.setUser(asR.findAsignedById(id_user));

        asig_cage_movement.setAnimal_movement(animal_data);
        asig_cage_movement.setCage(cage_used);

        asCMR.save(asig_cage_movement);

        CageService cage_animal_remove = new CageService(cageR, asR, aR, fAnimalR, fcR);

        Message message = cage_animal_remove.removeAnimal(id_cage);

        return message;
    }

    // Dar de baja por salida con todos los animales
    public Message removeAllAnimalRegister(List<AnimalMovementData> animal_input, long id_cage, long id_user) {

        Cage cage_used = cR.findById(id_cage).get();

        for (int i = 0; i < animal_input.size(); i++) {
            AnimalInOut animal_movement = new AnimalInOut();
            AsigCageMovement asig_cage_movement = new AsigCageMovement();

            AnimalMovementData animal_movement_input = animal_input.get(i);

            animal_movement.setAge(animal_movement_input.getAge());
            animal_movement.setWeight(animal_movement_input.getWeight());

            animal_movement.setUser(asR.findAsignedById(id_user));
            animal_movement.setOption(AnimalInOutOptions.Salida);
            animal_movement.setMovement_date(LocalDateTime.now());

            asig_cage_movement.setAnimal_movement(animal_movement);
            asig_cage_movement.setCage(cage_used);

            asCMR.save(asig_cage_movement);
        }

        CageService cage_animals_remove = new CageService(cageR, asR, aR, fAnimalR, fcR);

        cage_animals_remove.removeAllAnimal(id_cage);

        Message message = new Message();
        message.setMessage("Se han sacado a todos los animales del corral");

        return message;
    }

    public List<CageMovementView> reportAnimalInOut(long asig) {
        UserAsigned user = asR.findAsignedById(asig);

        List<AsigCageMovement> report_animals = asCMR.findReportCageMovementByCompanyId(
                user.getCompany().getId());

        List<CageMovementView> report_animals_view = new ArrayList<>();

        for (int i = 0; i < report_animals.size(); i++) {
            AsigCageMovement report = report_animals.get(i);

            CageMovementView report_view = new CageMovementView();

            report_view.setCage_code(report.getCage().getCode());
            report_view.setMovement_option(report.getAnimal_movement().getOption());
            report_view.setTime(report.getAnimal_movement().getMovement_date());
            report_view.setUser_username(report.getAnimal_movement().getUser().getUser().getUsername());

            report_animals_view.add(report_view);
        }

        return report_animals_view;
    }

}
