package com.sv.smartaviation.model.skyscanner;

@lombok.Data
public class PricingOption {
    private Agent[] agents;
    private Price price;
    private String url;
}
