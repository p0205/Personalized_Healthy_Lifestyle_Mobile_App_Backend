package com.utem.healthyLifeStyleApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.utem.healthyLifeStyleApp.model.RiskAssessmentQuestion;

@Repository
public interface RiskAssessmentQuestionRepo extends JpaRepository<RiskAssessmentQuestion,Integer>{

    
    List<RiskAssessmentQuestion> findByTest_Id(Integer healthTestId);

    @Query("SELECT q FROM RiskAssessmentQuestion q " +
           "JOIN FETCH q.test t " +
           "LEFT JOIN FETCH q.scoringRules sr " +
           "WHERE t.id = :healthTestId")
    List<RiskAssessmentQuestion> findQuestionsByHealthTestId(@Param("healthTestId") Integer healthTestId);
}
