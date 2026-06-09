package com.xoordul.calisthenicstracker.model;

/**
 * Author: Rico Krenn
 * Created: 6/2/2026
 * Version: 1.0
 * Description: WorkoutSession model
 * Project: 200_Abschlussprojekt
 */

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// @Entity and @Table are to create the Entity and Name
@Entity
@Table(name = "workout_session")
public class WorkoutSession {

    // Here is determined that id is the PK and is set to AI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attribut date with data type LocalDate
    private LocalDate date;

    // The string notes is set to NN
    @Column(nullable = false)
    private String notes;

    // The int effortRating is set to NN
    @Column(nullable = false)
    private Integer effortRating;

    // OneToMany so that WorkoutSession has knowledge of its WorkoutExercise children
    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutExercise> workoutExercises = new ArrayList<>();

    // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for date
    public LocalDate getDate() {
        return date;
    }

    // Setter for date
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter for notes
    public String getNotes() {
        return notes;
    }

    // Setter for notes
    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Getter for effortRating
    public Integer getEffortRating() {
        return effortRating;
    }

    // Setter for effortRating
    public void setEffortRating(Integer effortRating) {
        this.effortRating = effortRating;
    }

    // Getter for WorkoutExercise
    public List<WorkoutExercise> getWorkoutExercises() {
        return workoutExercises;
    }

    // Setter for WorkoutExercise
    public void setWorkoutExercises(List<WorkoutExercise> workoutExercises) {
        this.workoutExercises = workoutExercises;
    }
}
