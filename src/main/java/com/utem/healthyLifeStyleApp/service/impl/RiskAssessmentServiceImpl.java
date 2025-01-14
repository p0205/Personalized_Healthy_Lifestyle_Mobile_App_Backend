package com.utem.healthyLifeStyleApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utem.healthyLifeStyleApp.dto.AIResponseDTO;
import com.utem.healthyLifeStyleApp.dto.FilteredHeatlhTestDTO;
import com.utem.healthyLifeStyleApp.dto.RiskAssessmentConditionDTO;
import com.utem.healthyLifeStyleApp.dto.RiskAssessmentQuestionDTO;
import com.utem.healthyLifeStyleApp.dto.UserHealthTestStatus;
import com.utem.healthyLifeStyleApp.dto.UserScoreDTO;
import com.utem.healthyLifeStyleApp.model.HealthTest;
import com.utem.healthyLifeStyleApp.model.RiskAssessmentQuestion;
import com.utem.healthyLifeStyleApp.model.RiskLevel;
import com.utem.healthyLifeStyleApp.model.UserHealthTestKey;
import com.utem.healthyLifeStyleApp.model.UserScore;
import com.utem.healthyLifeStyleApp.repo.HealthTestRepo;
import com.utem.healthyLifeStyleApp.repo.RiskAssessmentQuestionRepo;
import com.utem.healthyLifeStyleApp.repo.RiskLevelRepo;
import com.utem.healthyLifeStyleApp.repo.UserScoreRepo;
import com.utem.healthyLifeStyleApp.service.RiskAssessmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RiskAssessmentServiceImpl implements RiskAssessmentService{

    // private final HealthTestRepo healthTestRepo;
    private final RiskAssessmentQuestionRepo riskAssessmentQuestionRepo;
    private final RiskLevelRepo riskLevelRepo;
    private final HealthTestRepo healthTestRepo;
    private final UserScoreRepo userScoreRepo;
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

    @Override
    public List<HealthTest> getAllTestType() {
        return healthTestRepo.findAll();
    }

    @Override
    public HealthTest getHealthTestById(Integer healthTestId) {
       return healthTestRepo.findById(healthTestId).orElse(null);
    }

    @Override
    public void saveUserScore(Integer userId, Integer healthTestId, int score, String response) {
        try {
            // Assuming response is a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            
            String riskLevelFromResponse = rootNode.path("riskLevel").asText();
            JsonNode suggestionsNode = rootNode.path("suggestions");

            // Deserialize exercise, diet, and health checkups suggestions
            List<String> exerciseSuggestions = parseSuggestions(suggestionsNode, "exercise");
            List<String> dietSuggestions = parseSuggestions(suggestionsNode, "diet");
            List<String> healthCheckups = parseSuggestions(suggestionsNode, "healthCheckups");


            // Get the User and HealthTest entities
            // User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            // HealthTest healthTest = healthTestRepo.findById(healthTestId).orElseThrow(() -> new RuntimeException("HealthTest not found"));

            UserHealthTestKey id = UserHealthTestKey.builder()
                                    .userId(userId)
                                    .healthTestId(healthTestId)
                                    .build();
            // Create the UserScore entity
            UserScore userScore = UserScore.builder()
                    .id(id)     
                    .score(score)
                    .riskLevel(riskLevelFromResponse)
                    .healthCheckups(objectMapper.writeValueAsString(healthCheckups)) // Save List<String> as JSON string
                    .exerciseSuggestions(objectMapper.writeValueAsString(exerciseSuggestions)) // Save List<String> as JSON string
                    .dietSuggestions(objectMapper.writeValueAsString(dietSuggestions)) // Save List<String> as JSON string
                    .build();

            // Save the UserScore entity to the database
            userScoreRepo.save(userScore);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving data", e);
        }
    }
    // Helper method to handle both array and string cases
    private List<String> parseSuggestions(JsonNode suggestionsNode, String key) {
        JsonNode node = suggestionsNode.path(key);
        List<String> suggestions = new ArrayList<>();
        
        if (node.isArray()) {
            // Deserialize JSON array to List<String>
            try {
                suggestions = new ObjectMapper().readValue(node.toString(), new TypeReference<List<String>>() {});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (node.isTextual()) {
            // If it's a string, add it to the list as a single item
            suggestions.add(node.asText());
        }
        
        return suggestions;
    }

    @Override
    public UserScoreDTO getUserScore(Integer userId, Integer healthTestId) {
        UserScore userScore = userScoreRepo.findByIdUserIdAndIdHealthTestId(userId, healthTestId);
        if(userScore == null)
            return null;
        
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<String> healthCheckups = objectMapper.readValue(userScore.getHealthCheckups(), new TypeReference<List<String>>() {});
            List<String> exerciseSuggestions = objectMapper.readValue(userScore.getExerciseSuggestions(), new TypeReference<List<String>>() {});
            List<String> dietSuggestions = objectMapper.readValue(userScore.getDietSuggestions(), new TypeReference<List<String>>() {});

            Map<String,List<String>> suggestions = Map.of(
                "healthCheckups", healthCheckups,
                "exercise", exerciseSuggestions,
                "diet", dietSuggestions
            );
            return UserScoreDTO.builder()
                    .userId(userScore.getId().getUserId())
                    .healthTestId(userScore.getId().getHealthTestId())
                    .score(userScore.getScore())
                    .riskLevel(userScore.getRiskLevel())
                    .suggestions(suggestions)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error parsing data", e);
        }
    }

    @Override
    public List<UserHealthTestStatus> getUserHealthTestStatus(Integer userId) {
        List<HealthTest> healthTests = healthTestRepo.findAll();
        List<UserScore> userScores = userScoreRepo.findByIdUserId(userId);
        List<UserHealthTestStatus> userHealthTestStatus = new ArrayList<>();


        for (HealthTest test : healthTests) {
            boolean completed = userScores.stream()
                .anyMatch(userScore -> userScore.getId().getHealthTestId().equals(test.getId()));
        userHealthTestStatus.add(new UserHealthTestStatus(test, completed));
        }
        return userHealthTestStatus;
    }

}
