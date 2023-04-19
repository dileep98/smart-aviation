package com.sv.smartaviation.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
@Data
public class ApiConfig {
    private boolean debug = false;
    private SkyScannerConfig skyScanner;

    @Data
    public static class SkyScannerConfig {
        private String url;
        private String key;
        private boolean debug = false;
        private String host;
    }

}
