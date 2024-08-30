package com.example.demo.private_endpoint.modules.animal_module.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.example.demo.private_endpoint.inputs.AnimalDiedData;
import com.example.demo.private_endpoint.modules.animal_module.models.AnimalInOut;
import com.example.demo.private_endpoint.modules.animal_module.repositories.AnimalInOutRepository;
import com.example.demo.private_endpoint.modules.animal_module.repositories.AnimalRespository;
import com.example.demo.private_endpoint.modules.cage_module.repositories.CageRepository;
import com.example.demo.private_endpoint.modules.cage_module.repositories.FeedAnimalRepository;
import com.example.demo.private_endpoint.modules.cage_module.services.CageService;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.AsignedRepository;
import com.example.demo.private_endpoint.views.AnimalInOutView;
import com.example.demo.private_endpoint.views.Message;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalInOutService {

    private final AnimalInOutRepository animalInOutRepository;
    private final AsignedRepository asR;

    // usados para el registro de la muerte
    private final CageRepository cageR;
    private final FeedAnimalRepository fAnimalR;
    private final AnimalRespository aR;

    public Message animalDiedRegister(AnimalDiedData animal_input, long id_cage, long id_user) {

        AnimalInOut animal = new AnimalInOut();

        // datos dados por el usuario
        animal.setAge(animal_input.getAge());
        animal.setWeight(animal_input.getWeight());

        // datos asignados automaticamente
        animal.setMovement_date(LocalDateTime.now());

        UserAsigned user = asR.findAsignedById(id_user);
        animal.setUser(user);

        animalInOutRepository.save(animal);

        // dar de baja al animal en el corral correspondiente
        CageService cage_animal_died = new CageService(cageR, asR, aR, fAnimalR);

        Message message = cage_animal_died.animalDied(id_cage);

        return message;
    }

    public List<AnimalInOutView> reportAnimalInOut(long asig) {
        List<AnimalInOut> report_animals = animalInOutRepository.findReportAnimalInOut(asig);

        List<AnimalInOutView> report_animals_view = new ArrayList<>();

        for (int i = 0; i < report_animals.size(); i++) {
            AnimalInOut report = report_animals.get(i);

            AnimalInOutView report_view = new AnimalInOutView();

            report_view.setAge(report.getAge());
            report_view.setDate(report.getMovement_date());
            report_view.setUser(report.getUser().getId());
            report_view.setWeight(report.getWeight());

            report_animals_view.add(report_view);
        }

        return report_animals_view;
    }

}
