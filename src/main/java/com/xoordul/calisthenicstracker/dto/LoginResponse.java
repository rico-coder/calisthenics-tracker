package com.xoordul.calisthenicstracker.dto;

/**
 * Author: Rico Krenn
 * Created: 06/08/2026
 * Version: 1.1
 * Description: The server sends a JWT when it was a successful login
 * Project: 200_Abschlussprojekt
 */

// This is what the server sends back after a successful login
public class LoginResponse {

    // sends back the JWT
    private String jwt;

    public LoginResponse(String token) {
        this.jwt = token;
    }

    // Getter and Setter from jwt
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
