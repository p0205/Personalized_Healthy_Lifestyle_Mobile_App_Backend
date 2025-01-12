package com.utem.healthyLifeStyleApp.dto;

import com.utem.healthyLifeStyleApp.model.HealthTest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserHealthTestStatus {

    private HealthTest healthTest;
    private Boolean isCompleted;
}
