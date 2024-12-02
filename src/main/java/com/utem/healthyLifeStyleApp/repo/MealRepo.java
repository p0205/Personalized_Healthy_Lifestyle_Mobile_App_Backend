package com.utem.healthyLifeStyleApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utem.healthyLifeStyleApp.model.Meal;

@Repository
public interface MealRepo extends JpaRepository<Meal,Integer>{

	public Meal findByName(String name);
	
	
	public List<Meal> findByNameLike(String name);
}
