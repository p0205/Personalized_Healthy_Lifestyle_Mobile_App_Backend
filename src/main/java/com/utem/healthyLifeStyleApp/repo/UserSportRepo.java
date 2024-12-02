package com.utem.healthyLifeStyleApp.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.utem.healthyLifeStyleApp.model.UserSport;

@Repository
public interface UserSportRepo extends JpaRepository<UserSport, Integer> {

    @Query("SELECT us FROM UserSport us WHERE us.user.id = :userId AND us.date = :date")
    public List<UserSport> findByUserIdAndDate(@Param("userId") Integer userId,@Param("date") LocalDate date);
    
    @Query("SELECT us FROM UserSport us WHERE us.user.id = :userId AND us.sport.id = :sportId")
    List<UserSport> findByUserIdAndSportId(@Param("userId") Integer userId, @Param("sportId") Integer sportId);

}
