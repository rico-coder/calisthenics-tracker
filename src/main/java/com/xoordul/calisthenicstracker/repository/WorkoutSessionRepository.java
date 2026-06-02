package com.xoordul.calisthenicstracker.repository;

import com.xoordul.calisthenicstracker.model.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Rico Krenn
 * Created: 6/2/2026
 * Version: 1.0
 * Description: WorkoutSessionRepository
 * Project: 200_Abschlussprojekt
 */

// Repository for Table WorkoutSession
@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
}
