package com.xoordul.calisthenicstracker.controller;

import com.xoordul.calisthenicstracker.model.WorkoutSession;
import com.xoordul.calisthenicstracker.service.WorkoutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Rico Krenn
 * Created: 06/09/2026
 * Version: 1.0
 * Description: Endpoint for workout
 * Project: 200_Abschlussprojekt
 */

// says the class it is a controller
@RestController
// defines base url for all methods in this class
@RequestMapping("/api/workout")
public class WorkoutController {

    // fields: makes the slot for the tool ready
    private final WorkoutService workoutService;

    // constructor: puts the actual tool in the slot
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    // endpoint get all workouts
    @GetMapping
    public List<WorkoutSession> getAll() {
        return workoutService.getAllWorkouts();
    }

    // endpoint to get workout by id
    @GetMapping("/{id}")
    public WorkoutSession getById(@PathVariable Long id) {
        return workoutService.getWorkoutSessionById(id);
    }

    // endpoint to create workouts
    @PostMapping
    public WorkoutSession create(@RequestBody WorkoutSession workoutSession) {
        return workoutService.createWorkout(workoutSession);
    }

    // endpoint to delete workouts
    @DeleteMapping("/{id}")
    public void deleteWorkoutSession(@PathVariable Long id) {
        workoutService.deleteWorkoutSession(id);
    }
}
