package com.sv.smartaviation.model.skyscanner;

import java.time.LocalDateTime;

@lombok.Data
public class Segment {
    private String id;
    private ParentClass origin;
    private ParentClass destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private long durationInMinutes;
    private String flightNumber;
    private TingCarrier marketingCarrier;
    private TingCarrier operatingCarrier;
}
