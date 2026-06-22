package com.xoordul.calisthenicstracker.model;

/**
 * Author: Rico Krenn
 * Created: 6/2/2026
 * Version: 1.0
 * Description: Exercise model
 * Project: 200_Abschlussprojekt
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// @Entity and @Table are to create the Entity and Name
@Entity
@Table(name = "exercise")
public class Exercise {

    // Here is determined that id is the PK and is set to AI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attribut name is set unique
    @Column(unique = true)
    private String name;

    // OneToMany so that Exercise has knowledge of its ProgressionLevel children
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProgressionLevel> progressionLevels = new ArrayList<>();

    // Attribut muscleGroup with data type String
    private String muscleGroup;

    // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Setter for muscleGroup
    public String getMuscleGroup() {
        return muscleGroup;
    }

    // Setter for muscleGroup
    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    // Getter for ProgressionLevels
    public List<ProgressionLevel> getProgressionLevels() {
        return progressionLevels;
    }

    // Setter for ProgressionLevels
    public void setProgressionLevels(List<ProgressionLevel> progressionLevels) {
        this.progressionLevels = progressionLevels;
    }
}
