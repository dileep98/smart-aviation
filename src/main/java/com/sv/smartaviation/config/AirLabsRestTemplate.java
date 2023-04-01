package com.sv.smartaviation.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AirLabsRestTemplate {

    private final AirlabsConfig airlabsConfig;
    @Bean
    public RestTemplate airlabsRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(airlabsConfig.getUrl()));
        return restTemplate;
    }

}
