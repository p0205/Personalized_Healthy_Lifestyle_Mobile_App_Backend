package com.utem.healthyLifeStyleApp.config;

import java.io.FileNotFoundException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;

import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;

@Configuration
@RequiredArgsConstructor
public class TesseractConfig {

	final Environment environment;
	
	@Bean
	public Tesseract tesseract() throws FileNotFoundException {
		Tesseract tesseract = new Tesseract();
		// Get the absolute path to tessdata directory in resources
        String tessdataPath = ResourceUtils.getFile("classpath:tessdata")
            .getAbsolutePath();

        tesseract.setDatapath(tessdataPath);

//		tesseract.setDatapath(environment.getProperty("tesseract.data.path"));
		tesseract.setLanguage(environment.getProperty("tesseract.data.language"));
		   tesseract.setVariable("user_defined_dpi", environment.getProperty("tesseract.data.dpi"));
		return tesseract;
	}
}
