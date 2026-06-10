package com.xoordul.calisthenicstracker.repository;

import com.xoordul.calisthenicstracker.model.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    // query for the sets for a week
    // made with assistance of ai
    @Query("SELECT EXTRACT(WEEK FROM ws.date) as week, COUNT(s) as totalSets " +
            "FROM WorkoutSet s " +
            "JOIN s.workoutExercise we " +
            "JOIN we.workoutSession ws " +
            "GROUP BY EXTRACT(WEEK FROM ws.date) " +
            "ORDER BY week")
    List<Object[]> findWeeklyVolume();
}
