package com.utem.healthyLifeStyleApp.dto;

import java.time.LocalDate;

import com.utem.healthyLifeStyleApp.model.MealType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserMealDTO {
	
	private MealType mealType;
	private Integer userId;
	private Integer mealId;
	private String mealName;
	private LocalDate date;

	private Double amountInGrams;
	private Double calories;
	private Double carbsInGrams;
	private Double proteinInGrams;
	private Double fatInGrams;
	
}
