package com.sv.smartaviation.service;

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

    public Flight getFLights() {
        Flight flight = restTemplate.getForObject("/flights", Flight.class);
        log.debug("Flight Response: {}", flight);
        return flight;
    }

}
