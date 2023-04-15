package com.sv.smartaviation.model.user;

@lombok.Data
public class UserPreference {

    private String name;

    private String username;

    private String email;

    private String phoneNumber;

    private String password;

    private String confirmPassword;

    private boolean smsToggle = true;

    private boolean emailToggle = true;

    private Long userId;
}
