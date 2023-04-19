package com.sv.smartaviation.model.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

@lombok.Data
public class Leg {
    private String id;
    private LegDestination origin;
    private LegDestination destination;
    private long durationInMinutes;
    private long stopCount;
    @JsonProperty("isSmallestStops")
    private boolean isSmallestStops;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private long timeDeltaInDays;
    private Carriers carriers;
    private List<Segment> segments;
}
