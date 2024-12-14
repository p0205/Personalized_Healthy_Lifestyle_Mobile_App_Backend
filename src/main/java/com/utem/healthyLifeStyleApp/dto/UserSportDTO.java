package com.utem.healthyLifeStyleApp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSportDTO {

    private Integer id; 
    
    private Integer userId;

    private Integer sportId;

    private String sportName;

    private Double durationInHours;

    private Double caloriesBurnt;

    private LocalDate date;
}
