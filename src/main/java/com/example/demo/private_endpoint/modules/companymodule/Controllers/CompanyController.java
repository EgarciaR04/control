package com.example.demo.private_endpoint.modules.companymodule.Controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.private_endpoint.modules.companymodule.Models.Company;
import com.example.demo.private_endpoint.modules.companymodule.Service.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping(path = "{id}/search")
    public Optional<Company> getCompanyBiId(@PathVariable long id) {
        return this.companyService.getCompanyById(id);
    }

    @PostMapping()
    public Company saveCompany(@RequestBody Company request) {
        return this.companyService.saveCompany(request);
    }

    @PutMapping(path = "{id}")
    public Company updateCompanyById(@RequestBody Company request, @PathVariable long id) {
        return this.companyService.updateCompanyById(request, id);
    }

}
