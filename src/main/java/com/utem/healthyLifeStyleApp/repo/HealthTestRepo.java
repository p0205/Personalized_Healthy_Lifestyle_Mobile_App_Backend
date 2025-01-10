package com.utem.healthyLifeStyleApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utem.healthyLifeStyleApp.model.HealthTest;

public interface HealthTestRepo extends JpaRepository<HealthTest,Integer>{

}
