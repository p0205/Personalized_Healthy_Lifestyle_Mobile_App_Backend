package com.utem.healthyLifeStyleApp.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.utem.healthyLifeStyleApp.dto.ImageTextDto;
import com.utem.healthyLifeStyleApp.model.Meal;
import com.utem.healthyLifeStyleApp.service.TesseractOCRService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {

	
	final private TesseractOCRService imageProcessService;
	
	
	@PostMapping("/upload")
	public ResponseEntity<ImageTextDto> extractTextFromImage(@RequestPart("file") MultipartFile file) throws IOException {
		
        return ResponseEntity.ok().body(imageProcessService.extractTextFromImage(file));
    }

	@PostMapping("/extract")
	public ResponseEntity<Meal> extractNutriFromImage(@RequestPart("file") MultipartFile file) throws IOException {
		
        ImageTextDto textDto =  imageProcessService.extractTextFromImage(file);
		Meal food = imageProcessService.extractNutritionFromOCRText(textDto);
		return ResponseEntity.ok().body(food);
    }

}
