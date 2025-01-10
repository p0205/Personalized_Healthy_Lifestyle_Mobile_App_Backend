// package com.utem.healthyLifeStyleApp.model;

// import java.time.LocalDateTime;

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
// public class TodoTask {

//     @Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;

//     @ManyToOne
//     @JoinColumn(name = "todo_id", nullable = false)
//     private Todo todo;

//     @Column(nullable = false)
//     private String type;

//     @Column(nullable = false)
//     private String title;

//     @Column(nullable = false)
//     private LocalDateTime startTime;

//     @Column(nullable = false)
//     private LocalDateTime endTime;

//     @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
//     private Boolean isComplete;
// }
