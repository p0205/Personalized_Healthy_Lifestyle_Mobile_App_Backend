package com.utem.healthyLifeStyleApp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.utem.healthyLifeStyleApp.dto.MealSearchDTO;
import com.utem.healthyLifeStyleApp.dto.UserMealDTO;
import com.utem.healthyLifeStyleApp.model.Meal;
import com.utem.healthyLifeStyleApp.model.MealType;

public interface MealService {

	public Meal addMeal(Meal meal);
	public List<MealSearchDTO> getMatchingFoodList(String name);
	public Meal getMealById(Integer id);
	public UserMealDTO addUserMeal(UserMealDTO userMealDTO);
	public UserMealDTO getUserMeal(Integer userId, Integer mealId);
	public void deleteUserMeal(Integer userId, Integer mealId);
	public Map<MealType, List<UserMealDTO>> getMealsByDateGroupedByType(Integer userId,LocalDate date);
}
