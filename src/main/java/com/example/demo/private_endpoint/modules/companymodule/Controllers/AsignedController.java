package com.example.demo.private_endpoint.modules.companymodule.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.User.User_;
import com.example.demo.private_endpoint.Message;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import com.example.demo.private_endpoint.modules.companymodule.Service.AsigneedService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/asigned")
@RequiredArgsConstructor
public class AsignedController {

    private final AsigneedService asigneedService;

    @GetMapping(path = "/user/{id}")
    public List<User_> findUsers(@PathVariable long id) {
        List<User_> user = this.asigneedService.findUserByCompanyId(id);
        return user;
    }

    @PostMapping
    public Message saveAsigned(@RequestBody UserAsigned request) {
        return this.asigneedService.saveAsigned(request);
    }

    @PutMapping(path = "{id}")
    public UserAsigned updateAsigned(@RequestBody UserAsigned request, @PathVariable long id) {
        return this.updateAsigned(request, id);
    }
}
