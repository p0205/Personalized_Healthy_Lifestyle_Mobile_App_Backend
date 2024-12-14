package com.utem.healthyLifeStyleApp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utem.healthyLifeStyleApp.dto.UserMealDTO;
import com.utem.healthyLifeStyleApp.dto.MealSearchDTO;
import com.utem.healthyLifeStyleApp.dto.NutritionalSummaryDTO;
import com.utem.healthyLifeStyleApp.model.Meal;
import com.utem.healthyLifeStyleApp.model.MealType;
import com.utem.healthyLifeStyleApp.service.MealService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/meal")
@RequiredArgsConstructor
public class MealController {

	final private MealService mealService;
	
	@PostMapping
	public ResponseEntity<Meal> addMeal(@RequestBody Meal meal){
		return ResponseEntity.status(HttpStatus.CREATED).body(mealService.addMeal(meal));
	}

	@GetMapping("/search")
	public ResponseEntity<List<MealSearchDTO>> getMatchingMealList(@RequestParam String name){
		return ResponseEntity.status(HttpStatus.OK).body(mealService.getMatchingFoodList(name));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Meal> getMealById(@PathVariable("id") Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(mealService.getMealById(id));
	}
	
	@PostMapping("/user")
	public ResponseEntity<UserMealDTO> addUserMeal(@RequestBody UserMealDTO request){
		return ResponseEntity.status(HttpStatus.CREATED).body(mealService.addUserMeal(request));
	}

	@GetMapping("/user")
	public ResponseEntity<Map<MealType, List<UserMealDTO>>> getUserMealListByDate(@RequestParam Integer userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
		return ResponseEntity.status(HttpStatus.OK).body(mealService.getMealsByDateGroupedByType(userId,date));
	}

	@GetMapping("/user/{mealId}")
	public ResponseEntity<UserMealDTO> getUserMeal(@PathVariable("mealId") Integer mealId, @RequestParam Integer userId){
		return ResponseEntity.status(HttpStatus.OK).body(mealService.getUserMeal(userId,mealId));
	}

	@DeleteMapping("/user/delete/{userMealId}")
	public ResponseEntity<String> deleteUserMeal(@PathVariable("userMealId") Integer userMealId){
		mealService.deleteUserMeal(userMealId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@GetMapping("/user/summary")
	public ResponseEntity<NutritionalSummaryDTO> getUserNutritionalSummaryByDate(@RequestParam Integer userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
		return ResponseEntity.status(HttpStatus.OK).body(mealService.getNutritionalSummary(userId,date));
	}
}
