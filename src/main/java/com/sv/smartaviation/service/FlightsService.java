package com.sv.smartaviation.service;

import com.sv.smartaviation.config.AirLabsRestTemplate;
import com.sv.smartaviation.config.AirlabsConfig;
import com.sv.smartaviation.model.Flight;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightsService {

    private final RestTemplate restTemplate;
    private final AirlabsConfig airlabsConfig;

    public Flight getFLights(){
        Flight flight = restTemplate.getForObject("/flights?key="+airlabsConfig.getKey(), Flight.class);
        log.debug("Flight Payload: {}", flight);
        return flight;
    }

}
