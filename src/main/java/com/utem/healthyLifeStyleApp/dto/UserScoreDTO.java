package com.utem.healthyLifeStyleApp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserScoreDTO {

    private Integer id;

    private Integer userId;

    private Integer healthTestId;

    private Integer score;

    private String riskLevel;

    private List<String> healthCheckups;

    private List<String> exerciseSuggestions;

    private List<String> dietSuggestions;

}
