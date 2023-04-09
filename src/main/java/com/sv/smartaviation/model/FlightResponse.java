package com.sv.smartaviation.model;

@lombok.Data
public class FlightResponse {
    private String hex;
    private String regNumber;
    private String flag;
    private double lat;
    private double lng;
    private long alt;
    private long dir;
    private long speed;
    private Double vSpeed;
    private String squawk;
    private String flightNumber;
    private String flightIcao;
    private String flightIata;
    private String depIcao;
    private String depIata;
    private String arrIcao;
    private String arrIata;
    private String airlineIcao;
    private String airlineIata;
    private String aircraftIcao;
    private long updated;
    private Status status;
}
