package com.sv.smartaviation.model;

@lombok.Data
public class Client {
    private String ip;
    private Geo geo;
    private Connection connection;
    private Agent device;
    private Agent agent;
    private Karma karma;
}
