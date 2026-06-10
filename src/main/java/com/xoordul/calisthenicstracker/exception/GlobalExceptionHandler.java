package com.xoordul.calisthenicstracker.exception;

import com.xoordul.calisthenicstracker.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Author: Rico Krenn
 * Created: 06/10/2026
 * Version: 1.0
 * Description: Catches errors and returns JSON
 * Project: 200_Abschlussprojekt
 */

// This annotation makes the class an exception handler
@RestControllerAdvice
public class GlobalExceptionHandler {

    // The parameter RuntimeException is the error message
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), 404);
        // sets the status code and error message together
        return ResponseEntity.status(404).body(error);
    }

    // The parameter Exception is the error message
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception  ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), 500);
        // sets the status code and error message together
        return ResponseEntity.status(500).body(error);
    }
}
