package com.xoordul.calisthenicstracker.model;

import ch.qos.logback.core.joran.spi.DefaultClass;
import jakarta.persistence.*;
import javafx.beans.DefaultProperty;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * Author: Rico Krenn
 * Created: 6/2/2026
 * Version: 1.1
 * Description: ProgressionLevel model
 * Project: 200_Abschlussprojekt
 */
// @Entity and @Table are to create the Entity and Name
@Entity
@Table(name = "progression_level")
public class ProgressionLevel {
    // Here is determined that id is the PK and is set to AI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Here is the FK exercise_id defined
    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    // Attribut name with datatype string
    private String name;

    // Attribut orderIndex with datatype int
    private int orderIndex;

    // isCurrent is default set to false
    private boolean isCurrent = false;

    // Getter and Setter for ProgressionLevel
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}
