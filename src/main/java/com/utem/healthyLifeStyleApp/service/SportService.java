package com.utem.healthyLifeStyleApp.service;

import java.time.LocalDate;
import java.util.List;


import com.utem.healthyLifeStyleApp.dto.UserSportDTO;
import com.utem.healthyLifeStyleApp.model.Sport;
import com.utem.healthyLifeStyleApp.model.UserSport;

public interface SportService {

    public Sport addSport(Sport sport);
    public List<Sport> getMatchingSportList(String name);
    public Sport getSportById(Integer sportId);
    public UserSportDTO addUserSport(UserSportDTO dto);
    public List<UserSportDTO> getUserSportListByDate(Integer userId,LocalDate date);
    public List<UserSportDTO> getUserSport(Integer userId, Integer sportId);
    public void deleteUserSport(UserSport userSport);
    
}
