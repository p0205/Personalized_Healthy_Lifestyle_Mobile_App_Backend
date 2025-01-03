package com.utem.healthyLifeStyleApp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = true)
	private String email;

	@Column(nullable = true)
	private String password;

	@Column(nullable = true)
	private Integer age;

	@Column(nullable = true)
	private char gender;

	@Column(nullable = true)
	private String name;

	@Column(nullable = false)
	private String occupationType;

	@Column(nullable = false)
	private String occupationTime;

	@Column(nullable = false)
	private String healthHistory;

	@Column(nullable = false)
	private String areaOfLiving;

	@Column(nullable = false)
	private Integer noOfFamilyMember;

	@Column(nullable = false)
	private Double weight;

	@Column(nullable = false)
	private Double height;

	@Column(nullable = false)
	private Integer goalCalories; //daily calories set by user
	
}
