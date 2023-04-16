package com.sv.smartaviation.model.skyscanner;

import java.util.List;

@lombok.Data
public class PricingOption {
    private List<Agent> agents;
    private Price price;
    private String url;
}
