package com.example.demo.private_endpoint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActUser {
    private String username;
    private String firstname;
    private String lastname;
    private String tel;
    private boolean hability;
    private boolean changePassword;
    private boolean changePasswordNextSession;

    public boolean getHability() {
        return this.hability;
    }
    public boolean getChangePassword() {
        return this.changePassword;
    }
    public boolean getChangePasswordNextSession() {
        return this.changePasswordNextSession;
    }

}
