package com.example.demo.private_endpoint.modules.companymodule.Controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.private_endpoint.modules.companymodule.Models.Company;
import com.example.demo.private_endpoint.modules.companymodule.Service.CompanyService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping(path = "{id}/select")
    public Company getCompanyBiId(@PathVariable long id) {
        return this.companyService.getCompanyById(id);
    }

    @PostMapping(path = "/create")
    public Company saveCompany(@RequestBody Company request) {
        return this.companyService.saveCompany(request);
    }

    @PutMapping(path = "{id}/update")
    public Company updateCompanyById(@RequestBody Company request, @PathVariable long id) {
        return this.companyService.updateCompanyById(request, id);
    }

}
