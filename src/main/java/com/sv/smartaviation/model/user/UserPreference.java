package com.sv.smartaviation.model.user;

import javax.validation.constraints.NotNull;

@lombok.Data
public class UserPreference {

    private Long id;

    private boolean smsToggle = false;

    private boolean emailToggle = false;

    @NotNull
    private Long flightId;

    @NotNull
    private Long userId;
}
