package com.xoordul.calisthenicstracker.dto;

/**
 * Author: Rico Krenn
 * Created: 06/08/2026
 * Version: 1.0
 * Description: The frontend sends a sign in request to the endpoint
 * Project: 200_Abschlussprojekt
 */

// This is what the frontend sends to the login endpoint
public class LoginRequest {

    // the username gets sent
    private String username;
    // the password gets sent
    private String password;

    // Getter and Setter for username and password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
