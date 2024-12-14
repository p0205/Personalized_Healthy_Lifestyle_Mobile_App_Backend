package com.utem.healthyLifeStyleApp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.utem.healthyLifeStyleApp.dto.SportSummary;
import com.utem.healthyLifeStyleApp.dto.UserSportDTO;
import com.utem.healthyLifeStyleApp.model.Sport;

public interface SportService {

    public Sport addSport(Sport sport);
    public List<Sport> getMatchingSportList(String name);
    public Sport getSportById(Integer sportId);
    public UserSportDTO addUserSport(UserSportDTO dto);
    public Map<String, List<UserSportDTO>> getUserSportListByDate(Integer userId,LocalDate date);
    public List<UserSportDTO> getUserSport(Integer userId, Integer sportId);
    public void deleteUserSport(Integer userSportID);
    public SportSummary getSportSummary(int userId, LocalDate date);
    
}
