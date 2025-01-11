package com.utem.healthyLifeStyleApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id",nullable = false)
    @JsonIgnore
	private HealthTest healthTest;

    private Integer score;

    private String riskLevel;

    @Column(name = "health_checkups", columnDefinition = "TEXT")
    private String healthCheckups; // Store the List<String> as a JSON string

    @Column(name = "exercise_suggestions", columnDefinition = "TEXT")
    private String exerciseSuggestions;

    @Column(name = "diet_suggestions", columnDefinition = "TEXT")
    private String dietSuggestions;

}
