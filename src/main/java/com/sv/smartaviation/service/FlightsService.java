package com.sv.smartaviation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sv.smartaviation.entity.Flight;
import com.sv.smartaviation.exception.ResourceNotFoundException;
import com.sv.smartaviation.mapper.FlightMapper;
import com.sv.smartaviation.repository.FlightRepository;
import com.sv.smartaviation.repository.UserRepository;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
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
    private final FlightMapper flightMapper;


    public com.sv.smartaviation.model.skyscanner.Flight getFlights(String origin, String destination, LocalDate departureDate) {
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
        flight.getItineraries().getResults().forEach(
                f -> {
                    f.getLegs().forEach(
                            l -> {
                                if (!flightRepository.existsById(l.getId())) {
                                    var flightEntity = flightMapper.map(l, f.getPricingOptions().get(0));
                                }
                            }
                    );
                }
        );
        log.debug("Flight Response: {}", flight);
        return flight;
    }

    public List<Flight> getSavedFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightsById(String flightId) {
        return flightRepository.findById(flightId).orElseThrow(() -> new ResourceNotFoundException("Flight", "flightId", flightId));
    }

    public List<Flight> getFlightsByUserId(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        return user.getUserFlightPreferences()
                .stream()
                .map(f -> f.getFlight().getId())
                .map(this::getFlightsById)
                .collect(Collectors.toList());
    }

    public Flight updateFlight(Flight flight) {
        return flightRepository.save(flight);
    }


    private String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
