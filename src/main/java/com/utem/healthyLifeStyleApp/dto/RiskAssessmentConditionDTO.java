package com.utem.healthyLifeStyleApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RiskAssessmentConditionDTO {
    private String condition;
    private Integer score;
    private Integer weight;

}
