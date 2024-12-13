package com.utem.healthyLifeStyleApp.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.utem.healthyLifeStyleApp.dto.SportSummary;
import com.utem.healthyLifeStyleApp.dto.UserDTO;
import com.utem.healthyLifeStyleApp.dto.UserMealDTO;
import com.utem.healthyLifeStyleApp.dto.UserSportDTO;
import com.utem.healthyLifeStyleApp.mapper.UserMapper;
import com.utem.healthyLifeStyleApp.mapper.UserSportMapper;
import com.utem.healthyLifeStyleApp.model.Sport;
import com.utem.healthyLifeStyleApp.model.User;
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
    public Map<String, List<UserSportDTO>> getUserSportListByDate(Integer userId,LocalDate date) {
        List<UserSport>  userSports = userSportRepo.findByUserIdAndDate(userId,date);

         // Group meals by MealType
        return userSports.stream()
                .collect(Collectors.groupingBy(
                    userSport -> userSport.getSport().getType(),
                    Collectors.mapping(userSportMapper::toDto, Collectors.toList())));
    }

    public List<UserSportDTO> getUserSport(Integer userId, Integer sportId){
        return userSportMapper.toDtoList(userSportRepo.findByUserIdAndSportId(userId, sportId));
    }

    @Override
    public void deleteUserSport(Integer userSportId) {
        userSportRepo.deleteById(userSportId);
    }

    @Override
    public SportSummary getSportSummary(int userId, LocalDate date) {
        SportSummary summary = new SportSummary();
        summary.setCalsBurntByType(getCaloriesBySportType(userSportRepo.findCaloriesBySportTypeAndDate(userId,date)));
        summary.setDate(date);
        summary.setTotalCalsBurnt(userSportRepo.findTotalCaloriesByType(userId, date));
        return summary;
    } 

    private Map<String, Double> getCaloriesBySportType( List<Object[]> results) {
    
        return results.stream()
                      .collect(Collectors.toMap(
                          result -> (String) result[1], // Sport type
                          result -> ((Number) result[0]).doubleValue() // Total calories
                      ));
    }


}
