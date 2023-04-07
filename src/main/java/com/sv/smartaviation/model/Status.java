package com.sv.smartaviation.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.IOException;

public enum Status {
    EN_ROUTE, LANDED, SCHEDULED;

    public String toValue() {
        switch (this) {
            case EN_ROUTE:
                return "en-route";
            case LANDED:
                return "landed";
            case SCHEDULED:
                return "scheduled";
        }
        return null;
    }

    @JsonCreator
    public static Status forValue(String value) throws IOException {
        if (value.equals("en-route")) return EN_ROUTE;
        if (value.equals("landed")) return LANDED;
        if (value.equals("scheduled")) return SCHEDULED;
        throw new IOException("Cannot deserialize Status");
    }
}
