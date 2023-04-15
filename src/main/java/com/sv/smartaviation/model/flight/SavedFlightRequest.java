package com.sv.smartaviation.model.flight;

import java.time.LocalDateTime;

@lombok.Data
public class SavedFlightRequest {

    private String originName;

    private String originCode;

    private String destinationName;

    private String destinationCode;

    private Integer stopCount;

    private LocalDateTime departureDateTime;

    private LocalDateTime arrivalDateTime;

    private String carrierName;

    private Long price;

    private Long userId;
}
