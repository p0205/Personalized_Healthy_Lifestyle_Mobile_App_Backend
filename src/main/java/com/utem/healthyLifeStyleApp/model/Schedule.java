// package com.utem.healthyLifeStyleApp.model;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// public class Schedule {

//     @Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;

//     @ManyToOne
//     @JoinColumn(name = "user_id",nullable = false)
//     private User user;

//     @Column(length = 12, nullable=false)
//     private String day;

// }
