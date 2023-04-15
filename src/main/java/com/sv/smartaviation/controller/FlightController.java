package com.sv.smartaviation.controller;

import com.sv.smartaviation.entity.SavedFlight;
import com.sv.smartaviation.model.skyscanner.Flight;
import com.sv.smartaviation.service.FlightsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/flights")
@Slf4j
@Validated
@RequiredArgsConstructor
public class FlightController {

    private final FlightsService flightsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> getFlights(@RequestParam String origin, @RequestParam String destination, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        return ResponseEntity.ok(flightsService.getFLights(origin, destination, departureDate));
    }

    @GetMapping("/flightById")
    public ResponseEntity<SavedFlight> getFlightsById(@RequestParam Long flightId) {
        return ResponseEntity.ok(flightsService.getFlightsById(flightId));
    }

    @GetMapping("/flightByUserId")
    public ResponseEntity<List<SavedFlight>> getFlightsByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(flightsService.getFlightsByUserId(userId));
    }

    @GetMapping("/savedFlights")
    public ResponseEntity<List<SavedFlight>> getSavedFlights() {
        return ResponseEntity.ok(flightsService.getSavedFlights());
    }

    @PostMapping("/updateFlight")
    public ResponseEntity<SavedFlight> updateSavedFlights(@Valid @RequestBody SavedFlight savedFlight){
        return ResponseEntity.ok(flightsService.updateFlight(savedFlight));
    }

    // TODO
//    @PostMapping("/saveFlight")
//    public ResponseEntity<Void> saveFlight(@Valid @RequestBody SignUpRequest signUpRequest){
//        return ResponseEntity.created();
//    }

}
