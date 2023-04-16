package com.sv.smartaviation.model.skyscanner;

import java.util.List;

@lombok.Data
public class Result {
    private String id;
    private List<Leg> legs;
    private List<PricingOption> pricingOptions;
    private String deeplink;
}
