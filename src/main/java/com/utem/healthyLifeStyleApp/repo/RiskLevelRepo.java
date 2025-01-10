package com.utem.healthyLifeStyleApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utem.healthyLifeStyleApp.model.RiskLevel;

@Repository
public interface RiskLevelRepo extends JpaRepository<RiskLevel,Integer>{
 
    List<RiskLevel> findByHealthTest_Id(Integer healthTestId);
}
