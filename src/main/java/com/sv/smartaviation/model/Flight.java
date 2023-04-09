package com.sv.smartaviation.model;

import java.util.List;

@lombok.Data
public class Flight {
    private FlightRequest request;
    private List<FlightResponse> response;
    private String terms;
}
