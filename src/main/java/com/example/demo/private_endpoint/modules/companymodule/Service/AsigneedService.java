package com.example.demo.private_endpoint.modules.companymodule.Service;

import java.util.List;
import java.util.ArrayList;

import com.example.demo.User.Role;
import com.example.demo.private_endpoint.DTOs.CreateUser;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import com.example.demo.User.User_;
import com.example.demo.auth.AuthService;
import com.example.demo.private_endpoint.modules.companymodule.Models.Company;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.AsignedRepository;
import com.example.demo.private_endpoint.views.Message;
import com.example.demo.private_endpoint.views.UserView;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsigneedService {

    private final AsignedRepository asignedRepository;
    private final CompanyRepository companyRepository;
    private final AuthService auth;
    private final CompanyService cs;

    public List<UserView> findUserByCompanyId(long id) {
        Company company = companyRepository.findCompanyByUserAsigned(id).get();

        List<UserAsigned> users = asignedRepository.findUserByCompanyId(company.getId());

        List<UserView> users_view = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            UserAsigned u = users.get(i);

            UserView uv = new UserView();
            uv.setId(u.getId());
            uv.setUsername(u.getUser().getUsername());
            uv.setFirstname(u.getUser().getFirstname());
            uv.setLastname(u.getUser().getLastname());
            uv.setTel(u.getUser().getTel());
            uv.setHability(u.getUser().getHability());
            uv.setRole(u.getUser().getRole());
            uv.setChangePassword(u.getUser().getChangePassword());
            uv.setChangePasswordNextSession(u.getUser().getChangePasswordNextSession());


            users_view.add(uv);
        }

        return users_view;
    }

    public Message saveAsigned(CreateUser request) {
        Company company = companyRepository.findCompanyByUserAsigned(request.getId_asig()).get();

        User_ user = new User_();
        UserAsigned userAsigned = new UserAsigned();

        user.setUsername(request.getUsername() + "@" +company.getUsernameExtension());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setTel(request.getTel());
        user.setHability(request.getHability());
        user.setChangePassword(request.getChangePassword());
        user.setChangePasswordNextSession(request.getChangePasswordNextSession());
        if(request.getRole() == 0){
            user.setRole(Role.USER);
        }
        else {
            user.setRole(Role.ADMIN);
        }

        System.out.println(request);


        userAsigned.setCompany(company);
        userAsigned.setUser(auth.resgiterUserByUser(user));


        Message message = new Message();

        message.setMessage("Usuario creado correctamente");

        asignedRepository.save(userAsigned);

        return message;
    }

}
