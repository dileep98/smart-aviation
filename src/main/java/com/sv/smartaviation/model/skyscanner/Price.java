package com.sv.smartaviation.model.skyscanner;

import java.time.LocalDateTime;

@lombok.Data
public class Price {
    private double amount;
    private String updateStatus;
    private LocalDateTime lastUpdated;
    private long quoteAge;
}
