package com.utem.healthyLifeStyleApp.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.utem.healthyLifeStyleApp.model.UserMeal;

@Repository
public interface UserMealRepo extends JpaRepository<UserMeal, Integer>{
	
    @Query("SELECT um FROM UserMeal um WHERE um.user.id = :userId")
    public List<UserMeal> findByUserId(Integer userId);

    @Query("SELECT um FROM UserMeal um WHERE um.user.id = :userId AND um.meal.id = :mealId")
    public UserMeal findByUserIdAndMealId(@Param("userId") Integer userId, @Param("mealId") Integer mealId);

   
    @Query("SELECT m FROM UserMeal m WHERE m.user.id = :userId AND m.date=:date ORDER BY m.mealType")
    public List<UserMeal> findByUserIdAndDateOrderByMealType(@Param("userId") Integer userId, @Param("date") LocalDate date);

}
