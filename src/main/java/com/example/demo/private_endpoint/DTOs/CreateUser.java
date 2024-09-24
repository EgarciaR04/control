package com.example.demo.private_endpoint.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUser {
    @NotNull
    private long id_asig;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    private String tel;
    private boolean hability;
    private boolean changePassword;
    private boolean changePasswordNexSession;

    public boolean getHability() {
        return this.hability;
    }
    public boolean getChangePassword() {
        return this.changePassword;
    }
    public boolean getChangePasswordNextSession() {
        return this.changePasswordNexSession;
    }

}
