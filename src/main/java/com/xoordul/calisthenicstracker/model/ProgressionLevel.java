package com.xoordul.calisthenicstracker.model;

import ch.qos.logback.core.joran.spi.DefaultClass;
import jakarta.persistence.*;
import javafx.beans.DefaultProperty;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * Author: Rico Krenn
 * Created: 6/2/2026
 * Version:
 * Description:
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
}
