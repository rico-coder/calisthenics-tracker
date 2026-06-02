package com.xoordul.calisthenicstracker.repository;

import com.xoordul.calisthenicstracker.model.ProgressionLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Rico Krenn
 * Created: 6/2/2026
 * Version: 1.0
 * Description: Repository for ProgressionLevel
 * Project: 200_Abschlussprojekt
 */
// Repository for Table ProgressionLevel
@Repository
public interface ProgressionLevelRepository extends JpaRepository<ProgressionLevel, Long> {
}
