package com.sv.smartaviation.model;

import java.time.OffsetDateTime;
import java.util.UUID;

@lombok.Data
public class Key {
    private long id;
    private UUID apiKey;
    private String type;
    private Object expired;
    private OffsetDateTime registered;
    private long limitsByHour;
    private long limitsByMinute;
    private long limitsByMonth;
    private long limitsTotal;
}
