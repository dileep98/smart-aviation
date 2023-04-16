package com.sv.smartaviation.model.user;

import javax.validation.constraints.NotNull;

@lombok.Data
public class UserFlightPreference {

    private Long id;

    private boolean smsToggle = false;

    private boolean emailToggle = false;

    @NotNull
    private String flightId;

    @NotNull
    private Long userId;
}