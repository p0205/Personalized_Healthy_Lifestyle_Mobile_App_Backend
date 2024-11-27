package com.utem.healthyLifeStyleApp.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.utem.healthyLifeStyleApp.dto.ImageTextDto;
import com.utem.healthyLifeStyleApp.model.Food;

public interface TesseractOCRService {
 
	ImageTextDto extractTextFromImage(MultipartFile file) throws IOException;
	public Food extractNutritionFromOCRText(ImageTextDto textDto);
}
