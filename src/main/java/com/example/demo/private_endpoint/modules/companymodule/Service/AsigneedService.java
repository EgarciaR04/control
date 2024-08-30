package com.example.demo.private_endpoint.modules.companymodule.Service;

import java.util.List;
import java.util.ArrayList;

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
    private final AuthService auth;
    private final CompanyService cs;

    public List<UserView> findUserByCompanyId(long id) {
        List<User_> users = asignedRepository.findUserByCompanyId(id);

        List<UserView> users_view = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User_ u = users.get(i);

            UserView uv = new UserView();
            uv.setId(u.getId());
            uv.setUsername(u.getUsername());
            uv.setFirstname(u.getFirstname());
            uv.setLastname(u.getLastname());
            uv.setCountry(u.getCountry());
            uv.setRole(u.getRole());
            uv.setTel(u.getTel());
            uv.setHability(u.getHability());

            users_view.add(uv);
        }

        return users_view;
    }

    public Message saveAsigned(UserAsigned request) {
        User_ user = request.getUser();

        request.setUser(auth.resgiterUserByUser(user));

        Company company = cs.findById(request.getCompany().getId());

        request.setCompany(company);

        Message message = new Message();

        message.setMessage("Usuario creado correctamente");

        asignedRepository.save(request);

        return message;
    }

}
