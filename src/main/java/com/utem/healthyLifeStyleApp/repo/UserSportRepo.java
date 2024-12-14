package com.utem.healthyLifeStyleApp.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    public List<UserSport> findByUserIdAndSportId(@Param("userId") Integer userId, @Param("sportId") Integer sportId);

    @Query(value = "SELECT SUM(us.calories_burnt), s.type " +
    "FROM user_sport us " +
    "JOIN sport s ON us.sport_id = s.id " +
    "WHERE us.date = :date AND us.user_id = :userId " +
    "GROUP BY s.type", nativeQuery = true)
    public List<Object[]> findCaloriesBySportTypeAndDate(@Param("userId") Integer userId, @Param("date") LocalDate date );

    @Query("SELECT SUM(caloriesBurnt) FROM UserSport WHERE date = :date AND user.id = :userId")
    public Double findTotalCaloriesByType(@Param("userId") Integer userId, @Param("date") LocalDate date);

}
