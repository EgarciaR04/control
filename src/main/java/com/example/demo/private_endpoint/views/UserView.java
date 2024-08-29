package com.example.demo.private_endpoint.views;

import com.example.demo.User.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserView {
    private long id;
    private String username;
    private String firstname;
    private String lastname;
    private String country;
    private String tel;
    private Role role;
    private boolean hability;

}
