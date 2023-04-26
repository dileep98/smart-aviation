package com.sv.smartaviation.controller;

import com.sv.smartaviation.auth.UserPrincipal;
import com.sv.smartaviation.model.user.UserFlightPreference;
import com.sv.smartaviation.model.user.UserFlightPreferenceResponse;
import com.sv.smartaviation.service.UserFlightPreferenceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserFlightPreferenceResponse>> getUserFlightPreferenceForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userFlightPreferenceService.getUserFlightPreferenceForUser(userId));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<UserFlightPreferenceResponse>> getLoggedInUserFlightPreferences() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserPrincipal) authentication.getPrincipal()).getId();
        return ResponseEntity.ok(userFlightPreferenceService.getUserFlightPreferenceForUser(userId));
    }

    @PostMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserFlightPreferenceResponse> saveOrUpdateFlightPreferences(@Valid @RequestBody UserFlightPreference userFlightPreference) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserPrincipal) authentication.getPrincipal()).getId();
        var result = userFlightPreferenceService.saveOrUpdateFlightPreferences(userFlightPreference, userId);
        return ResponseEntity.ok(result);
    }
}
