package com.sv.smartaviation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sv.smartaviation.entity.Flight;
import com.sv.smartaviation.exception.ResourceNotFoundException;
import com.sv.smartaviation.mapper.FlightMapper;
import com.sv.smartaviation.model.flight.SavedFlight;
import com.sv.smartaviation.repository.FlightRepository;
import com.sv.smartaviation.repository.UserFlightPreferenceRepository;
import com.sv.smartaviation.repository.UserRepository;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private final SmsSevice smsSevice;
    private final EmailService emailService;
    private final UserFlightPreferenceRepository userFlightPreferenceRepository;


    public List<SavedFlight> getFlights(String origin, String destination, LocalDate departureDate) {
        HashMap<String, String> request = new HashMap<>();
        request.put("adults", "1");
        request.put("origin", origin);
        request.put("destination", destination);
        request.put("departureDate", departureDate.toString());
        request.put("currency", "USD");
        // Mock code
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        com.sv.smartaviation.model.skyscanner.Flight flight = new com.sv.smartaviation.model.skyscanner.Flight();
        try {
            String file = "src/test/resources/getFlights.json";
            String json = readFileAsString(file);
            flight = mapper.readValue(json, com.sv.smartaviation.model.skyscanner.Flight.class);
        } catch (Exception e) {
            log.error("Error while mapping", e);
        } // Mock code
//        var flight = restTemplate.getForObject("/search-extended?adults={adults}&origin={origin}&destination={destination}&departureDate={departureDate}&currency={currency}", com.sv.smartaviation.model.skyscanner.Flight.class, request);
        List<SavedFlight> savedFlights = new ArrayList<>();
        flight.getItineraries().getResults().forEach(
                f -> f.getLegs().forEach(
                        l -> {
                            var flightEntityOptional = flightRepository.findById(l.getId());
                            if (flightEntityOptional.isPresent()) {
                                var flight1 = flightEntityOptional.get();
                                var savedFlight = flightMapper.map(flight1);
                                savedFlights.add(savedFlight);
                            } else {
                                var flightEntity = flightMapper.map(l, f.getPricingOptions().get(0));
                                var flight1 = flightRepository.save(flightEntity);
                                var savedFlight = flightMapper.map(flight1);
                                savedFlights.add(savedFlight);
                            }
                        }
                )
        );
        log.debug("Flight Response: {}", flight);
        return savedFlights;
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
        Flight updatedFlight = null;
        try {
            updatedFlight = flightRepository.save(flight);
        } catch (Exception e) {
            log.error("Error occurred while updating flight", e);
        }

        var flightPreferences = userFlightPreferenceRepository.findAllByFlightIdAAndEnabled(flight.getId());
        flightPreferences.forEach(
                userFlightPreference -> {
                    var user = userFlightPreference.getUser();
                    var profile = user.getUserProfile();
                    String subject = String.format("Update on flight %s", flight.getFlightNumber());
                    String message = String.format("Your flight %s got updated with Departure Time: %s Arrival Time: %s",
                            flight.getFlightNumber(), flight.getDepartureDateTime(), flight.getArrivalDateTime());
                    if (profile.getEmailToggle()) {
                        emailService.sendEmail(user.getEmail(), subject, message);
                    }
                    if (profile.getSmsToggle()) {
                        smsSevice.sendSms(profile.getPhoneNumber(), message);
                    }
                }
        );
        return updatedFlight;
    }


    private String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
