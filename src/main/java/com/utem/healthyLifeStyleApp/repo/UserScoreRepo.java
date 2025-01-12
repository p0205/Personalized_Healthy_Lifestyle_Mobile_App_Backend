package com.utem.healthyLifeStyleApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utem.healthyLifeStyleApp.model.UserScore;

public interface UserScoreRepo extends JpaRepository<UserScore,Integer>{    

    UserScore findByIdUserIdAndIdHealthTestId(Integer userId, Integer healthTestId);

    List<UserScore> findByIdUserId(Integer userId);
}
