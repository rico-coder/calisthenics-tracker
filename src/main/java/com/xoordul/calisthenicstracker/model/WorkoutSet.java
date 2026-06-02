package com.xoordul.calisthenicstracker.model;

import jakarta.persistence.*;

/**
 * Author: Rico Krenn
 * Created: 6/2/2026
 * Version:
 * Description:
 * Project: 200_Abschlussprojekt
 */
// @Entity and @Table are to create the Entity and Name
@Entity
@Table(name = "workout_set")
public class WorkoutSet {

    // Here is determined that id is the PK and is set to AI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Here is the FK workout_exercise_id defined
    @ManyToOne
    @JoinColumn(name = "workout_exercise_id", nullable = false)
    private WorkoutExercise workoutExercise;

    // The Attribut setNumber is defined as int
    private int setNumber;

    // The Attribut reps is defined as int
    private int reps;

    // Getter from id
    public Long getId() {
        return id;
    }

    // Setter from id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter from FK WorkoutExercise
    public WorkoutExercise getWorkoutExercise() {
        return workoutExercise;
    }

    // Setter from FK WorkoutExercise
    public void setWorkoutExercise(WorkoutExercise workoutExercise) {
        this.workoutExercise = workoutExercise;
    }

    // Getter from setNumber
    public int getSetNumber() {
        return setNumber;
    }

    // Setter from setNumeber
    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    // Getter from reps
    public int getReps() {
        return reps;
    }

    // Setter from reps
    public void setReps(int reps) {
        this.reps = reps;
    }
}
