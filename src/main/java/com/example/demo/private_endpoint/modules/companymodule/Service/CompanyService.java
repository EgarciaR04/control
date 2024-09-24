package com.example.demo.private_endpoint.modules.companymodule.Service;

import com.example.demo.private_endpoint.exeptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.private_endpoint.modules.companymodule.Models.Company;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company getCompanyById(long asig_user) {
        return companyRepository
                .findCompanyByUserAsigned(asig_user)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Company saveCompany(Company request) {
        return companyRepository.save(request);
    }

    public Company updateCompanyById(Company request, long id) {
        Company company = companyRepository.findCompanyByUserAsigned(id).get();

        company.setAddress(request.getAddress());
        company.setCompany_name(request.getCompany_name());
        company.setDeparment(request.getDeparment());
        company.setOwner(request.getOwner());
        company.setNit(request.getNit());
        company.setObservations(request.getObservations());
        company.setState(request.getState());
        company.setUsernameExtension(request.getUsernameExtension());
        company.setTel(request.getTel());

        companyRepository.save(company);

        return company;
    }

    public Company findById(long id) {
        return companyRepository.findCompanyCustom(id);
    }
}
