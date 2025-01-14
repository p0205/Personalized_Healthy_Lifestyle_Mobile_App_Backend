package com.utem.healthyLifeStyleApp.model;

import java.io.Serializable;

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
public class RiskLevel implements Serializable{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_test_id",nullable = false)
    @JsonIgnore
    private HealthTest healthTest;

    @Column(nullable = false)
    private String RiskLevel;
    
    @Column(nullable = false)
    private Integer scoreMin;

    @Column(nullable = false)
    private Integer scoreMax;
    
    private String description;

}
