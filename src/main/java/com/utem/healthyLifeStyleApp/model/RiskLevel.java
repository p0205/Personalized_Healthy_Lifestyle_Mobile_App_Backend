package com.utem.healthyLifeStyleApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class RiskLevel {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "health_test_id",nullable = false)
    private HealthTest test;

    @Column(nullable = false)
    private String RiskLevel;
    
    @Column(nullable = false)
    private Integer scoreMin;

    @Column(nullable = false)
    private Integer scoreMax;
    
    private String description;

}
