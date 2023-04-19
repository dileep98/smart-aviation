package com.sv.smartaviation.model.skyscanner;

@lombok.Data
public class ParentClass {
    private String flightPlaceId;
    private ParentClass parent;
    private String name;
    private String type;
}
