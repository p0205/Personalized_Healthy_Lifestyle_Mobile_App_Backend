package com.utem.healthyLifeStyleApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utem.healthyLifeStyleApp.model.UserScore;

public interface UserScoreRepo extends JpaRepository<UserScore,Integer>{    

    public UserScore findByUser_IdAndHealthTest_Id(Integer userId, Integer healthTestId);

}
