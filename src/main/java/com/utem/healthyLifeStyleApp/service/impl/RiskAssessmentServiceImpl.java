package com.utem.healthyLifeStyleApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.utem.healthyLifeStyleApp.dto.AIResponseDTO;
import com.utem.healthyLifeStyleApp.dto.FilteredHeatlhTestDTO;
import com.utem.healthyLifeStyleApp.dto.RiskAssessmentConditionDTO;
import com.utem.healthyLifeStyleApp.dto.RiskAssessmentQuestionDTO;
import com.utem.healthyLifeStyleApp.model.RiskAssessmentQuestion;
import com.utem.healthyLifeStyleApp.model.RiskLevel;
import com.utem.healthyLifeStyleApp.repo.RiskAssessmentQuestionRepo;
import com.utem.healthyLifeStyleApp.repo.RiskLevelRepo;
import com.utem.healthyLifeStyleApp.service.RiskAssessmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RiskAssessmentServiceImpl implements RiskAssessmentService{

    // private final HealthTestRepo healthTestRepo;
    private final RiskAssessmentQuestionRepo riskAssessmentQuestionRepo;
    private final RiskLevelRepo riskLevelRepo;
    // private final RiskAssessmentScoringRulesRepo riskAssessmentScoringRulesRepo;
    
    // @Override
    // public HealthTest getHealthTestById(Integer id) {
    //     Optional<HealthTest> healthTest = healthTestRepo.findById(id);
    //     if(healthTest.isEmpty())
    //         return null;
    //     return healthTest.get();
    // }

    // @Override
    // public List<RiskAssessmentQuestion> getRiskAssessmentQuestionByTestId(Integer testId) {
    //     return riskAssessmentQuestionRepo.findByTest_Id(testId);
    // }

    // @Override
    // public List<RiskAssessmentScoringRules> getRiskAssessmentScoringRulesByQuestionId(Integer questionId) {
    //    return riskAssessmentScoringRulesRepo.findByQuestion_Id(questionId);
    // }

    @Override
    public List<RiskAssessmentQuestionDTO> getQuestionsWithConditionsByTestId(Integer healthTestId) {
        List<RiskAssessmentQuestion> questions = riskAssessmentQuestionRepo.findQuestionsByHealthTestId(healthTestId);

        return questions.stream()
                .map(question -> {
                    List<RiskAssessmentConditionDTO> conditions = question.getScoringRules().stream()
                            .map(rule -> new RiskAssessmentConditionDTO(rule.getCondition(), rule.getScore(), rule.getWeight()))
                            .collect(Collectors.toList());

                    return new RiskAssessmentQuestionDTO(question.getId(), question.getQuestion(), conditions);
                })
                .collect(Collectors.toList());
    }

    private RiskAssessmentQuestion getQuestionById(Integer questionId){
        Optional<RiskAssessmentQuestion> question = riskAssessmentQuestionRepo.findById(questionId);
        if(question.isEmpty())
            return null;
        return question.get();
    }
    public RiskAssessmentQuestionDTO getQuestionsWithConditionsByQuesId(Integer questionId) {
        RiskAssessmentQuestion question = getQuestionById(questionId);

        List<RiskAssessmentConditionDTO> conditions = question.getScoringRules().stream()
                .map(rule -> new RiskAssessmentConditionDTO(rule.getCondition(), rule.getScore(), rule.getWeight()))
                .collect(Collectors.toList());

            return new RiskAssessmentQuestionDTO(question.getId(), question.getQuestion(), conditions);
        
    }

    @Override
    public List<RiskLevel> getRiskLevelsByHealthTestId(Integer healthTestId) {
        return riskLevelRepo.findByHealthTest_Id(healthTestId);
    }

    public String determineRiskLevel(double score, Integer healthTestId) {
        List<RiskLevel> riskLevels = getRiskLevelsByHealthTestId(healthTestId);
        // Compare the score with predefined risk levels
        for (RiskLevel level : riskLevels) {
            if (score >= level.getScoreMin() && score <= level.getScoreMax()) {
                return level.getRiskLevel();
            }
        }
        return "Unknown Risk Level";
    }



    public FilteredHeatlhTestDTO processAIResponse(AIResponseDTO aiResponse) {

        // Save the calculatedScore from AI response
        Integer calculatedScore = aiResponse.getCalculatedScore();
        // userTestSessionRepository.saveScore(userId, testId, calculatedScore);

        // Get questions needing user input
        List<RiskAssessmentQuestionDTO> filteredQuestions = aiResponse.getQuestions().stream()
                                                                    .filter(q -> !q.getIsSkipped() && q.getAnswer() == null) // Only questions for user input
                                                                    .map(q -> getQuestionsWithConditionsByQuesId(q.getId())) // Call method for each question ID
                                                                    .collect(Collectors.toList()); // Collect results into a list

        FilteredHeatlhTestDTO dto = FilteredHeatlhTestDTO.builder()
                                    .calculatedScore(calculatedScore)
                                    .questions(filteredQuestions)
                                    .build();
        return dto;
    }

    // public static final String WRITE_ME_HAIKU_ABOUT_CAT = """
    //     Write me Haiku about cat,
    //     haiku should start with the word cat obligatory""";

    // private final AiClient aiClent;
    

}
