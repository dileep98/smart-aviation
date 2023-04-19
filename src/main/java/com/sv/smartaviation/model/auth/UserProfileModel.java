package com.sv.smartaviation.model.auth;

import javax.validation.constraints.Size;

@lombok.Data
public class UserProfileModel {
    @Size(max = 15)
    private String phoneNumber;

    private Boolean smsToggle = false;

    private Boolean emailToggle = false;
}
