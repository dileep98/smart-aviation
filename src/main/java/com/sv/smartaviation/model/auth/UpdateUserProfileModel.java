package com.sv.smartaviation.model.auth;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@lombok.Data
public class UpdateUserProfileModel {

    @NotNull
    private long id;

    @Size(max = 15)
    private String phoneNumber;

    private Boolean smsToggle = false;

    private Boolean emailToggle = false;
}
