package com.sv.smartaviation.service;

import com.sv.smartaviation.exception.ResourceNotFoundException;
import com.sv.smartaviation.mapper.UserFlightPreferenceMapper;
import com.sv.smartaviation.model.user.UserFlightPreference;
import com.sv.smartaviation.model.user.UserFlightPreferenceResponse;
import com.sv.smartaviation.repository.FlightRepository;
import com.sv.smartaviation.repository.UserFlightPreferenceRepository;
import com.sv.smartaviation.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserFlightPreferenceService {

    private final UserFlightPreferenceRepository userFlightPreferenceRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final UserFlightPreferenceMapper userFlightPreferenceMapper;

    public UserFlightPreferenceResponse updateFlightPreferenceForUser(UserFlightPreference userFlightPreference) {
        if (!userFlightPreferenceRepository.existsById(userFlightPreference.getId())) {
            throw new ResourceNotFoundException("UserFlightPreference", "id", userFlightPreference.getId());
        }
        return saveFlightPreferenceForUser(userFlightPreference);
    }

    public UserFlightPreferenceResponse saveFlightPreferenceForUser(UserFlightPreference userFlightPreference) {
        var user = userRepository.findById(userFlightPreference.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "userPreference.userId", userFlightPreference.getUserId()));
        var flight = flightRepository.findById(userFlightPreference.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException("Flight", "userPreference.flightId", userFlightPreference.getFlightId()));
        var entity = userFlightPreferenceMapper.mapToEntity(userFlightPreference, user, flight);
        var saved = userFlightPreferenceRepository.save(entity);
        return userFlightPreferenceMapper.map(saved);
    }

    public List<UserFlightPreferenceResponse> getUserFlightPreferenceForUser(Long userId) {
        if (!userFlightPreferenceRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "userId", userId);
        }
        return userFlightPreferenceRepository.findAllByUserId(userId)
                .stream()
                .map(userFlightPreferenceMapper::map)
                .collect(Collectors.toList());
    }

    public UserFlightPreferenceResponse getUserFlightPreferenceForId(Long id) {
        var result = userFlightPreferenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserFlightPreference", "id", id));
        return userFlightPreferenceMapper.map(result);
    }

}
