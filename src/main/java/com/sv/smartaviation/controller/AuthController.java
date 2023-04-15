package com.sv.smartaviation.controller;

import com.sv.smartaviation.auth.JwtTokenProvider;
import com.sv.smartaviation.auth.UserPrincipal;
import com.sv.smartaviation.entity.Role;
import com.sv.smartaviation.entity.RoleName;
import com.sv.smartaviation.entity.User;
import com.sv.smartaviation.exception.AppException;
import com.sv.smartaviation.model.auth.ApiResponse;
import com.sv.smartaviation.model.auth.JwtAuthenticationResponse;
import com.sv.smartaviation.model.auth.LoginRequest;
import com.sv.smartaviation.model.auth.RefreshRequest;
import com.sv.smartaviation.model.auth.SignUpRequest;
import com.sv.smartaviation.repository.RoleRepository;
import com.sv.smartaviation.repository.UserRepository;
import java.net.URI;
import java.util.Collections;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/auth")
@Slf4j
@Validated
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();
        var userId = Long.toString(principal.getId());

        String jwt = tokenProvider.generateToken(userId);
        String refreshToken = tokenProvider.generateRefreshToken(userId);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, refreshToken));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@Valid @RequestBody RefreshRequest refreshRequest) {
        String requestRefreshToken = refreshRequest.getRefreshToken();
        if (!tokenProvider.validateToken(requestRefreshToken)) {
            throw new AppException("Invalid refresh token");
        }

        var userId = Long.toString(tokenProvider.getUserIdFromJWT(requestRefreshToken));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(userId);
        String refreshToken = tokenProvider.generateRefreshToken(userId);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, refreshToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));


        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/v1/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    // TODO
    /*@PostMapping("/updateUserPreference")
    public ResponseEntity<UserPreference> updateUserPreference(@Valid @RequestBody Use){

    }*/
}
