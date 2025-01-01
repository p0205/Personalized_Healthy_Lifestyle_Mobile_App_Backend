package com.utem.healthyLifeStyleApp.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.utem.healthyLifeStyleApp.dto.ImageTextDto;
import com.utem.healthyLifeStyleApp.model.Meal;
import com.utem.healthyLifeStyleApp.service.ImagePreprocessingService;
import com.utem.healthyLifeStyleApp.service.TesseractOCRService;

import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
@RequiredArgsConstructor
public class TesseractOCRServiceImpl implements TesseractOCRService {

	final Tesseract tesseract;

	final ImagePreprocessingService preprocessingService;

	// final TextDetectionService textDetectionService;


	@Override
	public ImageTextDto extractTextFromImage(MultipartFile file) throws IOException {
	
		nu.pattern.OpenCV.loadLocally();

		try {
			// Perform OCR on the image
			Mat image = preprocessingService.convertToGrayscale(file);
			
					// Define a temporary file path
			File tempFile = File.createTempFile("tempImage", ".png");
			tempFile.deleteOnExit(); // Ensure the file is deleted when the program exits
			
			// Save the Mat image to the temporary file
			Imgcodecs.imwrite(tempFile.getAbsolutePath(), image);
    
			  String text = tesseract.doOCR(tempFile);
			  
	            return ImageTextDto.builder()
	                    .fileName(file.getOriginalFilename())
	                    .text(text)
	                    .build();
		}catch(TesseractException e) {
			   return ImageTextDto.builder()
	                    .fileName(file.getOriginalFilename())
	                    .text("")
	                    .build();
		}
	}
	// public ImageTextDto extractTextFromImage2(MultipartFile file) throws IOException {
	
	// 	nu.pattern.OpenCV.loadLocally();

	// 	try {
	// 		// Perform OCR on the image
	// 		Mat image = preprocessingService.convertToGrayscale(file);
			
	// 				// Define a temporary file path
	// 		File tempFile = File.createTempFile("tempImage", ".png");
	// 		tempFile.deleteOnExit(); // Ensure the file is deleted when the program exits
			
	// 		// Save the Mat image to the temporary file
	// 		Imgcodecs.imwrite(tempFile.getAbsolutePath(), image);
    
	// 		  String text = tesseract.doOCR(tempFile);
			  
	//             return ImageTextDto.builder()
	//                     .fileName(file.getOriginalFilename())
	//                     .text(text)
	//                     .build();
	// 	}catch(TesseractException e) {
	// 		   return ImageTextDto.builder()
	//                     .fileName(file.getOriginalFilename())
	//                     .text("")
	//                     .build();
	// 	}
	// }

	public Meal extractNutritionFromOCRText(ImageTextDto textDto){

		String text = textDto.getText();
		Meal food = new Meal();

		 // Using regex patterns to extract specific values
		 Pattern servingSizePattern = Pattern.compile("Serving size.*?\\((\\d+\\.?\\d*)g\\)");
		 Pattern caloriesPattern = Pattern.compile("(?:Calories|fe)\\s+(\\d+)");
		 Pattern fatPattern = Pattern.compile("Total\\s*Fat\\s+(\\d+[.]?\\d*)g?");
		 Pattern carbPattern = Pattern.compile("Carbohydrate\\s+(\\d+[.]?\\d*)g?");
		 Pattern proteinPattern = Pattern.compile("Protein\\s+(\\d+[.]?\\d*)g?");
 
		 // Extract serving size
		 Matcher servingSizeMatcher = servingSizePattern.matcher(text);
		 
		 if (servingSizeMatcher.find()) {
			try {
				food.setUnitWeight(Double.parseDouble((servingSizeMatcher.group(1).trim())));
			} catch (Exception e) {
				//set unit weight to null
				e.printStackTrace();
			}
		 }
 
		 // Extract calories
		 Matcher caloriesMatcher = caloriesPattern.matcher(text);
		 if (caloriesMatcher.find()) {
			try {
				food.setEnergyPer100g(Double.parseDouble((caloriesMatcher.group(1).trim())));
			} catch (Exception e) {
				//set unit weight to null
				e.printStackTrace();
			}
		 }
 
		 // Extract fat
		 Matcher fatMatcher = fatPattern.matcher(text);
		 if (fatMatcher.find()) {
			try {
				food.setFatPer100g(Double.parseDouble((fatMatcher.group(1).trim())));
			} catch (Exception e) {
				//set unit weight to null
				e.printStackTrace();
			}

		 }
 
		 // Extract carbs
		 Matcher carbsMatcher = carbPattern.matcher(text);
		 if (carbsMatcher.find()) {
			try {
				food.setCarbsPer100g(Double.parseDouble(carbsMatcher.group(1).trim()));
			} catch (Exception e) {
				//set unit weight to null
				e.printStackTrace();
			}
		 }
 
		 // Extract protein
		 Matcher proteinMatcher = proteinPattern.matcher(text);
		 if (proteinMatcher.find()) {
			try {
				food.setProteinPer100g((Double.parseDouble(proteinMatcher.group(1).trim())));
			} catch (Exception e) {
				//set unit weight to null
				e.printStackTrace();
			}

		 }
		 return food;
	}

}
