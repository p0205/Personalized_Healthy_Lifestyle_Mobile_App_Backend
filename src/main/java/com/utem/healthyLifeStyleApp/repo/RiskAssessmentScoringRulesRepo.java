package com.utem.healthyLifeStyleApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utem.healthyLifeStyleApp.model.RiskAssessmentScoringRules;

@Repository
public interface RiskAssessmentScoringRulesRepo extends JpaRepository<RiskAssessmentScoringRules,Integer>{
  
    List<RiskAssessmentScoringRules> findByQuestion_Id(Integer questionId);

}
