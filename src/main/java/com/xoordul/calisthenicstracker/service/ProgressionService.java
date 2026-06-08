package com.xoordul.calisthenicstracker.service;

import com.xoordul.calisthenicstracker.model.ProgressionLevel;
import com.xoordul.calisthenicstracker.repository.ProgressionLevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Rico Krenn
 * Created: 06/08/2026
 * Version: 1.0
 * Description: Service for progression
 * Project: 200_Abschlussprojekt
 */

@Service
public class ProgressionService {

    // field: makes the slot for the tool ready
    private final ProgressionLevelRepository progressionLevelRepository;

    // constructor: puts the actual tool in the slot
    public ProgressionService(ProgressionLevelRepository progressionLevelRepository) {
        this.progressionLevelRepository = progressionLevelRepository;
    }

    // get all exercises from repository
    public List<ProgressionLevel> getAllProgressions() {
        return progressionLevelRepository.findAll();
    }

    // update progession
    public ProgressionLevel updateProgression(Long id, boolean isCurrent) {
        ProgressionLevel level = progressionLevelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progression not found"));
        level.setCurrent(isCurrent);
        return progressionLevelRepository.save(level);
    }
}
