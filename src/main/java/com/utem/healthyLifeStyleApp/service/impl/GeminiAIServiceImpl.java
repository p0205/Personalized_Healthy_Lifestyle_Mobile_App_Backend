package com.utem.healthyLifeStyleApp.service.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.stereotype.Service;

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

    // Construct the prompt
    StringBuilder text = new StringBuilder("You are an intelligent assistant to provide tailored suggestions based on the final risk level:\n\n");

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
        text.append("  1. Answer it using the user's profile data, choose one matched condition from the conditions (mark as \"isSkipped\": false, \"answer\": (option chosen), and calculate its score based on the score of the chosen condition multiplied by its weight).\n");
        text.append("  2. Retain it for user input (mark as \"isSkipped\": false, with \"score\": null).\n");
        text.append("  3. Skip it entirely because it is irrelevant (mark as \"isSkipped\": true, with \"score\": null).\n");
        text.append("- For skipped questions (\"isSkipped\": true), assign a penalty score using this logic:\n");
        text.append("  - Use the median score of the possible conditions for the question.\n");
        text.append("- Include the penalty score in the final calculatedScore.\n");
        text.append("- Only irrelevant questions should be marked as skipped (\"isSkipped\": true).\n");
        text.append("- Return the filtered question list with fields: { \"id\", \"isSkipped\", \"score\" }.\n");
        text.append("- Provide a separate field for the calculatedScore (sum of scores for AI-answered questions plus penalties for skipped questions).\n");
        text.append("- Return response strictly in JSON format only..\n");
        text.append("- MUST NOT wrap the JSON codes in JSON markers\n");

		text.append("\n**Response Format (JSON only):**\n You must not include explanations or any text outside of the JSON response. You must not wrap the JSON codes in JSON markers");
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



	public String generateRecommendationsPrompt(String testName, String riskLevel, Integer userId) {
        // Create a JSON payload
       
        RiskAssessmentUserDTO userDTO = userService.getUserBasicInfoById(userId);
        StringBuilder text = new StringBuilder("You are an intelligent assistant for tailoring health assessments. Analyze the user's profile and the health test questions to optimize the assessment process.\n\n");

        text.append("**User Profile:**\n");
        text.append("{\n");
        text.append("  \"name\": \"").append(userDTO.getName()).append("\",\n");
        text.append("  \"gender\": \"").append(userDTO.getGender()).append("\",\n");
        text.append("  \"age\": ").append(userDTO.getAge()).append(",\n");
        text.append("  \"weight\": ").append(userDTO.getWeight()).append(",\n");
        text.append("  \"height\": ").append(userDTO.getHeight()).append(",\n");
        text.append("  \"BMI\": ").append(userDTO.getBmi()).append(",\n");
        text.append("  \"occupationType\": \"").append(userDTO.getOccupationType()).append("\",\n");
        text.append("  \"healthHistory\": \"").append(userDTO.getHealthHistory()).append("\"\n");
        text.append("}\n\n");

        text.append("**Health Test Information:**\n");
        text.append("{\n");
        text.append("  \"healthTestName\": \"").append(testName).append("\",\n");
        text.append("  \"finalRiskLevel\": \"").append(riskLevel).append("\",\n");
        text.append("}\n\n");

        text.append("**Task:**\n");
        text.append("Analyze the user's profile information and the results of their health test. Based on the final risk level (Low Risk, Moderate Risk, High Risk) and their total score, generate personalized health recommendations. These suggestions should be structured and actionable, covering the following categories: Exercise, Diet, and Health Checkups.\n");
        text.append("- MUST NOT wrap the JSON codes in JSON markers\n");
        // text.append("For each risk level, provide relevant suggestions:\n");
        // text.append("1. **Low Risk:**\n");
        // text.append("   - Exercise: [Provide recommendations based on their current health status.]\n");
        // text.append("   - Diet: [Provide dietary suggestions to maintain good health.]\n");
        // text.append("   - Health Checkups: [Offer guidance on maintaining regular health checkups.]\n");

        // text.append("2. **Moderate Risk:**\n");
        // text.append("   - Exercise: [Suggest lifestyle changes such as increasing physical activity.]\n");
        // text.append("   - Diet: [Recommend dietary changes like reducing sodium intake or increasing fiber.]\n");
        // text.append("   - Health Checkups: [Encourage regular medical checkups to monitor health.]\n");

        // text.append("3. **High Risk:**\n");
        // text.append("   - Exercise: [Provide exercise recommendations focusing on weight management or improving cardiovascular health.]\n");
        // text.append("   - Diet: [Suggest a stricter dietary plan to address issues like high cholesterol, high blood pressure, etc.]\n");
        // text.append("   - Health Checkups: [Recommend seeking medical advice and regularly monitoring critical health parameters.]\n");

        text.append("Provide clear, actionable steps for each category. Make sure the advice is personalized and addresses the user’s health history, current condition, and risk level.\n");

		text.append("\n**Response Format (JSON only):**\n You must not include explanations or any text outside of the JSON response. Do not wrap the JSON codes in JSON markers");
        text.append("{\n");

        text.append("    \"exercise\": \"[Exercise recommendations based on risk level]\",\n");
        text.append("    \"diet\": \"[Dietary suggestions based on risk level]\",\n");
        text.append("    \"healthCheckups\": \"[Health checkup recommendations based on risk level]\"\n");

        text.append("}\n");

        // Encode the JSON payload for URL
        return  "?prompt=" + URLEncoder.encode(text.toString(), StandardCharsets.UTF_8); 
    }
}
