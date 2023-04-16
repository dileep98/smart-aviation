package com.sv.smartaviation.service;

import com.sv.smartaviation.exception.ResourceNotFoundException;
import com.sv.smartaviation.mapper.UserPreferenceMapper;
import com.sv.smartaviation.model.user.UserPreference;
import com.sv.smartaviation.repository.FlightRepository;
import com.sv.smartaviation.repository.UserPreferenceRepository;
import com.sv.smartaviation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserPreferenceService {

    private final UserPreferenceRepository userPreferenceRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final UserPreferenceMapper userPreferenceMapper;

    public void updateFlightPreferenceForUser(UserPreference userPreference) {
        var user = userRepository.findById(userPreference.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "userPreference.userId", userPreference.getUserId()));
        var flight = flightRepository.findById(userPreference.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException("Flight", "userPreference.flightId", userPreference.getFlightId()));
        var entity = userPreferenceMapper.mapToEntity(userPreference, user, flight);
        userPreferenceRepository.save(entity);
    }

}
