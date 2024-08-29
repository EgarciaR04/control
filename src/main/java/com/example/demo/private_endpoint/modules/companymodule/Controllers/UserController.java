package com.example.demo.private_endpoint.modules.companymodule.Controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// import java.util.List;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.User.User_;
import com.example.demo.private_endpoint.modules.companymodule.Service.UserService;

import lombok.RequiredArgsConstructor;

// import com.auth.demo_jwt.User.User_;
// import com.auth.demo_jwt.private_endpoint.Service.UserService;

// import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "{id}/select")
    public Optional<User_> getUserById(@PathVariable long id) {
        return this.userService.getUserById(id);
    }

    @PutMapping(path = "{id}")
    public User_ updateUserById(@RequestBody User_ request, @PathVariable long id) {
        return this.userService.updateUserById(request, id);
    }
}
