package com.sv.smartaviation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sv.smartaviation.entity.Flight;
import com.sv.smartaviation.exception.ResourceNotFoundException;
import com.sv.smartaviation.repository.FlightRepository;
import com.sv.smartaviation.repository.UserRepository;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightsService {

    private final RestTemplate restTemplate;

    private final FlightRepository flightRepository;

    private final UserRepository userRepository;


    public com.sv.smartaviation.model.skyscanner.Flight getFLights(String origin, String destination, LocalDate departureDate) {
        HashMap<String, String> request = new HashMap<>();
        request.put("adults", "1");
        request.put("origin", origin);
        request.put("destination", destination);
        request.put("departureDate", departureDate.toString());
        request.put("currency", "USD");
        // Mock code
        ObjectMapper mapper = new ObjectMapper();
        com.sv.smartaviation.model.skyscanner.Flight flight = new com.sv.smartaviation.model.skyscanner.Flight();
        try {
            String file = "src/test/resources/getFlights.json";
            String json = readFileAsString(file);
            flight = mapper.readValue(json, com.sv.smartaviation.model.skyscanner.Flight.class);
        } catch (Exception e) {

        } // Mock code
//        Flight flight = restTemplate.getForObject("/search-extended?adults={adults}&origin={origin}&destination={destination}&departureDate={departureDate}&currency={currency}", Flight.class, request);
        log.debug("Flight Response: {}", flight);
        return flight;
    }

    public List<Flight> getSavedFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightsById(Long flightId) {
        return flightRepository.findById(flightId).orElseThrow(() -> new ResourceNotFoundException("Flight", "flightId", flightId));
    }

    public List<Flight> getFlightsByUserId(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        return flightRepository.findAllByUser(user).orElse(Collections.emptyList());
    }

    public Flight updateFlight(Flight flight) {
        return flightRepository.save(flight);
    }


    private String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
