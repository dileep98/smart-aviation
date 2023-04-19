package com.sv.smartaviation.model.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@lombok.Data
public class SavedFlight {

    private String id;
    private String originName;

    private String originCode;

    private String destinationName;

    private String destinationCode;

    private Integer stopCount;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime departureDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime arrivalDateTime;

    private String carrierName;

    private Double price;

    private Long userId;
}
