package com.utem.healthyLifeStyleApp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NutritionalSummaryDTO {

    private LocalDate date;
    private Double caloriesLeft;
    private Double caloriesIntake;
    private Double carbsIntake;
    private Double proteinIntake;
    private Double fatIntake;
}
