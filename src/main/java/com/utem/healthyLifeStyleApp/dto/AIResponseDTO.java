package com.utem.healthyLifeStyleApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIResponseDTO {

    private List<Question> questions;
    private Integer calculatedScore;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Question {
        private Integer id;
        private Boolean isSkipped;
        private String answer;
        private Integer score; // Score if the question is automatically answered
    }
}
