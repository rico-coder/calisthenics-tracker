package com.xoordul.calisthenicstracker.controller;

import com.xoordul.calisthenicstracker.model.ProgressionLevel;
import com.xoordul.calisthenicstracker.service.ProgressionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Rico Krenn
 * Created: 06/08/2026
 * Version: 1.0
 * Description: Endpoints for progression
 * Project: 200_Abschlussprojekt
 */

// says the class it is a controller
@RestController
// defines base url for all methods in this class
@RequestMapping("/api/progression")
public class ProgressionController {

    // fields: makes the slot for the tool ready
    private final ProgressionService progressionService;

    // constructor: puts the actual tool in the slot
    public ProgressionController(ProgressionService progressionService) {
        this.progressionService = progressionService;
    }

    // endpoint get all exercises
    @GetMapping
    public List<ProgressionLevel> getAll() {
        return progressionService.getAllProgressions();
    }

    // endpoint update progression level
    @PutMapping("/{id}")
    public ProgressionLevel update(@PathVariable Long id, @RequestBody ProgressionLevel progressionLevel) {
        return progressionService.updateProgression(id, progressionLevel.isCurrent());
    }
}
