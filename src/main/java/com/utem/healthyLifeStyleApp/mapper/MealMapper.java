package com.utem.healthyLifeStyleApp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.utem.healthyLifeStyleApp.dto.MealSearchDTO;
import com.utem.healthyLifeStyleApp.model.Meal;

@Mapper(componentModel = "spring")
public interface MealMapper {
	
	MealSearchDTO toMealSearchDTO(Meal meal);
	public List<MealSearchDTO> toFoodSearchDTOList(List<Meal> meals);
}
