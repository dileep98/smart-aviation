package com.sv.smartaviation.controller;

import com.sv.smartaviation.auth.UserPrincipal;
import com.sv.smartaviation.model.auth.UpdateUserProfileModel;
import com.sv.smartaviation.model.auth.UserProfileModel;
import com.sv.smartaviation.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/profile")
@Slf4j
@Validated
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    private final AuthenticationManager authenticationManager;

    @PutMapping("/update/me")
    public ResponseEntity<Void> updateUserProfile(@Valid @RequestBody UserProfileModel updateUserProfileModel) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserPrincipal)authentication.getPrincipal()).getId();
        userProfileService.updateUserProfile(updateUserProfileModel,userId);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/me")
    public ResponseEntity<UpdateUserProfileModel> getUserProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserPrincipal)authentication.getPrincipal()).getId();
        return ResponseEntity.ok(userProfileService.getUserProfile(userId));

    }

}
