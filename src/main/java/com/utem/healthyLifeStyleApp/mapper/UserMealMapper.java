package com.utem.healthyLifeStyleApp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.utem.healthyLifeStyleApp.dto.UserMealDTO;
import com.utem.healthyLifeStyleApp.model.UserMeal;

@Mapper(componentModel = "spring")
public interface UserMealMapper {

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "meal.id", source = "mealId")
    @Mapping(target = "meal.name", source = "mealName")
    public UserMeal fromDto(UserMealDTO dto);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "mealId", source = "meal.id")
    @Mapping(target = "mealName", source = "meal.name")
    public UserMealDTO toDto(UserMeal userMeal);

    public List<UserMealDTO> toDtoList(List<UserMeal> userMealList);
}
