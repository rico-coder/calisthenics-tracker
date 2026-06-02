package com.xoordul.calisthenicstracker.repository;

import com.xoordul.calisthenicstracker.model.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Rico Krenn
 * Created: 6/2/2026
 * Version: 1.0
 * Description: Repository for WorkoutSet
 * Project: 200_Abschlussprojekt
 */
// Repository for Table WorkoutSet
@Repository
public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Long> {
}
