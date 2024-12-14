package com.utem.healthyLifeStyleApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MealSearchDTO {

	private Integer id;
	private String name;
	private Double energyPer100g;
}
