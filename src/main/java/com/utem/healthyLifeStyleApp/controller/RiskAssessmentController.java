package com.utem.healthyLifeStyleApp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utem.healthyLifeStyleApp.dto.AIResponseDTO;
import com.utem.healthyLifeStyleApp.dto.FilteredHeatlhTestDTO;
import com.utem.healthyLifeStyleApp.model.RiskLevel;
import com.utem.healthyLifeStyleApp.service.GeminiAIService;
import com.utem.healthyLifeStyleApp.service.RiskAssessmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class RiskAssessmentController {

	// @Qualifier("openaiRestTemplate")
	// @Autowired
	// private RestTemplate restTemplate;



	// private GeminiAIService geminiAIService;

	private final RiskAssessmentService riskAssessmentService;
	private final GeminiAIService geminiAIService;
	private final ChatClient chatClient;



	
	@GetMapping("/ai/filter-questions/{userId}/{healthId}")
	public ResponseEntity<FilteredHeatlhTestDTO> filterQuestion(@PathVariable("userId") Integer userId, @PathVariable("healthId") Integer healthId) {

		ObjectMapper objectMapper = new ObjectMapper();
		String encodedPrompt = geminiAIService.filterQuestionsPrompt(userId, healthId);

		// Send the request to the AI client and return the response
		String response =  chatClient
				.prompt(encodedPrompt)
				.call()
				.content();

		try {
			AIResponseDTO aiResponse = objectMapper.readValue(response, AIResponseDTO.class);
			return ResponseEntity.ok(riskAssessmentService.processAIResponse(aiResponse));
		} catch (Exception e) {
			throw new RuntimeException("Error parsing AI response", e);
		}
	}

	@GetMapping("/riskLevel/{healthTestId}/{score}")
	public ResponseEntity<String> getRiskLevelsByHealthTestId(@PathVariable ("healthTestId") Integer healthTestId, @PathVariable("score") double score) {
		return ResponseEntity.ok(riskAssessmentService.determineRiskLevel(score, healthTestId));
	}

    @GetMapping("/ai/suggestions/{userId}")
	public ResponseEntity<String> getRiskLevelsByHealthTestId( @PathVariable("userId") Integer userId, @RequestParam String testName) {
        String encodedPrompt = geminiAIService.generateRecommendationsPrompt(testName, "high", userId);
        String response =  chatClient
                            .prompt(encodedPrompt)
                            .call()
                            .content();
		return ResponseEntity.ok(response);
	}

	//  @GetMapping("/questions/{healthTestId}")
    // public ResponseEntity<List<RiskAssessmentQuestionDTO>> getQuestionsByHealthTestId(@PathVariable Integer healthTestId) {
    //     List<RiskAssessmentQuestionDTO> questions = riskAssessmentService.getQuestionsWithConditionsByTestId(healthTestId);
    //     if (questions.isEmpty()) {
    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.ok(questions);
    // }

    // @GetMapping("/health-test/{id}")
    // public ResponseEntity<HealthTest> getHealthTestById(@PathVariable Integer id) {
    //     HealthTest healthTest = riskAssessmentService.getHealthTestById(id);
    //     if (healthTest == null) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(healthTest);
    // }


    // @GetMapping("/questions/{testId}")
    // public ResponseEntity<List<RiskAssessmentQuestion>> getRiskAssessmentQuestionsByTestId(@PathVariable Integer testId) {
    //     List<RiskAssessmentQuestion> questions = riskAssessmentService.getRiskAssessmentQuestionByTestId(testId);
    //     if (questions.isEmpty()) {
    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.ok(questions);
    // }

    // @GetMapping("/scoring-rules/{questionId}")

    // public ResponseEntity<List<RiskAssessmentScoringRules>> getRiskAssessmentScoringRulesByQuestionId(@PathVariable Integer questionId) {
    //     List<RiskAssessmentScoringRules> scoringRules = riskAssessmentService.getRiskAssessmentScoringRulesByQuestionId(questionId);
    //     if (scoringRules.isEmpty()) {
    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.ok(scoringRules);
    // }



	@GetMapping("/ai/suggestions/{testName}/{score}/{riskLevel}")
	public String generateRecommendations(String testName, int score, List<String> riskLevel, Map<String, Object> userProfile) {
        // // Create a JSON payload
        // ObjectMapper objectMapper = new ObjectMapper();
        // String jsonPayload = "";
        // try {
        //     Map<String, Object> payload = Map.of(
        //         "testDetails", Map.of(
        //             "testName", testName,
        //             "score", score,
        //             "riskLevel", riskLevel
        //         ),
        //         "userProfile", userProfile,
        //         "requirements", Map.of(
        //             "actionableRecommendations", "Provide 2-3 actionable recommendations in categories such as Diet, Exercise, or Lifestyle.",
        //             "motivationalMessage", "Include a motivational encouragement message to promote positive engagement.",
        //             "practicalSuggestions", "Ensure suggestions are practical and personalized to the user profile."
        //         )
        //     );

        //     jsonPayload = objectMapper.writeValueAsString(payload);
        // } catch (JsonProcessingException e) {
        //     throw new RuntimeException("Error encoding JSON payload", e);
        // }

    //     // Encode the JSON payload for URL
    //     String encodedPrompt = "?prompt=" + URLEncoder.encode(jsonPayload, StandardCharsets.UTF_8);

    //     // Call the AI service and return the response
    //     return chatClient
    //             .prompt(geminiAIService.generateRecommendationsPrompt())
    //             .call()
    //            .content();
	return null;
    }


}
