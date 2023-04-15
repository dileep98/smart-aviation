package com.sv.smartaviation.model.skyscanner;

@lombok.Data
public class Result {
    private String id;
    private Leg[] legs;
    private PricingOption[] pricingOptions;
    private String deeplink;
}
