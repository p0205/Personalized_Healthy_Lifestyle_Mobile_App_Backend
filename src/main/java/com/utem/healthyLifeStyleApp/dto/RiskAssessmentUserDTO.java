package com.utem.healthyLifeStyleApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RiskAssessmentUserDTO {
    private Integer id;
	private String name;
	private char gender;
	private Integer age;
	private Double weight;
	private Double height;
    private Double bmi;
    private String occupationType;
	private String healthHistory;
}
