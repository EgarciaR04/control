package com.example.demo.private_endpoint.modules.animal_module.services;

import com.example.demo.private_endpoint.DTOs.DTOConcentrate;
import com.example.demo.private_endpoint.modules.animal_module.models.Concentrate;
import com.example.demo.private_endpoint.modules.animal_module.repositories.ConcentrateRepository;
import com.example.demo.private_endpoint.modules.companymodule.Models.Company;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.AsignedRepository;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.CompanyRepository;
import com.example.demo.private_endpoint.views.ConcentrateView;
import com.example.demo.private_endpoint.views.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcentrateService {
    private final ConcentrateRepository concentrateRepository;
    private final CompanyRepository companyRepository;
    private final AsignedRepository asignedRepository;

    public List<ConcentrateView> getAllConcentrateInCompany(long id){
        Company id_company = companyRepository.findCompanyByUserAsigned(id).get();

        List<Concentrate> concentrates = this.concentrateRepository.findAllConcentratesInCompany(id_company.getId());

        List<ConcentrateView> concentratesView = new ArrayList<>();

        for(Concentrate concentrate: concentrates){
            ConcentrateView concentrateView = new ConcentrateView();

            concentrateView.setConcentrate_name(concentrate.getConcentrate_name());
            concentrateView.setId(concentrate.getId());
            concentrateView.setHability(concentrate.isHablity());
            concentrateView.setObservations(concentrate.getObservations());

            concentratesView.add(concentrateView);
        }

        return concentratesView;
    }

    public Message createConcentrate(DTOConcentrate request, long user){
        Concentrate concentrate = new Concentrate();

        concentrate.setConcentrate_name(request.getConcentrate_name());
        concentrate.setHablity(request.isHability());
        concentrate.setUser(asignedRepository.findById(user).get());
        concentrate.setObservations(request.getObservations());

        concentrateRepository.save(concentrate);

        Message message = new Message();
        message.setMessage("Concentrado agregado");

        return message;
    }

    public Message putConcentrate(DTOConcentrate request, long id_concentrate) {
        Concentrate concentrate = concentrateRepository.findById(id_concentrate).get();

        concentrate.setObservations(request.getObservations());
        concentrate.setHablity(request.isHability());
        concentrate.setConcentrate_name(request.getConcentrate_name());

        concentrateRepository.save(concentrate);

        Message message = new Message();
        message.setMessage("Concentrado actualizado");

        return message;
    }
}
