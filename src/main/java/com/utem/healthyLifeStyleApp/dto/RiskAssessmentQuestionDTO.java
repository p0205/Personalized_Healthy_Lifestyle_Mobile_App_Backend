package com.utem.healthyLifeStyleApp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RiskAssessmentQuestionDTO {
    private Integer id;
    private String question;
    private List<RiskAssessmentConditionDTO> conditions;
}
