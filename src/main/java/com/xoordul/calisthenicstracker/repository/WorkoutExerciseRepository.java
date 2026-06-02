package com.xoordul.calisthenicstracker.repository;

import com.xoordul.calisthenicstracker.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Rico Krenn
 * Created: 6/2/2026
 * Version:
 * Description:
 * Project: 200_Abschlussprojekt
 */

// Repository for Table WorkoutExercise
@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
}
