package com.utem.healthyLifeStyleApp.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMeal implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MealType mealType; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "meal_id",nullable = false)
	private Meal meal;
	
	@Column(nullable = false)
	private Double amountInGrams;
	
	private Double calories;
	
	private Double carbsInGrams;
	
	private Double proteinInGrams;
	
	private Double fatInGrams;

	@Column(nullable = false)
	private LocalDate date;

}

