package com.sv.smartaviation.model.skyscanner;

import java.time.LocalDateTime;

@lombok.Data
public class Leg {
    private String id;
    private LegDestination origin;
    private LegDestination destination;
    private long durationInMinutes;
    private long stopCount;
    private boolean isSmallestStops;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private long timeDeltaInDays;
    private Carriers carriers;
    private Segment[] segments;
}
