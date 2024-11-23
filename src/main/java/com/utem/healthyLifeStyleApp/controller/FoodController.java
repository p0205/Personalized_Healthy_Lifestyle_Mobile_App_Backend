package com.utem.healthyLifeStyleApp.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.utem.healthyLifeStyleApp.dto.FoodSearchDTO;
import com.utem.healthyLifeStyleApp.dto.ImageTextDto;
import com.utem.healthyLifeStyleApp.model.Food;
import com.utem.healthyLifeStyleApp.service.FoodService;
import com.utem.healthyLifeStyleApp.service.TesseractOCRService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/food")
public class FoodController {

	
	private FoodService foodService;
	
	
	final TesseractOCRService imageProcessService;
	
	@GetMapping("/search")
	public ResponseEntity<List<FoodSearchDTO>> getMatchingFoodList(@RequestParam String name){
	
		System.out.println("Request arrived");
		return ResponseEntity.status(HttpStatus.OK).body(foodService.getMatchingFoodList(name));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Food> getFoodList(@PathVariable("id") Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(foodService.getById(id));
	}
	
	@PostMapping("/upload")
	public ImageTextDto extractTextFromImage(@RequestPart("file") MultipartFile file) throws IOException {
        return imageProcessService.extractTextFromImage(file);
    }

}
