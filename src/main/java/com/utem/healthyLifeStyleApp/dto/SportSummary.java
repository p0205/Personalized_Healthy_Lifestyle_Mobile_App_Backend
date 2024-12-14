package com.utem.healthyLifeStyleApp.dto;

import java.time.LocalDate;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SportSummary {

    private LocalDate date;
    private Double totalCalsBurnt;
    private Map<String, Double> calsBurntByType;
}
