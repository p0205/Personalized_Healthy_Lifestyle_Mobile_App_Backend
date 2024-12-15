package com.utem.healthyLifeStyleApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utem.healthyLifeStyleApp.model.Sport;

@Repository
public interface SportRepo extends JpaRepository<Sport,Integer>{
    
    @Query("SELECT DISTINCT t.type FROM Sport t ORDER BY t.type")
    public List<String> findDistinctSportTypes();

    public List<Sport> findByNameLike(String name);
}


