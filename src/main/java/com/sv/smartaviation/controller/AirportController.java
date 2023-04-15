package com.sv.smartaviation.controller;

import com.sv.smartaviation.entity.Airport;
import com.sv.smartaviation.service.AirportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/airports")
@Slf4j
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AirportController {
    private final AirportService airportService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{query}")
    public ResponseEntity<List<Airport>> searchAirport(@PathVariable("query") String query) {
        return ResponseEntity.ok(airportService.searchAirport(query));
    }
}
