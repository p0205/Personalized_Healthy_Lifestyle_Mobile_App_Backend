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
public class FilteredHeatlhTestDTO {

    private List<RiskAssessmentQuestionDTO> questions;
    private Integer calculatedScore;
}
