package com.sv.smartaviation.model;

import java.util.List;

@lombok.Data
public class Flight {
    private Request request;
    private List<Response> response;
    private String terms;
}
