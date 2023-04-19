package com.sv.smartaviation.model.skyscanner;

import java.util.UUID;

@lombok.Data
public class Context {
    private String status;
    private UUID sessionId;
}
