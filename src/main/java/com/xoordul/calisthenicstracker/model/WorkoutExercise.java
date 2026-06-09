package com.xoordul.calisthenicstracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Rico Krenn
 * Created: 6/2/2026
 * Version: 1.0
 * Description: WorkoutExercise model
 * Project: 200_Abschlussprojekt
 */
// @Entity and @Table are to create the Entity and Name
@Entity
@Table(name = "workout_exercise")
public class WorkoutExercise {

    // Here is determined that id is the PK and is set to AI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Here is The FK workout_session_id defined
    @ManyToOne
    @JoinColumn(name = "workout_session_id", nullable = false)
    private WorkoutSession workoutSession;

    // Here is the FK exercise_id defined
    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    // OneToMany so that WorkoutExercise has knowledge of its WorkoutSet children
    @OneToMany(mappedBy = "workoutExercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutSet> workoutSets = new ArrayList<>();

    // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for WorkoutSession
    public WorkoutSession getWorkoutSession() {
        return workoutSession;
    }

    // Setter for WorkoutSession
    public void setWorkoutSession(WorkoutSession workoutSession) {
        this.workoutSession = workoutSession;
    }

    // Getter for Exercise
    public Exercise getExercise() {
        return exercise;
    }

    // Setter for Exercise
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    // Getter for WorkoutSet
    public List<WorkoutSet> getWorkoutSets() {
        return workoutSets;
    }

    // Setter for WorkoutSet
    public void setWorkoutSets(List<WorkoutSet> workoutSets) {
        this.workoutSets = workoutSets;
    }
}
