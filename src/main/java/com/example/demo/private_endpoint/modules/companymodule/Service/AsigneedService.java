package com.example.demo.private_endpoint.modules.companymodule.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.User.User_;
import com.example.demo.auth.AuthService;
import com.example.demo.private_endpoint.Message;
import com.example.demo.private_endpoint.modules.companymodule.Models.Company;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.AsignedRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsigneedService {

    private final AsignedRepository asignedRepository;
    private final AuthService auth;
    private final CompanyService cs;

    public List<User_> findUserByCompanyId(long id) {
        return asignedRepository.findUserByCompanyId(id);
    }

    public UserAsigned updateAsigned(UserAsigned request, long id) {
        UserAsigned asigned = asignedRepository.findById(id).get();

        asigned.setUser(request.getUser());
        asigned.setCompany(request.getCompany());

        asignedRepository.save(asigned);

        return asigned;
    }

    public Message saveAsigned(UserAsigned request) {
        User_ user = request.getUser();

        request.setUser(auth.resgiterUserByUser(user));

        Company company = cs.findById(request.getCompany().getId());

        request.setCompany(company);

        System.out.println(request);

        Message message = new Message();

        message.setMessage("Usuario creado correctamente");

        asignedRepository.save(request);

        return message;
    }

}
