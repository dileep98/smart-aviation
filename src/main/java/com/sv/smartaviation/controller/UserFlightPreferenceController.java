package com.sv.smartaviation.controller;

import com.sv.smartaviation.exception.BadRequestException;
import com.sv.smartaviation.model.user.UserFlightPreference;
import com.sv.smartaviation.model.user.UserFlightPreferenceResponse;
import com.sv.smartaviation.service.UserFlightPreferenceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/user-flight-pref")
@Slf4j
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserFlightPreferenceController {
    private final UserFlightPreferenceService userFlightPreferenceService;

    @GetMapping("/{id}")
    @PostAuthorize("returnObject.body.flight.userId == authentication.principal.id")
    public ResponseEntity<UserFlightPreferenceResponse> getUserFlightPreferenceById(@PathVariable Long id) {
        return ResponseEntity.ok(userFlightPreferenceService.getUserFlightPreferenceForId(id));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("userId == authentication.principal.id")
    public ResponseEntity<List<UserFlightPreferenceResponse>> getUserFlightPreferenceForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userFlightPreferenceService.getUserFlightPreferenceForUser(userId));
    }

    @PostMapping
    @PreAuthorize("userFlightPreference.userId == authentication.principal.id")
    public ResponseEntity<UserFlightPreferenceResponse> saveUserFlightPreference(@Valid @RequestBody UserFlightPreference userFlightPreference) {

        if (userFlightPreference.getId() != null) {
            throw new BadRequestException("Id should be null for new preference");
        }

        var result = userFlightPreferenceService.saveFlightPreferenceForUser(userFlightPreference);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/v1/users-flight-pref/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(result);
    }

    @PutMapping
    @PreAuthorize("userFlightPreference.userId == authentication.principal.id")
    public ResponseEntity<UserFlightPreferenceResponse> updateUserFlightPreference(@Valid @RequestBody UserFlightPreference userFlightPreference) {
        if (userFlightPreference.getId() == null) {
            throw new BadRequestException("Id is required for update");
        }
        var result = userFlightPreferenceService.updateFlightPreferenceForUser(userFlightPreference);
        return ResponseEntity.ok(result);
    }

}
