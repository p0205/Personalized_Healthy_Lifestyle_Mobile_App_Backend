package com.utem.healthyLifeStyleApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageTextDto {

	private String fileName;
	private String text;
}
