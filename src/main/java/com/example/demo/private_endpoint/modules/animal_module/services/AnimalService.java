package com.example.demo.private_endpoint.modules.animal_module.services;

import java.util.List;
import java.util.ArrayList;

import com.example.demo.private_endpoint.DTOs.AnimalDTO;
import org.springframework.stereotype.Service;

import com.example.demo.private_endpoint.modules.animal_module.models.Animal;
import com.example.demo.private_endpoint.modules.animal_module.repositories.AnimalRespository;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.AsignedRepository;
import com.example.demo.private_endpoint.views.AnimalView;
import com.example.demo.private_endpoint.views.Message;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRespository animalRespository;
    private final AsignedRepository asr;

    public Message saveAnimal(AnimalDTO request, long id_user) {
        UserAsigned user = asr.findAsignedById(id_user);
        Animal animal = new Animal();

        animal.setUser(user);
        animal.setAnimal_name(request.getAnimal_name());
        animal.setHability(request.isHability());
        animal.setObservations(request.getObservations());

        animalRespository.save(animal);

        Message message = new Message("");
        message.setMessage("Animal registrado correctamente");

        return message;
    }

    public AnimalView findAnimalById(long id) {
        Animal animal = animalRespository.findAnimal(id);

        AnimalView animal_view = new AnimalView();
        animal_view.setId(animal.getId());
        animal_view.setId_user(animal.getUser().getId());
        animal_view.setAnimal_name(animal.getAnimal_name());
        animal_view.setObservations(animal.getObservations());
        animal_view.setHability(animal.getHability());

        return animal_view;
    }

    public List<AnimalView> findAnimalsInCompany(long id_user) {
        UserAsigned user = asr.findAsignedById(id_user);

        List<Animal> animals = animalRespository.findAnimalInCompany(user.getCompany().getId());

        List<AnimalView> animals_view = new ArrayList<>();

        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);

            AnimalView av = new AnimalView();
            av.setId(a.getId());
            av.setId_user(a.getUser().getId());
            av.setAnimal_name(a.getAnimal_name());
            av.setObservations(a.getObservations());
            av.setHability(a.getHability());

            animals_view.add(av);
        }

        return animals_view;
    }

    public Message updateAnimal(AnimalDTO request, long id) {
        Animal animal = animalRespository.findById(id).get();

        animal.setAnimal_name(request.getAnimal_name());
        animal.setObservations(request.getObservations());
        animal.setHability(request.isHability());

        animalRespository.save(animal);

        Message message = new Message("");
        message.setMessage("Animal actualizado");

        return message;

    }
}
