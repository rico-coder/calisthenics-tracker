package com.xoordul.calisthenicstracker.controller;

import com.xoordul.calisthenicstracker.dto.WeeklyVolumeResponse;
import com.xoordul.calisthenicstracker.repository.WorkoutSetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: Rico Krenn
 * Created: 06/10/2026
 * Version: 1.0
 * Description: Endpoint for stats
 * Project: 200_Abschlussprojekt
 */

// says the class it is a controller
@RestController
// defines base url for all methods in this class
@RequestMapping("/api/stats")
public class StatsController {

    // fields for week and totalSets
    private final WorkoutSetRepository workoutSetRepository;

    // constructor for week and totalSets
    public StatsController(WorkoutSetRepository workoutSetRepository) {
        this.workoutSetRepository = workoutSetRepository;
    }

    // endpoint for volume
    @GetMapping("/volume")
    public List<WeeklyVolumeResponse> getWeeklyVolume() {
        // return is made with assistance of ai
        return workoutSetRepository.findWeeklyVolume()
                .stream()
                .map(row -> {
                    WeeklyVolumeResponse response = new WeeklyVolumeResponse();
                    response.setWeek("W" + row[0]);
                    response.setTotalSets(((Number) row[1]).intValue());
                    return response;
                })
                .toList();
    }
}
