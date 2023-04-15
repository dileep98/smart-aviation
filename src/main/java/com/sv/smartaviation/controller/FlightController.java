package com.sv.smartaviation.controller;

import com.sv.smartaviation.model.skyscanner.Flight;
import com.sv.smartaviation.service.FlightsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/flights")
@Slf4j
@Validated
@RequiredArgsConstructor
public class FlightController {

    private final FlightsService flightsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> getFlights(@RequestParam String origin,@RequestParam String destination,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        return ResponseEntity.ok(flightsService.getFLights(origin, destination, departureDate));
    }

}
