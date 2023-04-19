package com.sv.smartaviation.config;

import com.sv.smartaviation.config.interceptor.ApiKeyInterceptor;
import com.sv.smartaviation.config.interceptor.LoggingInterceptor;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SkyScannerRestTemplate {

    private final ApiConfig apiConfig;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors
                = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new ApiKeyInterceptor(apiConfig.getSkyScanner()));
        if (apiConfig.isDebug() && log.isDebugEnabled()) {
            restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
            interceptors.add(new LoggingInterceptor());
        }
        restTemplate.setInterceptors(interceptors);
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(apiConfig.getSkyScanner().getUrl()));
        return restTemplate;
    }

}
