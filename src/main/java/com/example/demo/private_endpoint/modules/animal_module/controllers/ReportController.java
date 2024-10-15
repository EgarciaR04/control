package com.example.demo.private_endpoint.modules.animal_module.controllers;

import com.example.demo.private_endpoint.modules.animal_module.services.ReportService;
import com.example.demo.private_endpoint.views.MovementView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/report/list")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping(path = "/{id_asig}/search")
    public List<MovementView> getMovementInCageList(@PathVariable long id_asig) {
        return  reportService.getAllReports(id_asig);
    }

}
