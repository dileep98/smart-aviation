package com.sv.smartaviation.model.user;

import javax.validation.constraints.NotNull;

@lombok.Data
public class UserFlightPreference {
    private boolean enabled = false;

    @NotNull
    private String flightId;
}
