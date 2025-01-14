package com.utem.healthyLifeStyleApp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

	private Integer id;
	private String name;
	private String email;
	private char gender;
	private Integer age;
	private Double weight;
	private Double height;
	private Integer goalCalories; //daily calories set by user
	private String occupationType;
    private String occupationTime;
    private String healthHistory;
    private String areaOfLiving;
    private Integer noOfFamilyMember;
	private byte[] profileImage;
}
