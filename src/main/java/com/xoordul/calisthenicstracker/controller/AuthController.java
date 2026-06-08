package com.xoordul.calisthenicstracker.controller;

import com.xoordul.calisthenicstracker.dto.LoginRequest;
import com.xoordul.calisthenicstracker.dto.LoginResponse;
import com.xoordul.calisthenicstracker.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Rico Krenn
 * Created: 06/08/2026
 * Version: 1.0
 * Description: Auth Endpoint
 * Project: 200_Abschlussprojekt
 */

// makes the calss a controller
@RestController
// defines the base url path for all methods in this class
@RequestMapping("/api/auth")
public class AuthController {

    // fields: makes the slot for the tool ready
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // constructor: puts the actual tool in the slot
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // call point for frontend
    @PostMapping("/login")
    // makes a login request with the incoming JSON
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        // sends the username and password to JwtUtil class
        String token = jwtUtil.generateToken(loginRequest.getUsername());
        // returns token from JwtUtil class
        return new LoginResponse(token);
    }
}