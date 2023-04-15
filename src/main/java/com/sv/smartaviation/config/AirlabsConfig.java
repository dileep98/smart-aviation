package com.sv.smartaviation.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.airlabs")
@Data
public class AirlabsConfig {
    private String url;
    private String key;
    private boolean debug = false;
    private String host;


}
