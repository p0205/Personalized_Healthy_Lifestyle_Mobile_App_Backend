package com.utem.healthyLifeStyleApp.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.utem.healthyLifeStyleApp.dto.MealSearchDTO;
import com.utem.healthyLifeStyleApp.dto.UserMealDTO;
import com.utem.healthyLifeStyleApp.mapper.MealMapper;
import com.utem.healthyLifeStyleApp.mapper.UserMapper;
import com.utem.healthyLifeStyleApp.mapper.UserMealMapper;
import com.utem.healthyLifeStyleApp.model.Meal;
import com.utem.healthyLifeStyleApp.model.MealType;
import com.utem.healthyLifeStyleApp.model.UserMeal;
import com.utem.healthyLifeStyleApp.repo.MealRepo;
import com.utem.healthyLifeStyleApp.repo.UserMealRepo;
import com.utem.healthyLifeStyleApp.service.MealService;
import com.utem.healthyLifeStyleApp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService{
	
	final private MealRepo mealRepo;
	
	final private UserMealRepo userMealRepo;
	
	final private MealMapper mealMapper;

	final private UserMealMapper userMealMapper;

	final private UserService userService;

	final private UserMapper userMapper;

	@Override
	public List<MealSearchDTO> getMatchingFoodList(String name) {
		return mealMapper.toFoodSearchDTOList(mealRepo.findByNameLike("%"+name+"%"));
	}

	@Override
	public Meal getMealById(Integer id) {
		
		Optional<Meal> food = mealRepo.findById(id);
		if(food.isEmpty())
			return null;
		return food.get();
	}

	@Override
	public Meal addMeal(Meal meal) {
		return mealRepo.save(meal);
	}

	@Override
	public UserMealDTO getUserMeal(Integer userId, Integer mealId) {
		return userMealMapper.toDto(userMealRepo.findByUserIdAndMealId(userId, mealId));
	}

	@Override
	public void deleteUserMeal(Integer userId, Integer mealId) {
		UserMeal userMeal = userMealRepo.findByUserIdAndMealId(userId,mealId);
		userMealRepo.delete(userMeal);
	}

	@Override
	public UserMealDTO addUserMeal(UserMealDTO dto) {

        UserMeal userMeal = UserMeal.builder()
				.user(userMapper.fromDTO(userService.getUserById(dto.getUserId())))
				.meal(getMealById(dto.getMealId()))
				.mealType(dto.getMealType())
				.calories(dto.getCalories())
				.amountInGrams(dto.getAmountInGrams())
				.carbsInGrams(dto.getCarbsInGrams())
				.proteinInGrams(dto.getProteinInGrams())
				.fatInGrams(dto.getFatInGrams())
				.date(LocalDate.now())
				.build();
		
		return userMealMapper.toDto(userMealRepo.save(userMeal));
	}

	  public Map<MealType, List<UserMealDTO>> getMealsByDateGroupedByType(Integer userId,LocalDate date) {
        List<UserMeal> userMeals = userMealRepo.findByUserIdAndDateOrderByMealType(userId,date);

        // Group meals by MealType
        return userMeals.stream()
                .map(userMeal -> userMealMapper.toDto(userMeal))
                .collect(Collectors.groupingBy(UserMealDTO::getMealType));
    }

}
