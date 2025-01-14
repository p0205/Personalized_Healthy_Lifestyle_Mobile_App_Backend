package com.utem.healthyLifeStyleApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserScore {

    @EmbeddedId
    private UserHealthTestKey id;

    private Integer score;

    private String riskLevel;

    @Column(name = "health_checkups", columnDefinition = "TEXT")
    private String healthCheckups; // Store the List<String> as a JSON string

    @Column(name = "exercise_suggestions", columnDefinition = "TEXT")
    private String exerciseSuggestions;

    @Column(name = "diet_suggestions", columnDefinition = "TEXT")
    private String dietSuggestions;

}
