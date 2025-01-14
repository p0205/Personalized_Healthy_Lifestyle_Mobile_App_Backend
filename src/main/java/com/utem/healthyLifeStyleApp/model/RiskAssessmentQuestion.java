package com.utem.healthyLifeStyleApp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskAssessmentQuestion {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String question;

    @ManyToOne
    @JoinColumn(name = "health_test_id",nullable = false)
    private HealthTest test;

    @OneToOne
    @JoinColumn(name = "FOLLOWUP_QUESTION")
    private RiskAssessmentQuestion followUpQuestion;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<RiskAssessmentScoringRules> scoringRules;
}
