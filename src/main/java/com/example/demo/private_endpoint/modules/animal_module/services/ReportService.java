package com.example.demo.private_endpoint.modules.animal_module.services;

import com.example.demo.private_endpoint.modules.cage_module.models.AsigCageMovement;
import com.example.demo.private_endpoint.modules.cage_module.repositories.AsigCageMovementRepository;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.AsignedRepository;
import com.example.demo.private_endpoint.views.MovementView;
import com.example.demo.private_endpoint.views.optinos.MovementCageSelection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final AsignedRepository asR;
    private final AsigCageMovementRepository asCMR;

    public List<MovementView> getAllReports(long id_asig){
        UserAsigned user = asR.findAsignedById(id_asig);
        List<MovementView> reports_view = new ArrayList<>();

        List<AsigCageMovement> report_animals = asCMR.findReportCageMovementByCompanyId(
                user.getCompany().getId());

        for (AsigCageMovement report_animal : report_animals){
            MovementView report = new MovementView();

            report.setUser_name(report_animal.getAnimal_movement().getUser().getUser().getFirstname());
            report.setCage_code(report_animal.getCage().getCode());
            report.setCage_name(report_animal.getCage().getName());
            report.setMovement_type(report_animal.getAnimal_movement().getType().toString());
            report.setAnimalOrAnimalFood(MovementCageSelection.ANIMAL);
            report.setAge(report_animal.getAnimal_movement().getAge());
            report.setWeigth(report_animal.getAnimal_movement().getWeight());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            report.setTime(report_animal.getAnimal_movement().getMovement_date().format(formatter));

            reports_view.add(report);
        }


        return reports_view;
    }
}
