package com.utem.healthyLifeStyleApp.service;

import java.util.List;

import com.utem.healthyLifeStyleApp.dto.AIResponseDTO;
import com.utem.healthyLifeStyleApp.dto.FilteredHeatlhTestDTO;
import com.utem.healthyLifeStyleApp.dto.RiskAssessmentQuestionDTO;
import com.utem.healthyLifeStyleApp.model.RiskLevel;

public interface RiskAssessmentService {
    

    // public HealthTest getHealthTestById(Integer id);

    // public List<RiskAssessmentQuestion> getRiskAssessmentQuestionByTestId(Integer testId);

    // public List<RiskAssessmentScoringRules> getRiskAssessmentScoringRulesByQuestionId(Integer questionId);

     public List<RiskAssessmentQuestionDTO> getQuestionsWithConditionsByTestId(Integer healthTestId);

    public FilteredHeatlhTestDTO processAIResponse(AIResponseDTO aiResponse);

     public List<RiskLevel> getRiskLevelsByHealthTestId(Integer healthTestId);

     public String determineRiskLevel(double score, Integer healthTestId);

     
}
