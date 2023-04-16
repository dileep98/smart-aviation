package com.sv.smartaviation.model.user;

import com.sv.smartaviation.model.flight.SavedFlight;

@lombok.Data
public class UserFlightPreferenceResponse {

    private Long id;

    private boolean smsToggle;

    private boolean emailToggle;

    private SavedFlight flight;
}
