package com.sv.smartaviation.model;

@lombok.Data
public class Geo {
    private String countryCode;
    private String country;
    private String continent;
    private String city;
    private double lat;
    private double lng;
    private String timezone;
}
