package com.xoordul.calisthenicstracker.dto;

/**
 * Author: Rico Krenn
 * Created: 06/10/2026
 * Version: 1.0
 * Description: dto to send a status and message with the error page
 * Project: 200_Abschlussprojekt
 */
// This is sent with the error page
public class ErrorResponse {
    // string with the message with information to the error
    private String message;
    // string with the status of the error
    private int status;

    // constructor of message and status
    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    // Getter and Setter for message and status
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
