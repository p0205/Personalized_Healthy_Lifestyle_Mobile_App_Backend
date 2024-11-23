package com.utem.healthyLifeStyleApp.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.utem.healthyLifeStyleApp.dto.ImageTextDto;
import com.utem.healthyLifeStyleApp.service.TesseractOCRService;
import com.utem.healthyLifeStyleApp.utils.TesseractOcrUtil;

import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
@RequiredArgsConstructor
public class TesseractOCRServiceImpl implements TesseractOCRService {

	final Tesseract tesseract;
	
	@Override
	public ImageTextDto extractTextFromImage(MultipartFile file) throws IOException {

		
		try {
			// Perform OCR on the image
			  String text = tesseract.doOCR(TesseractOcrUtil.createImageFromBytes(file.getBytes()));

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

}
