package com.utem.healthyLifeStyleApp.service;

import java.io.IOException;

import org.opencv.core.Mat;
import org.springframework.web.multipart.MultipartFile;

public interface ImagePreprocessingService {

    public Mat convertToGrayscale(MultipartFile file)throws IOException;
}
