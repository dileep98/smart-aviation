package com.sv.smartaviation.model;

@lombok.Data
public class FlightRequest {
    private String lang;
    private String currency;
    private long time;
    private String id;
    private String server;
    private String host;
    private long pid;
    private Key key;
    private Params params;
    private long version;
    private String method;
    private Client client;
}
