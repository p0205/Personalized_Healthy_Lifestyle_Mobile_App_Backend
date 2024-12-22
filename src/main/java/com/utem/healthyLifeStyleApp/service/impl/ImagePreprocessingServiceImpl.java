package com.utem.healthyLifeStyleApp.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.utem.healthyLifeStyleApp.service.ImagePreprocessingService;
import com.utem.healthyLifeStyleApp.utils.TesseractOcrUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImagePreprocessingServiceImpl implements ImagePreprocessingService{

    
    final TextDetectionService textDetectionService;
    final TableServiceImpl detector;

    
    //1. convert MultipartFile to Mat
    private Mat convertMultipartFileToMat(MultipartFile file,String fileExtension) throws IOException{
        BufferedImage bufferedImage = TesseractOcrUtil.createImageFromBytes(file.getBytes());
         // Convert BufferedImage to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, fileExtension, baos); // You can use PNG or another format if needed
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] byteArray = baos.toByteArray();
        // Convert byte array to Mat using OpenCV's imdecode function
        return Imgcodecs.imdecode(new MatOfByte(byteArray), Imgcodecs.IMREAD_COLOR);  // IMREAD_COLOR to load as a color image
    }

    private String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase(); // "jpg", "png", etc.
        }
        return "jpg"; // default to jpg if no extension is found
    }

    //2. convert Mat to grayScale
    public Mat convertToGrayscale(MultipartFile file) throws IOException{

        String fileExtension = getFileExtension(file);
        Mat image = convertMultipartFileToMat(file,fileExtension);

        System.out.println(textDetectionService.detectText(image));

        //crop the detected nutrition table(if any)
        Mat detectedTable  = detector.cropNutritionTable(image);

        if(detectedTable == null){
            System.out.println("no table is detected");
        }

        //grayImage
        Mat grayImage = new Mat();
        Imgproc.cvtColor(detectedTable, grayImage, Imgproc.COLOR_BGRA2GRAY);
        saveGrayScaleImage(grayImage,file.getOriginalFilename()+"grayImage");

        Mat horizontal = detectHorizontalLine(image);
        saveGrayScaleImage(horizontal,file.getOriginalFilename()+"horizontal");

        return horizontal;
    }
    

    private void saveGrayScaleImage(Mat grayscaleImage,String filename) throws IOException {
        // Get the path to the resources directory
        File resourceDirectory = new File("src/main/resources/grayScale");
    
        // Ensure the directory exists
        if (!resourceDirectory.exists()) {
            resourceDirectory.mkdirs(); // Create the directory if it doesn't exist
        }
    
        // Define the full path for the output image
        String outputPath = new File(resourceDirectory, filename+".jpg").getAbsolutePath();
    
        // Save the image using OpenCV
        boolean isSaved = Imgcodecs.imwrite(outputPath, grayscaleImage);

    }

    public Mat detectHorizontalLines(Mat source) {
        // Create binary image (assuming source is grayscale)
        Mat thresh = new Mat();
        Imgproc.threshold(source, thresh, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);

        // Create result image
        Mat result = source.clone();
        
        // Create horizontal kernel
        Mat horizontalKernel = Imgproc.getStructuringElement(
            Imgproc.MORPH_RECT, 
            new Size(40, 1)
        );

        // Detect horizontal lines
        Mat detectHorizontal = new Mat();
        Imgproc.morphologyEx(
            thresh, 
            detectHorizontal, 
            Imgproc.MORPH_OPEN, 
            horizontalKernel,
            new Point(-1, -1),
            3  // iterations
        );

        // Find contours
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(
            detectHorizontal, 
            contours, 
            hierarchy, 
            Imgproc.RETR_EXTERNAL, 
            Imgproc.CHAIN_APPROX_SIMPLE
        );

        // Draw contours
        Scalar color = new Scalar(36, 255, 12); // BGR color
        for (MatOfPoint contour : contours) {
            Imgproc.drawContours(
                result,
                Arrays.asList(contour),
                -1,  // draw all contours
                color,
                1   // thickness
            );
        }

        return result;
    }


    private Mat detectLines(Mat grayScale) throws IOException{
        Mat bw = new Mat();
        // Imgproc.threshold(grayScale, bw, 0, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C + Imgproc.THRESH_BINARY);
        Imgproc.threshold(grayScale, bw, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);

        saveGrayScaleImage(bw, "bw");

        Mat horizontal = bw.clone();
        // Specify size on horizontal axis
        int horizontal_size = horizontal.cols() / 30;
        // Create structure element for extracting horizontal lines through morphology operations
        Mat horizontalKernel = Imgproc.getStructuringElement(
            Imgproc.MORPH_RECT, 
            new Size(horizontal_size, 1)
        );

        // Apply morphology operations
        // Extract vertical lines
        Imgproc.erode(horizontal, horizontal, horizontalKernel,new Point(-1,-1));
        Imgproc.dilate(horizontal, horizontal, horizontalKernel, new Point(-1,-1));
        saveGrayScaleImage(horizontal, "extracted_horizontal_Line");
        return horizontal;
    }

    private Mat detectHorizontalLine(Mat originalMat){
        Mat grayImage = new Mat();
        Imgproc.cvtColor(originalMat, grayImage, Imgproc.COLOR_BGR2GRAY);

        Mat gray = grayImage.clone();
        Imgproc.threshold(grayImage, gray, 0, 255, Imgproc.THRESH_BINARY_INV + Imgproc.THRESH_OTSU);

        Mat horizontalKernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(40,1));

        //Detect horizontal lines
        Mat remove_horizontal = new Mat();
        for(int i = 0; i<2;i++){
            Imgproc.morphologyEx(gray, remove_horizontal,Imgproc.MORPH_OPEN, horizontalKernel);
        }

        //Get points of horizontal lines
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(remove_horizontal, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);


        //overwrite lines on the image
        //line color white for image with which background
        Scalar color = new Scalar(255,255,255);


        Mat blurred = new Mat();
        Imgproc.GaussianBlur(grayImage, blurred, new Size(5, 5), 1.5);

        // Create the high-frequency mask (original - blurred)
        Mat highFreq = new Mat();
        Core.subtract(grayImage, blurred, highFreq);

        // Amplify the high-frequency components and add them back
        Mat sharpImage = new Mat();
        Core.addWeighted(grayImage, 1.5, highFreq, 0.8, 0, sharpImage);
      

        //Overwrite horizontal black lines with white lines
        Imgproc.drawContours(sharpImage, contours, -1, color,2);
        return sharpImage;
    }
}
