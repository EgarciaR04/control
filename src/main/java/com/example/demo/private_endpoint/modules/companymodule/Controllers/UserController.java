package com.example.demo.private_endpoint.modules.companymodule.Controllers;

import com.example.demo.private_endpoint.DTOs.ActUser;
import com.example.demo.private_endpoint.DTOs.ActUserPassword;
import com.example.demo.private_endpoint.views.Message;
import org.springframework.web.bind.annotation.*;

// import java.util.List;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.private_endpoint.modules.companymodule.Service.UserService;
import com.example.demo.private_endpoint.views.UserView;

import lombok.RequiredArgsConstructor;

// import com.auth.demo_jwt.User.User_;
// import com.auth.demo_jwt.private_endpoint.Service.UserService;

// import lombok.RequiredArgsConstructor;
@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "{id}/select")
    public UserView getUserById(@PathVariable long id) {
        return this.userService.getUserById(id);
    }

    @PutMapping(path = "{id}/update")
    public Message updateUserById(@RequestBody ActUser request, @PathVariable long id) {
        return this.userService.updateUserById(request, id);
    }

    @PutMapping(path = "{id}/update/password")
    public Message updateUserPassword(@RequestBody ActUserPassword request, @PathVariable long id){
        return this.userService.updatePassword(request, id);
    }
}
