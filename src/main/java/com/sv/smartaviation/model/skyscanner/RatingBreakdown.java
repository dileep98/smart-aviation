package com.sv.smartaviation.model.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class RatingBreakdown {
    @JsonProperty("reliable_prices")
    private double reliablePrices;
    @JsonProperty("clear_extra_fees")
    private double clearExtraFees;
    @JsonProperty("customer_service")
    private double customerService;
    @JsonProperty("ease_of_booking")
    private double easeOfBooking;
    private double other;
}
