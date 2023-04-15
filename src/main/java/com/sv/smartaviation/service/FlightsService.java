package com.sv.smartaviation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sv.smartaviation.entity.SavedFlight;
import com.sv.smartaviation.model.skyscanner.Flight;
import com.sv.smartaviation.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightsService {

    private final RestTemplate restTemplate;

    private FlightRepository flightRepository;


    public Flight getFLights(String origin, String destination, LocalDate departureDate) {
        HashMap<String, String> request = new HashMap<>();
        request.put("adults", "1");
        request.put("origin", origin);
        request.put("destination", destination);
        request.put("departureDate", departureDate.toString());
        request.put("currency", "USD");
        // Mock code
        ObjectMapper mapper = new ObjectMapper();
        Flight flight = new Flight();
        try {
            String file = "src/test/resources/getFlights.json";
            String json = readFileAsString(file);
            flight = mapper.readValue(json, Flight.class);
        } catch (Exception e) {

        } // Mock code
//        Flight flight = restTemplate.getForObject("/search-extended?adults={adults}&origin={origin}&destination={destination}&departureDate={departureDate}&currency={currency}", Flight.class, request);
        log.debug("Flight Response: {}", flight);
        return flight;
    }

    public List<SavedFlight> getSavedFlights(){
        return flightRepository.findAll();
    }
    public SavedFlight getFlightsById(Long flightId){
        return flightRepository.findByIdIn(flightId).get();
    }

    public List<SavedFlight> getFlightsByUserId(Long userId){
        return flightRepository.findAllByUserId(userId).get();
    }

    public SavedFlight updateFlight(SavedFlight savedFlight){
        return flightRepository.save(savedFlight);
    }


    private String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
