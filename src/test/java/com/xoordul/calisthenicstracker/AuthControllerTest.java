package com.xoordul.calisthenicstracker;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: Rico Krenn
 * Created: 06/10/2026
 * Version: 1.0
 * Description: Test the Authentication (AI assisted)
 * Project: 200_Abschlussprojekt
 */
// starts the full application context for the test
@SpringBootTest
// Creates a MockMvc object that simulates HTTP requests
@AutoConfigureMockMvc
// rolls back any database changes after each test
@Transactional
@ActiveProfiles("test")
public class AuthControllerTest {
    // inject MockMvc
    @Autowired
    private MockMvc mockMvc;

    // test method
    @Test
    public void loginReturnsToken() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"xoordul\",\"password\":\"admin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").exists());
    }
}
