package com.xoordul.calisthenicstracker.service;

import com.xoordul.calisthenicstracker.model.Exercise;
import com.xoordul.calisthenicstracker.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Rico Krenn
 * Created: 06/08/2026
 * Version: 1.0
 * Description: Service for exercise
 * Project: 200_Abschlussprojekt
 */

@Service
public class ExerciseService {

    // field: makes the slot for the tool ready
    private final ExerciseRepository exerciseRepository;

    // constructor: puts the actual tool in the slot
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    // get all exercises from repository
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    // create exercises and save them to the repository
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

}
