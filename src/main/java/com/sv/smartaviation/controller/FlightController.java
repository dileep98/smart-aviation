package com.sv.smartaviation.controller;

import com.sv.smartaviation.entity.Flight;
import com.sv.smartaviation.model.flight.SavedFlight;
import com.sv.smartaviation.service.FlightsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flights")
@Slf4j
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class FlightController {

    private final FlightsService flightsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SavedFlight>> getFlights(@RequestParam String origin,
                                                        @RequestParam String destination,
                                                        @RequestParam
                                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                                   LocalDate departureDate) {
        return ResponseEntity.ok(flightsService.getFlights(origin, destination, departureDate));
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightsById(@PathVariable String flightId) {
        return ResponseEntity.ok(flightsService.getFlightsById(flightId));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == authentication.principal.id")
    public ResponseEntity<List<Flight>> getFlightsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(flightsService.getFlightsByUserId(userId));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Flight>> getSavedFlights() {
        return ResponseEntity.ok(flightsService.getSavedFlights());
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Flight> updateSavedFlights(@Valid @RequestBody Flight flight) {
        return ResponseEntity.ok(flightsService.updateFlight(flight));
    }
}
