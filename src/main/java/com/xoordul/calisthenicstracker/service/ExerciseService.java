package com.xoordul.calisthenicstracker.service;

import com.xoordul.calisthenicstracker.model.Exercise;
import com.xoordul.calisthenicstracker.repository.ExerciseRepository;
import com.xoordul.calisthenicstracker.repository.WorkoutExerciseRepository;
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
    private final WorkoutExerciseRepository workoutExerciseRepository;

    // constructor: puts the actual tool in the slot
    public ExerciseService(ExerciseRepository exerciseRepository, WorkoutExerciseRepository workoutExerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    // get all exercises from repository
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    // create exercises and save them to the repository
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    // checks if exercise is used in history
    public void deleteExercise(Long id) {
        if (workoutExerciseRepository.existsByExerciseId(id)) {
            throw new RuntimeException("This exercise can't be deleted, because it is already in use.");
        } else {
            exerciseRepository.deleteById(id);
        }
    }
}
