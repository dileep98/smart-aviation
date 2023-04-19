package com.sv.smartaviation.model.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class TingCarrier {
    private long id;
    private String name;
    @JsonProperty("alternate_di")
    private String alternateDi;
    private Long allianceId;
}
