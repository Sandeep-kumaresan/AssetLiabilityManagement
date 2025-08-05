package com.oracle.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "scenarios")
@Data
public class Scenarios {

    @Id
    @Column(name = "scenario_id", nullable = false)
    private String scenarioId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "impact_factor")
    private double impactFactor;

    @Column(name = "scenario_type")
    private String scenarioType;

    @Column(name = "execution_date")
    private LocalDate executionDate;

    @Column(name = "affected_entity_type")
    private String affectedEntityType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
