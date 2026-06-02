package com.xoordul.calisthenicstracker.model;

import jakarta.persistence.*;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_session_id", nullable = false)
    private WorkoutSession workoutSession;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
}
