package com.utem.healthyLifeStyleApp.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserScoreDTO {

    private Integer userId;

    private Integer healthTestId;

    private Integer score;

    private String riskLevel;

    private Map<String, List<String>> suggestions;

}
