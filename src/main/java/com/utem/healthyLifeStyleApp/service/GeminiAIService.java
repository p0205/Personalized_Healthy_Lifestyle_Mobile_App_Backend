package com.utem.healthyLifeStyleApp.service;


import java.util.Map;

public interface GeminiAIService {

    public String filterQuestionsPrompt(Integer userId, Integer healthId);
    public String generateRecommendationsPrompt(String testName, int score, String riskLevel, Map<String, Object> userProfile);
      
}
