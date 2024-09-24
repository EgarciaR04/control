package com.example.demo.private_endpoint.modules.companymodule.Controllers;

import java.util.List;

import com.example.demo.private_endpoint.DTOs.CreateUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.private_endpoint.modules.companymodule.Service.AsigneedService;
import com.example.demo.private_endpoint.views.Message;
import com.example.demo.private_endpoint.views.UserView;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/asigned")
@RequiredArgsConstructor
public class AsignedController {

    private final AsigneedService asigneedService;

    @GetMapping(path = "/users/{id}/company")
    public List<UserView> findUsers(@PathVariable long id) {
        List<UserView> user = this.asigneedService.findUserByCompanyId(id);
        return user;
    }

    @PostMapping(path = "/create")
    public Message saveAsigned(@Validated @RequestBody CreateUser request) {
        return this.asigneedService.saveAsigned(request);
    }
}
