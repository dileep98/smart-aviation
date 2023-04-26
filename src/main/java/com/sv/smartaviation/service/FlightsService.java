package com.sv.smartaviation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sv.smartaviation.entity.Flight;
import com.sv.smartaviation.exception.InternalServerErrorException;
import com.sv.smartaviation.exception.ResourceNotFoundException;
import com.sv.smartaviation.mapper.FlightMapper;
import com.sv.smartaviation.model.flight.SavedFlight;
import com.sv.smartaviation.model.flight.UpdateFlightRequest;
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
    private final SmsService smsService;
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
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.findAndRegisterModules();
//        com.sv.smartaviation.model.skyscanner.Flight flight = new com.sv.smartaviation.model.skyscanner.Flight();
//        try {
//            String file = "src/test/resources/getFlights.json";
//            String json = readFileAsString(file);
//            flight = mapper.readValue(json, com.sv.smartaviation.model.skyscanner.Flight.class);
//        } catch (Exception e) {
//            log.error("Error while mapping", e);
//        } // Mock code
        var flight = restTemplate.getForObject("/search-extended?adults={adults}&origin={origin}&destination={destination}&departureDate={departureDate}&currency={currency}", com.sv.smartaviation.model.skyscanner.Flight.class, request);
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

    public List<SavedFlight> getSavedFlights() {
        return flightRepository
                .findAll()
                .parallelStream()
                .map(flightMapper::map)
                .collect(Collectors.toList());
    }

    public SavedFlight getFlightsById(String flightId) {
        return flightRepository
                .findById(flightId)
                .map(flightMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Flight", "flightId", flightId));
    }

    public List<SavedFlight> getFlightsByUserId(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        return user.getUserFlightPreferences()
                .stream()
                .map(f -> f.getFlight().getId())
                .map(this::getFlightsById)
                .collect(Collectors.toList());
    }

    public SavedFlight updateFlight(UpdateFlightRequest updateFlightRequest) {
        Flight updatedFlight;

        if (!flightRepository.existsById(updateFlightRequest.getId())) {
            throw new ResourceNotFoundException("flight", "updateFlightRequest.id", updateFlightRequest.getId());
        }

        try {
            var existingFlight = flightRepository.findById(updateFlightRequest.getId())
                    .orElseThrow(()->new ResourceNotFoundException("flight", "updateFlightRequest.id", updateFlightRequest.getId()));
            existingFlight.setArrivalDateTime(updateFlightRequest.getArrivalDateTime());
            existingFlight.setDepartureDateTime(updateFlightRequest.getDepartureDateTime());
            updatedFlight = flightRepository.save(existingFlight);
        } catch (Exception e) {
            log.error("Error occurred while updating flight", e);
            throw new InternalServerErrorException("Error occurred while updating flight", e);
        }

        var flightPreferences = userFlightPreferenceRepository.findAllByFlightIdAndEnabledIsTrue(updatedFlight.getId());
        Flight finalUpdatedFlight = updatedFlight;
        flightPreferences.forEach(
                userFlightPreference -> {
                    var user = userFlightPreference.getUser();
                    var profile = user.getUserProfile();
                    String subject = String.format("Update on flight %s", finalUpdatedFlight.getFlightNumber());
                    String message = String.format("Your flight %s got updated with Departure Time: %s Arrival Time: %s",
                            finalUpdatedFlight.getFlightNumber(), finalUpdatedFlight.getDepartureDateTime(), finalUpdatedFlight.getArrivalDateTime());
                    if (profile.getEmailToggle()) {
                        emailService.sendEmail(user.getEmail(), subject, message);
                    }
                    if (profile.getSmsToggle()) {
                        smsService.sendSms(profile.getPhoneNumber(), message);
                    }
                }
        );
        return flightMapper.map(updatedFlight);
    }


    private String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
