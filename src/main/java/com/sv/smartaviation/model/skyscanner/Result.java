package com.sv.smartaviation.model.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
public class Result {
    private String id;
    private List<Leg> legs;
    @JsonProperty("pricing_options")
    private List<PricingOption> pricingOptions;
    private String deeplink;
}
