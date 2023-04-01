package com.sv.smartaviation.controller;

import com.sv.smartaviation.model.Flight;
import com.sv.smartaviation.service.FlightsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/flights")
@Slf4j
@Validated
@RequiredArgsConstructor
public class FlightController {

    private final FlightsService flightsService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> getFlights(){
        return ResponseEntity.ok(flightsService.getFLights());
    }

}
