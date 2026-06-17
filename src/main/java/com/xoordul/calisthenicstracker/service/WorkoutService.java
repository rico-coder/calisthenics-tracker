package com.xoordul.calisthenicstracker.service;

import com.xoordul.calisthenicstracker.model.WorkoutExercise;
import com.xoordul.calisthenicstracker.model.WorkoutSession;
import com.xoordul.calisthenicstracker.model.WorkoutSet;
import com.xoordul.calisthenicstracker.repository.WorkoutSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Rico Krenn
 * Created: 06/09/2026
 * Version: 1.0
 * Description: Service for workout
 * Project: 200_Abschlussprojekt
 */

@Service
public class WorkoutService {

    // field: makes the slot for the tool ready
    private final WorkoutSessionRepository workoutSessionRepository;

    // constructor: puts the actual tool in the slot
    public WorkoutService(WorkoutSessionRepository workoutSessionRepository) {
        this.workoutSessionRepository = workoutSessionRepository;
    }

    // get all workouts from repository
    @Transactional
    public List<WorkoutSession> getAllWorkouts() {
        return workoutSessionRepository.findAll();
    }

    // get workout with id from repository
    public WorkoutSession getWorkoutSessionById(Long id) {
        return workoutSessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout session not found"));
    }

    // create workout (parent refactor made with AI)
    public WorkoutSession createWorkout(WorkoutSession workoutSession) {
        for (WorkoutExercise we : workoutSession.getWorkoutExercises()) {
            we.setWorkoutSession(workoutSession);
            for (WorkoutSet ws : we.getWorkoutSets()) {
                ws.setWorkoutExercise(we);
            }
        }
        return workoutSessionRepository.save(workoutSession);
    }

    // delete workout by id from repository
    public void deleteWorkoutSession(Long id) {
        workoutSessionRepository.deleteById(id);
    }
}
