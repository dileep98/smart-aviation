package com.sv.smartaviation.model.skyscanner;


@lombok.Data
public class Agent {
    private String id;
    private String name;
    private boolean isCarrier;
    private String updateStatus;
    private boolean optimisedForMobile;
    private boolean liveUpdateAllowed;
    private String ratingStatus;
    private double rating;
    private long feedbackCount;
    private RatingBreakdown ratingBreakdown;
}
