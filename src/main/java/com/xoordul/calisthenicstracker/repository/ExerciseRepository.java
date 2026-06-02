package com.xoordul.calisthenicstracker.repository;
/**
 * Author: Rico Krenn
 * Created: 6/2/2026
 * Version: 1.0
 * Description: Exercise repository
 * Project: 200_Abschlussprojekt
 */

import com.xoordul.calisthenicstracker.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository for Table Exercise
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
