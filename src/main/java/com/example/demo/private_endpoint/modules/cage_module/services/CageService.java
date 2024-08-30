package com.example.demo.private_endpoint.modules.cage_module.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.private_endpoint.inputs.AsignCageAnimalData;
import com.example.demo.private_endpoint.modules.animal_module.models.Animal;
import com.example.demo.private_endpoint.modules.animal_module.repositories.AnimalRespository;
import com.example.demo.private_endpoint.modules.cage_module.models.Cage;
import com.example.demo.private_endpoint.modules.cage_module.models.FeedAnimal;
import com.example.demo.private_endpoint.modules.cage_module.repositories.CageRepository;
import com.example.demo.private_endpoint.modules.cage_module.repositories.FeedAnimalRepository;
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

    public Message saveCage(Cage request) {
        UserAsigned user = request.getUser();

        request.setUser(asR.findAsignedById(user.getId()));

        cageRepository.save(request);

        Message message = new Message();

        message.setMessage("Corral creado exitosamente");

        return message;
    }

    public List<CageView> findAllCageInCompany(long id_user) {
        List<Cage> cages = cageRepository.findAllCagesInCompany(id_user);

        List<CageView> cage_view = new ArrayList<>();

        for (int i = 0; i < cages.size(); i++) {
            Cage c = cages.get(i);

            CageView cv = new CageView();
            cv.setId(c.getId());
            cv.setUser(c.getUser().getId());
            cv.setCode(c.getCode());
            cv.setName(c.getName());
            cv.setActive(c.getActive());
            cv.setObservations(c.getObservations());
            cv.setFeedconcentrate(c.getFeedConcentrate().getAmount());
            cv.setFeedanimal(c.getFeedAnimal().getAnimal_amount());

            try {
                cv.setAnimal_name(c.getAnimal().getAnimal_name());
            } catch (Exception ex) {
                cv.setAnimal_name("Animal no ingresado");
            }

            cage_view.add(cv);
        }

        return cage_view;
    }

    public CageView findCageById(long id) {
        Cage cage = cageRepository.findById(id).get();

        CageView cage_view = new CageView();
        cage_view.setId(cage.getId());
        cage_view.setUser(cage.getUser().getId());
        cage_view.setCode(cage.getCode());
        cage_view.setName(cage.getName());
        cage_view.setActive(cage.getActive());
        cage_view.setObservations(cage.getObservations());
        cage_view.setFeedconcentrate(cage.getFeedConcentrate().getAmount());
        cage_view.setFeedanimal(cage.getFeedAnimal().getAnimal_amount());

        try {
            cage_view.setAnimal_name(cage.getAnimal().getAnimal_name());
        } catch (Exception ex) {
            cage_view.setAnimal_name("Animal no ingresado");
        }

        return cage_view;
    }

    public CageView updateCageById(Cage request, long id) {
        Cage cage = cageRepository.findById(id).get();

        cage.setCode(request.getCode());
        cage.setName(request.getName());
        cage.setActive(request.getActive());
        cage.setObservations(request.getObservations());

        cageRepository.save(cage);

        CageView cage_view = new CageView();
        cage_view.setId(cage.getId());
        cage_view.setUser(cage.getUser().getId());
        cage_view.setCode(cage.getCode());
        cage_view.setName(cage.getName());
        cage_view.setActive(cage.getActive());
        cage_view.setObservations(cage.getObservations());

        return cage_view;
    }

    public Message asignCageAnimal(AsignCageAnimalData animal, long id_cage) {
        Cage cage = cageRepository.findById(id_cage).get();

        Animal a = aR.findAnimal(animal.getAnimal());

        cage.setAnimal(a);

        cageRepository.save(cage);

        Message message = new Message();
        message.setMessage("Animal asignado correctamente");

        return message;
    }

    public Message animalDied(long id) {
        Cage cage = cageRepository.findById(id).get();

        FeedAnimal animal_amount = faR.findById(cage.getFeedAnimal().getId()).get();

        animal_amount.setAnimal_amount(animal_amount.getAnimal_amount() - 1);

        faR.save(animal_amount);

        Message message = new Message();

        message.setMessage("Se ha dado de baja a un animal");

        return message;

    }

}
