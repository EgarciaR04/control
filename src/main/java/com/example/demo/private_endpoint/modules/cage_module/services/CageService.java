package com.example.demo.private_endpoint.modules.cage_module.services;

import java.util.List;
import java.util.ArrayList;

import com.example.demo.private_endpoint.DTOs.*;
import com.example.demo.private_endpoint.modules.animal_module.models.Concentrate;
import com.example.demo.private_endpoint.modules.animal_module.repositories.ConcentrateRepository;
import org.springframework.stereotype.Service;

import com.example.demo.private_endpoint.modules.animal_module.models.Animal;
import com.example.demo.private_endpoint.modules.animal_module.repositories.AnimalRespository;
import com.example.demo.private_endpoint.modules.cage_module.models.Cage;
import com.example.demo.private_endpoint.modules.cage_module.models.FeedAnimal;
import com.example.demo.private_endpoint.modules.cage_module.models.FeedConcentrate;
import com.example.demo.private_endpoint.modules.cage_module.repositories.CageRepository;
import com.example.demo.private_endpoint.modules.cage_module.repositories.FeedAnimalRepository;
import com.example.demo.private_endpoint.modules.cage_module.repositories.FeedConcentrateRepository;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.AsignedRepository;
import com.example.demo.private_endpoint.views.CageView;
import com.example.demo.private_endpoint.views.Message;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CageService {

    private final CageRepository cageRepository;
    private final AsignedRepository asR;
    private final AnimalRespository aR;
    private final FeedAnimalRepository faR;
    private final FeedConcentrateRepository fcR;
    private final ConcentrateRepository cr;

    public CageView saveCage(CageInput request) {
        Cage cage = new Cage();

        FeedAnimal feedAnimal = new FeedAnimal();
        feedAnimal.setAnimal_amount(0);
        FeedConcentrate feedConcentrate = new FeedConcentrate();
        feedConcentrate.setAmount(0);

        cage.setActive(request.getActive());
        cage.setAnimal(null);
        cage.setFeedAnimal(feedAnimal);
        cage.setFeedConcentrate(feedConcentrate);
        cage.setName(request.getName());
        cage.setCode(request.getCode());
        cage.setObservations(request.getObservations());
        cage.setUser(asR.findAsignedById(request.getUser()));

        cageRepository.save(cage);

        CageView cv = new CageView();

        AnimalAsigned animalAsigned = new AnimalAsigned();
        ConcentrateAsigned concentrateAsigned = new ConcentrateAsigned();

        cv.setId(cage.getId());
        cv.setUser(cage.getUser().getId());
        cv.setCode(cage.getCode());
        cv.setName(cage.getName());
        cv.setActive(cage.getActive());
        cv.setObservations(cage.getObservations());
        cv.setAnimalAsigned(null);
        cv.setConcentrateAsigned(null);

        return cv;
    }

    public List<CageView> findAllCageInCompany(long id_user) {
        UserAsigned user = asR.findAsignedById(id_user);

        List<Cage> cages = cageRepository.findAllCagesInCompany(user.getCompany().getId());

        List<CageView> cage_view = new ArrayList<>();

        for (int i = 0; i < cages.size(); i++) {
            Cage c = cages.get(i);

            CageView cv = new CageView();
            AnimalAsigned animalAsigned = new AnimalAsigned();
            ConcentrateAsigned concentrateAsigned = new ConcentrateAsigned();

            cv.setId(c.getId());
            cv.setUser(c.getUser().getId());
            cv.setCode(c.getCode());
            cv.setName(c.getName());
            cv.setActive(c.getActive());
            cv.setObservations(c.getObservations());

            // establecer animal asignado
            try{
                animalAsigned.setAnimalId(c.getAnimal().getId());
                animalAsigned.setAnimalName(c.getAnimal().getAnimal_name());
                animalAsigned.setAnimalAmount(c.getFeedAnimal().getAnimal_amount());
            }catch (Exception exception){
                animalAsigned.setAnimalId(0);
                animalAsigned.setAnimalName("Animal no asignado");
                animalAsigned.setAnimalAmount(0);
            }

            // establecer concentrado asignado
            try {
                concentrateAsigned.setConcentrateName(c.getConcentrate().getConcentrate_name());
                concentrateAsigned.setConcentrateId(c.getConcentrate().getId());
                concentrateAsigned.setConcentrateAmount(c.getFeedConcentrate().getAmount());
            } catch (Exception e) {
                concentrateAsigned.setConcentrateName("Concentrado no asignado");
                concentrateAsigned.setConcentrateId(0);
                concentrateAsigned.setConcentrateAmount(0);
            }

            // colocar valores asignados
            cv.setConcentrateAsigned(concentrateAsigned);
            cv.setAnimalAsigned(animalAsigned);

            cage_view.add(cv);
        }

        return cage_view;
    }

    public CageView findCageById(long id) {
        Cage cage = cageRepository.findById(id).get();

        CageView cage_view = new CageView();
        AnimalAsigned animalAsigned = new AnimalAsigned();
        ConcentrateAsigned concentrateAsigned = new ConcentrateAsigned();

        cage_view.setId(cage.getId());
        cage_view.setUser(cage.getUser().getId());
        cage_view.setCode(cage.getCode());
        cage_view.setName(cage.getName());
        cage_view.setActive(cage.getActive());
        cage_view.setObservations(cage.getObservations());

        // establecer animal asignado
        try{
            animalAsigned.setAnimalId(cage.getAnimal().getId());
            animalAsigned.setAnimalName(cage.getAnimal().getAnimal_name());
            animalAsigned.setAnimalAmount(cage.getFeedAnimal().getAnimal_amount());
        }catch (Exception exception){
            animalAsigned.setAnimalId(0);
            animalAsigned.setAnimalName("Animal no asignado");
            animalAsigned.setAnimalAmount(0);
        }

        // establecer concentrado asignado
        try {
            concentrateAsigned.setConcentrateName(cage.getConcentrate().getConcentrate_name());
            concentrateAsigned.setConcentrateId(cage.getConcentrate().getId());
            concentrateAsigned.setConcentrateAmount(cage.getFeedConcentrate().getAmount());
        } catch (Exception e) {
            concentrateAsigned.setConcentrateName("Concentrado no asignado");
            concentrateAsigned.setConcentrateId(0);
            concentrateAsigned.setConcentrateAmount(0);
        }

        // colocar valores asignados
        cage_view.setConcentrateAsigned(concentrateAsigned);
        cage_view.setAnimalAsigned(animalAsigned);

        return cage_view;
    }

    public Message updateCageById(PutCage request, long id) {
        Cage cage = cageRepository.findById(id).get();

        cage.setCode(request.getCode());
        cage.setName(request.getName());
        cage.setActive(request.getActive());
        cage.setObservations(request.getObservations());

        cageRepository.save(cage);

        Message message = new Message("");
        message.setMessage("Corral actualizado correctamente");

        return message;
    }
    // ASIG CONCENTRATE IN A CAGE
    public Message asigConcentrate(long id_concentrate, long id_cage, long amount){
        Cage cage = cageRepository.findById(id_cage).get();
        Concentrate concentrate = cr.findById(id_concentrate).get();
        FeedConcentrate feedConcentrate = new FeedConcentrate();

        feedConcentrate.setAmount(amount);

        cage.setConcentrate(concentrate);
        cage.setFeedConcentrate(feedConcentrate);

        Message message = new Message("");
        message.setMessage("Concentrado asignado correctamente");

        cageRepository.save(cage);

        return message;
    }


    // ASIGN AND MOVEMENT OF ANIMAL IN A CAGE
    public Message asignCageAnimal(AsignCageAnimalData animal, long id_cage) {
        Cage cage = cageRepository.findById(id_cage).get();

        Animal a = aR.findAnimal(animal.getAnimal());
        FeedAnimal fa = cage.getFeedAnimal();
        fa.setAnimal_amount(animal.getAnimal_amount());

        cage.setFeedAnimal(fa);
        cage.setAnimal(a);

        cageRepository.save(cage);

        Message message = new Message("");
        message.setMessage("Animal asignado correctamente");

        return message;
    }

    public Message setNewAnimals(long id_cage, int amount_animals) {
        Cage cage = cageRepository.findById(id_cage).get();

        FeedAnimal animal_amount = faR.findById(cage.getFeedAnimal().getId()).get();

        animal_amount.setAnimal_amount(animal_amount.getAnimal_amount() + amount_animals);

        faR.save(animal_amount);

        return new Message("Se han agregado " + amount_animals + " animales");
    }

    public Message animalDied(long id) {
        Cage cage = cageRepository.findById(id).get();

        FeedAnimal animal_amount = faR.findById(cage.getFeedAnimal().getId()).get();

        animal_amount.setAnimal_amount(animal_amount.getAnimal_amount() - 1);

        faR.save(animal_amount);

        Message message = new Message("");

        message.setMessage("Se ha dado de baja a un animal");

        return message;

    }

    public Message removeAnimal(long id_cage) {
        Cage cage = cageRepository.findById(id_cage).get();

        FeedAnimal animal_amount = faR.findById(cage.getFeedAnimal().getId()).get();

        animal_amount.setAnimal_amount(animal_amount.getAnimal_amount() - 1);

        faR.save(animal_amount);

        Message message = new Message("");

        message.setMessage("Se ha registrado la salida del animal");

        return message;
    }

    public void removeAllAnimal(long id_cage) {
        Cage cage = cageRepository.findById(id_cage).get();

        FeedAnimal animal_amount = faR.findById(cage.getFeedAnimal().getId()).get();
        FeedConcentrate concentrate_amount = fcR.findById(cage.getFeedConcentrate().getId()).get();

        animal_amount.setAnimal_amount(0);
        concentrate_amount.setAmount(0);

        fcR.save(concentrate_amount);
        faR.save(animal_amount);
    }
}
