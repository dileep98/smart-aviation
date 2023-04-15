package com.sv.smartaviation.model.auth;

import javax.validation.constraints.NotBlank;

@lombok.Data
public class LoginRequest {
    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
}
