package com.utem.healthyLifeStyleApp.controller;

import java.util.List;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utem.healthyLifeStyleApp.dto.AIResponseDTO;
import com.utem.healthyLifeStyleApp.dto.UserHealthTestStatus;
import com.utem.healthyLifeStyleApp.dto.UserScoreDTO;
import com.utem.healthyLifeStyleApp.service.GeminiAIService;
import com.utem.healthyLifeStyleApp.service.RiskAssessmentService;

import io.grpc.StatusRuntimeException;
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

    // @GetMapping("/riskAssessment/test")
	// public ResponseEntity<List<HealthTest>> getAllTestType() {
	// 	return ResponseEntity.ok(riskAssessmentService.getAllTestType());
	// }

    @GetMapping("/riskAssessment/test/{userId}")
	public ResponseEntity<List<UserHealthTestStatus>> getUserHealthTestStatus(@PathVariable("userId") Integer userId) {

        return ResponseEntity
        .ok(riskAssessmentService.getUserHealthTestStatus(userId));

	}

	
	@GetMapping("/ai/filter-questions/{userId}/{healthId}")
	public ResponseEntity<?> filterQuestion(@PathVariable("userId") Integer userId, @PathVariable("healthId") Integer healthId) {

		ObjectMapper objectMapper = new ObjectMapper();
		String encodedPrompt = geminiAIService.filterQuestionsPrompt(userId, healthId);

		

		try {
            // Send the request to the AI client and return the response
            String response =  chatClient
            .prompt(encodedPrompt)
            .call()
            .content();
            AIResponseDTO aiResponse;
            try {
                aiResponse = objectMapper.readValue(response, AIResponseDTO.class);
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body("Error processing AI response.");
            }
            // System.out.println("response: " + aiResponse.toString());
            return ResponseEntity.ok()
                     .header(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8")
                     .body(riskAssessmentService.processAIResponse(aiResponse));
            } catch (StatusRuntimeException e) {
           
            // Handle resource exhaustion error (quota exceeded)
            if (e.getStatus().getCode() == io.grpc.Status.Code.RESOURCE_EXHAUSTED) {
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                                    .body("Quota exceeded for AI platform. Please try again later.");
            }
            
            // Handle any other general errors
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                                .body("Error generating content. Please try again later.");
        } catch (RuntimeException e) {
          
            if (e.getMessage().contains("Failed to generate content")) {
              
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                                    .body("Quota exceeded for AI platform. Please try again later.");
            }

            // Handle any other unexpected runtime exceptions
           
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Unexpected error occurred. Please try again later.");
        
        }
	}

	// @GetMapping("/riskLevel/{healthTestId}/{score}")
	// public ResponseEntity<String> getRiskLevelsByHealthTestId(@PathVariable ("healthTestId") Integer healthTestId, @PathVariable("score") double score) {
        
	// 	return ResponseEntity.ok(riskAssessmentService.determineRiskLevel(score, healthTestId));
	// }

    @GetMapping("/ai/suggestions/{userId}/{healthTestId}")
	public ResponseEntity<String> getRiskLevelsByHealthTestId( @PathVariable("userId") Integer userId,@PathVariable("healthTestId") Integer healthTestId, @RequestParam int score) {
        String riskLevel = riskAssessmentService.determineRiskLevel(score, healthTestId);
        String testName = riskAssessmentService.getHealthTestById(healthTestId).getDiseaseName();
        String encodedPrompt = geminiAIService.generateRecommendationsPrompt(testName, riskLevel, userId);
        
        try {
        String response =  chatClient
                            .prompt(encodedPrompt)
                            .call()
                            .content();
        //save to database
        
        riskAssessmentService.saveUserScore(userId, healthTestId, score, response);
        return ResponseEntity.ok(response);
        } catch (StatusRuntimeException e) {
          
        // Handle resource exhaustion error (quota exceeded)
        if (e.getStatus().getCode() == io.grpc.Status.Code.RESOURCE_EXHAUSTED) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                                 .body("Quota exceeded for AI platform. Please try again later.");
        }
        
        // Handle any other general errors
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                             .body("Error generating content. Please try again later.");
    } catch (RuntimeException e) {
        if (e.getMessage().contains("Failed to generate content")) {
           
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                                 .body("Quota exceeded for AI platform. Please try again later.");
        }

        // Handle any other unexpected runtime exceptions
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Unexpected error occurred. Please try again later.");
    
    }
		
	}

    @GetMapping("/riskAssessment/{userId}/{healthTestId}")
    public ResponseEntity<UserScoreDTO> getUserScore(@PathVariable Integer userId, @PathVariable Integer healthTestId) {
        UserScoreDTO userScore = riskAssessmentService.getUserScore(userId, healthTestId);
        if (userScore == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userScore);
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




}
