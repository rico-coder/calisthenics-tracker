package com.xoordul.calisthenicstracker.controller;

import com.xoordul.calisthenicstracker.model.Exercise;
import com.xoordul.calisthenicstracker.service.ExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Rico Krenn
 * Created: 06/08/2026
 * Version: 1.0
 * Description: Endpoints for Exercise
 * Project: 200_Abschlussprojekt
 */

// says the class it is a controller
@RestController
// defines base url for all methods in this class
@RequestMapping("/api/exercises")
public class ExerciseController {

    // fields: makes the slot for the tool ready
    private final ExerciseService exerciseService;

    // constructor: puts the actual tool in the slot
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    // endpoint get all exercises
    @GetMapping
    public List<Exercise> getAll() {
        return exerciseService.getAllExercises();
    }

    // endpoint post exercises
    @PostMapping
    public Exercise create(@RequestBody Exercise exercise) {
        return exerciseService.createExercise(exercise);
    }

    // endpoint delete exercises
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
    }
}
