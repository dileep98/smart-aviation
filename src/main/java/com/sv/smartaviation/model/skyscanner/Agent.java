package com.sv.smartaviation.model.skyscanner;


import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Agent {
    private String id;
    private String name;
    @JsonProperty("is_carrier")
    private boolean isCarrier;
    @JsonProperty("update_status")
    private String updateStatus;
    @JsonProperty("optimised_for_mobile")
    private boolean optimisedForMobile;
    @JsonProperty("live_update_allowed")
    private boolean liveUpdateAllowed;
    @JsonProperty("rating_status")
    private String ratingStatus;
    private double rating;
    @JsonProperty("feedback_count")
    private long feedbackCount;
    @JsonProperty("rating_breakdown")
    private RatingBreakdown ratingBreakdown;
}
