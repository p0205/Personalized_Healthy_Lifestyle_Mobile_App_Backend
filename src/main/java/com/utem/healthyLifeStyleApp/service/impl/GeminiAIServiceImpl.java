package com.utem.healthyLifeStyleApp.service.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utem.healthyLifeStyleApp.dto.RiskAssessmentQuestionDTO;
import com.utem.healthyLifeStyleApp.dto.RiskAssessmentUserDTO;
import com.utem.healthyLifeStyleApp.service.GeminiAIService;
import com.utem.healthyLifeStyleApp.service.RiskAssessmentService;
import com.utem.healthyLifeStyleApp.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GeminiAIServiceImpl implements GeminiAIService{

	private final UserService userService;
	private final RiskAssessmentService riskAssessmentService;

public String filterQuestionsPrompt(Integer userId, Integer healthId) {
    // Get user profile and convert it to a map

    RiskAssessmentUserDTO userDTO = userService.getUserBasicInfoById(userId);
	

    // Fetch questions
    List<RiskAssessmentQuestionDTO> questionDTOs = riskAssessmentService.getQuestionsWithConditionsByTestId(healthId);
    // List<String> questions = questionDTOs.stream()
    //                                      .map(RiskAssessmentQuestionDTO::getQuestion)
    //                                      .collect(Collectors.toList());

    // Construct the prompt
    StringBuilder text = new StringBuilder("You are an intelligent assistant for tailoring health assessments. Analyze the user's profile and the health test questions to optimize the assessment process.\n\n");

    // Add user profile information
    text.append("**User Profile:**\n");
    text.append("{\n");
    text.append("  \"id\": ").append(userDTO.getId()).append(",\n");
    text.append("  \"name\": \"").append(userDTO.getName()).append("\",\n");
    text.append("  \"gender\": \"").append(userDTO.getGender()).append("\",\n");
    text.append("  \"age\": ").append(userDTO.getAge()).append(",\n");
    text.append("  \"weight\": ").append(userDTO.getWeight()).append(",\n");
    text.append("  \"height\": ").append(userDTO.getHeight()).append(",\n");
    text.append("  \"BMI\": ").append(userDTO.getBmi()).append(",\n");
    text.append("  \"occupationType\": \"").append(userDTO.getOccupationType()).append("\",\n");
    text.append("  \"healthHistory\": \"").append(userDTO.getHealthHistory()).append("\"\n");
    text.append("}\n\n");

    // Add questions
    text.append("**Questions:**\n");
    for (RiskAssessmentQuestionDTO question: questionDTOs) {
		text.append("  \"id:\": ").append(question.getId()).append(",\n");
		text.append("  \"question\": \"").append(question.getQuestion()).append("\",\n");
		text.append("  \"conditions\": \"").append("\"\n");
		text.append("  [\n");
		for (int i = 0; i < question.getConditions().size(); i++) {
			text.append("    { \"condition\": \"").append(question.getConditions().get(i).getCondition()).append("\", \"score\": ").append(question.getConditions().get(i).getScore()).append(", \"weight\": ").append(question.getConditions().get(i).getWeight()).append(" }");
			if (i < question.getConditions().size() - 1) {
				text.append(",");
			}
			text.append("\n");
		}
		text.append("}\n\n");
    }

		text.append("\n**Requirements:**\n");
		text.append("- For each question, decide whether to:\n");
		text.append("  1. Answer it using the user's profile data, choose one matched condition from the conditions. (mark as \"isSkipped\": false, \"answer\": (option chosen) and calculate its score based on the score of chosen condition times with its weight).\n");
		text.append("  2. Retain it for user input (mark as \"isSkipped\": false, with \"score\": null).\n");
		text.append("  3. Skip it entirely because it is irrelevant (mark as \"isSkipped\": true, with \"score\": null).\n");
		text.append("- Only irrelevant questions should be marked as skipped (\"isSkipped\": true).\n");
		text.append("- Return the filtered question list with fields: { \"id\", \"isSkipped\", \"score\" }.\n");
		text.append("- Provide a separate field for the calculatedScore (sum of scores for AI-answered questions only).\n");
		text.append("- Return response strictly in JSON format only. Do not include explanations or any text outside of the JSON response.Do not wrap the json codes in JSON markers\n");
		

		text.append("\n**Response Format (JSON only):**\n");
		text.append("{\n");
		text.append("  \"questions\": [\n");
		text.append("    { \"id\": 1, \"isSkipped\": false, \"answer\": \"BMI < 25\", \"score\": 2 },\n");
		text.append("    { \"id\": 2, \"isSkipped\": true, \"answer\": null, \"score\": null },\n");
		text.append("    { \"id\": 3, \"isSkipped\": false, \"answer\": null, \"score\": null }\n");
		text.append("  ],\n");
		text.append("  \"calculatedScore\": 2\n");
		text.append("}\n");

	// return text.toString();
    // URL encode the prompt
    return "?prompt=" + URLEncoder.encode(text.toString(), StandardCharsets.UTF_8);
}



	public String generateRecommendationsPrompt(String testName, int score, String riskLevel, Map<String, Object> userProfile) {
        // Create a JSON payload
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = "";
        try {
            Map<String, Object> payload = Map.of(
                "testDetails", Map.of(
                    "testName", testName,
                    "score", score,
                    "riskLevel", riskLevel
                ),
                "userProfile", userProfile,
                "requirements", Map.of(
                    "actionableRecommendations", "Provide 2-3 actionable recommendations in categories such as Diet, Exercise, or Lifestyle.",
                    "motivationalMessage", "Include a motivational encouragement message to promote positive engagement.",
                    "practicalSuggestions", "Ensure suggestions are practical and personalized to the user profile."
                )
            );

            jsonPayload = objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error encoding JSON payload", e);
        }

		System.out.println("?prompt=" + URLEncoder.encode(jsonPayload, StandardCharsets.UTF_8));
        // Encode the JSON payload for URL
        return  "?prompt=" + URLEncoder.encode(jsonPayload, StandardCharsets.UTF_8); 
    }
}
