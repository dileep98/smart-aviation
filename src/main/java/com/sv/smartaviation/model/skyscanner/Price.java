package com.sv.smartaviation.model.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@lombok.Data
public class Price {
    private double amount;
    @JsonProperty("update_status")
    private String updateStatus;
    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
    @JsonProperty("quote_age")
    private long quoteAge;
}
