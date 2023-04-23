package com.sv.smartaviation.controller;

import com.sv.smartaviation.auth.UserPrincipal;
import com.sv.smartaviation.model.flight.SavedFlight;
import com.sv.smartaviation.model.flight.UpdateFlightRequest;
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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/search")
    public ResponseEntity<List<SavedFlight>> searchFlights(@RequestParam String origin,
                                                           @RequestParam String destination,
                                                           @RequestParam
                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                           LocalDate departureDate) {
        return ResponseEntity.ok(flightsService.getFlights(origin, destination, departureDate));
    }

    @GetMapping("/{flightId}")
    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.body.userId == authentication.principal.id")
    public ResponseEntity<SavedFlight> getFlightsById(@PathVariable String flightId) {
        return ResponseEntity.ok(flightsService.getFlightsById(flightId));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SavedFlight>> getFlightsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(flightsService.getFlightsByUserId(userId));
    }

    @GetMapping("/me/flights")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<SavedFlight>> getLoggedInUserFlights() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserPrincipal)authentication.getPrincipal()).getId();
        return ResponseEntity.ok(flightsService.getFlightsByUserId(userId));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SavedFlight>> getSavedFlights() {
        return ResponseEntity.ok(flightsService.getSavedFlights());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SavedFlight> updateSavedFlights(@Valid @RequestBody UpdateFlightRequest updateFlightRequest) {
        return ResponseEntity.ok(flightsService.updateFlight(updateFlightRequest));
    }
}
