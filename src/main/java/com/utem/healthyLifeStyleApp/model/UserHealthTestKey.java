package com.utem.healthyLifeStyleApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.RequiredArgsConstructor;

@Embeddable
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@Data
public class UserHealthTestKey {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "test_id")
    private Integer healthTestId;
}
