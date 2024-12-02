package com.utem.healthyLifeStyleApp.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.utem.healthyLifeStyleApp.dto.UserDTO;
import com.utem.healthyLifeStyleApp.dto.UserSportDTO;
import com.utem.healthyLifeStyleApp.mapper.UserMapper;
import com.utem.healthyLifeStyleApp.mapper.UserSportMapper;
import com.utem.healthyLifeStyleApp.model.Sport;
import com.utem.healthyLifeStyleApp.model.UserSport;
import com.utem.healthyLifeStyleApp.repo.SportRepo;
import com.utem.healthyLifeStyleApp.repo.UserSportRepo;
import com.utem.healthyLifeStyleApp.service.SportService;
import com.utem.healthyLifeStyleApp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SportServiceImpl implements SportService{

    final SportRepo sportRepo;

    final UserSportRepo userSportRepo;

    final UserService userService;

    final UserMapper userMapper;

    final UserSportMapper userSportMapper;

    @Override
    public Sport addSport(Sport sport) {
        return sportRepo.save(sport);
    }

    @Override
    public List<Sport> getMatchingSportList(String name) {
       return sportRepo.findByNameLike("%"+name+"%");
    }
    @Override
    public Sport getSportById(Integer sportId){
        Optional<Sport> sportOpt = sportRepo.findById(sportId);
        if(sportOpt.isPresent()){
            return sportOpt.get();
        }
        else{
            return null;
        }
           
    }

    @Override
    public UserSportDTO addUserSport(UserSportDTO dto) {
        Sport sport = getSportById(dto.getSportId());
        UserDTO user = userService.getUserById(dto.getUserId());
        UserSport userSport = UserSport.builder()
                                .user(userMapper.fromDTO(user))
                                .sport(sport)
                                .durationInHours(dto.getDurationInHours())
                                .date(LocalDate.now())
                                .caloriesBurnt(calcCalories(sport.getCaloriesBurntPerHourPerKg(), dto.getDurationInHours(),user.getWeight()))
                                .build();
    
        return userSportMapper.toDto(userSportRepo.save(userSport));
    }

    private Double calcCalories(Double calories_per_hour_per_kg, Double durationHours, Double weight){
        return calories_per_hour_per_kg * durationHours * weight;
    }


    @Override
    public List<UserSportDTO> getUserSportListByDate(Integer userId,LocalDate date) {
        return userSportMapper.toDtoList(userSportRepo.findByUserIdAndDate(userId,date));
    }

    public List<UserSportDTO> getUserSport(Integer userId, Integer sportId){
        return userSportMapper.toDtoList(userSportRepo.findByUserIdAndSportId(userId, sportId));
    }

    @Override
    public void deleteUserSport(UserSport userSport) {
        userSportRepo.delete(userSport);
    }


}
