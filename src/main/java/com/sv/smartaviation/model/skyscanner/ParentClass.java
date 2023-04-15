package com.sv.smartaviation.model.skyscanner;

@lombok.Data
public class ParentClass {
    private String flightPlaceID;
    private ParentClass parent;
    private String name;
    private String type;
}
