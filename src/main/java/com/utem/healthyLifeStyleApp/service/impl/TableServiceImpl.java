package com.utem.healthyLifeStyleApp.service.impl;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableServiceImpl {
    final Tesseract tesseract;

    // public void detectTables(Mat image) {
    //     try {
    //         // Read the image
          
    //         Mat gray = new Mat();
    //         Mat binary = new Mat();
    //         Mat horizontal = new Mat();
    //         Mat vertical = new Mat();
            
    //         // Convert to grayscale
    //         Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);
            
    //         // Apply adaptive thresholding
    //         Imgproc.adaptiveThreshold(gray, binary, 255,
    //                 Imgproc.ADAPTIVE_THRESH_MEAN_C,
    //                 Imgproc.THRESH_BINARY_INV, 15, 2);
            
    //         // Create structure elements for detecting horizontal and vertical lines
    //         Mat horizontalStructure = Imgproc.getStructuringElement(
    //                 Imgproc.MORPH_RECT, new Size(binary.cols() / 30, 1));
    //         Mat verticalStructure = Imgproc.getStructuringElement(
    //                 Imgproc.MORPH_RECT, new Size(1, binary.rows() / 30));
            
    //         // Detect horizontal lines
    //         Imgproc.erode(binary, horizontal, horizontalStructure);
    //         Imgproc.dilate(horizontal, horizontal, horizontalStructure);
            
    //         // Detect vertical lines
    //         Imgproc.erode(binary, vertical, verticalStructure);
    //         Imgproc.dilate(vertical, vertical, verticalStructure);
            
    //         // Combine horizontal and vertical lines
    //         Mat table = new Mat();
    //         Core.add(horizontal, vertical, table);
            
    //         // Find contours
    //         List<MatOfPoint> contours = new ArrayList<>();
    //         Mat hierarchy = new Mat();
    //         Imgproc.findContours(table, contours, hierarchy,
    //                 Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
            
    //         // Filter and draw rectangles around tables
    //         for (MatOfPoint contour : contours) {
    //             double area = Imgproc.contourArea(contour);
                
    //             // Filter small contours - adjust threshold as needed
    //             if (area < 500) continue;
                
    //             Rect rect = Imgproc.boundingRect(contour);
                
    //             // Filter based on aspect ratio if needed
    //             double aspectRatio = (double) rect.width / rect.height;
    //             if (aspectRatio < 0.2 || aspectRatio > 5) continue;
                
    //             // Draw rectangle
    //             Imgproc.rectangle(
    //                 image,
    //                 new Point(rect.x, rect.y),
    //                 new Point(rect.x + rect.width, rect.y + rect.height),
    //                 new Scalar(0, 255, 0),
    //                 2
    //             );
                
    //             System.out.printf("Table found at: x=%d, y=%d, width=%d, height=%d%n",
    //                 rect.x, rect.y, rect.width, rect.height);
    //         }
            
    //         // Save result
    //         Imgcodecs.imwrite("detected_tables.png", image);
            
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    
    // Optional: Method to extract table content
    public void extractTableContent(Mat image, Rect tableRect) {
        try {
            // Extract table region
            Mat tableRegion = new Mat(image, tableRect);
            
            // Convert to grayscale
            Mat gray = new Mat();
            Imgproc.cvtColor(tableRegion, gray, Imgproc.COLOR_BGR2GRAY);
            
            // Threshold
            Mat binary = new Mat();
            Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);
            
            // Find contours for cells
            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(binary, contours, hierarchy,
                    Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
            
            // Sort contours by position (top to bottom, left to right)
            Collections.sort(contours, (c1, c2) -> {
                Rect r1 = Imgproc.boundingRect(c1);
                Rect r2 = Imgproc.boundingRect(c2);
                int result = Integer.compare(r1.y, r2.y);
                if (result == 0) {
                    result = Integer.compare(r1.x, r2.x);
                }
                return result;
            });
            
            // Process each cell
            for (MatOfPoint contour : contours) {
                Rect cellRect = Imgproc.boundingRect(contour);
                // Here you can add OCR processing for each cell if needed
                // For example, using Tesseract OCR
                
                // Draw cell boundaries for visualization
                Imgproc.rectangle(
                    tableRegion,
                    new Point(cellRect.x, cellRect.y),
                    new Point(cellRect.x + cellRect.width, cellRect.y + cellRect.height),
                    new Scalar(0, 0, 255),
                    1
                );
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Mat cropNutritionTable(Mat image) {
    try {

        List<Rect> tableRegions = detectTableRegions(image);
        
        if (tableRegions.isEmpty()) {
            System.out.println("No tables detected");
            return null;
        }

        // Score and rank each detected region
        List<TableScore> scoredTables = new ArrayList<>();
        for (Rect region : tableRegions) {
            Mat tableRegion = new Mat(image, region);
            double score = calculateNutritionTableScore(tableRegion, region);
            scoredTables.add(new TableScore(region, score));
        }

        // Sort by score in descending order
        Collections.sort(scoredTables, (a, b) -> Double.compare(b.score, a.score));

        // Get the highest scoring region
        Rect bestRegion = scoredTables.get(0).region;
        
        // Crop the region with some padding
        int padding = 10;
        int x = Math.max(0, bestRegion.x - padding);
        int y = Math.max(0, bestRegion.y - padding);
        int width = Math.min(image.cols() - x, bestRegion.width + 2 * padding);
        int height = Math.min(image.rows() - y, bestRegion.height + 2 * padding);
        
        Rect paddedRegion = new Rect(x, y, width, height);
        Mat croppedTable = new Mat(image, paddedRegion);
        
        // Save the cropped image
        Imgcodecs.imwrite("cropped_nutrition_table.png", croppedTable);
        System.out.println("end cropNutritionTable");
        return croppedTable;

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

private double calculateNutritionTableScore(Mat region, Rect bounds) {
    double score = 0.0;
    System.out.println("enter calculateNutritionTableScore");
    // 1. Aspect ratio check (nutrition tables typically have height > width)
    double aspectRatio = (double) bounds.height / bounds.width;
    if (aspectRatio > 1.2 && aspectRatio < 2.5) {
        score += 20.0;
    }
    
    // 2. Size check (not too small or too large relative to image)
    double relativeSize = (double)(bounds.width * bounds.height) / 
                         (region.cols() * region.rows());
    if (relativeSize > 0.1 && relativeSize < 0.7) {
        score += 15.0;
    }
    
    // 3. Content analysis
    Mat gray = new Mat();
    Imgproc.cvtColor(region, gray, Imgproc.COLOR_BGR2GRAY);
    
    // Check for "Nutrition Facts" text at top
    Rect headerRegion = new Rect(0, 0, bounds.width, bounds.height/6);
    Mat header = new Mat(gray, headerRegion);
    String headerText = performOCR(header);
    if (headerText.toLowerCase().contains("nutrition") && 
        headerText.toLowerCase().contains("facts")) {
        score += 30.0;
    }
    
    // 4. Structure analysis
    // Look for characteristic horizontal lines
    Mat binary = new Mat();
    Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY_INV + Imgproc.THRESH_OTSU);
    
    Mat horizontalStructure = Imgproc.getStructuringElement(
        Imgproc.MORPH_RECT, new Size(binary.cols()/3, 1));
    Mat horizontal = new Mat();
    Imgproc.erode(binary, horizontal, horizontalStructure);
    Imgproc.dilate(horizontal, horizontal, horizontalStructure);
    
    int horizontalLines = Core.countNonZero(horizontal);
    if (horizontalLines > 5) { // Typical nutrition tables have multiple horizontal lines
        score += 15.0;
    }
    
    // 5. Check for percentage signs (%) in right portion
    Rect rightPortion = new Rect(
        bounds.width * 2/3, 0, 
        bounds.width/3, bounds.height
    );
    Mat rightRegion = new Mat(gray, rightPortion);
    String rightText = performOCR(rightRegion);
    int percentCount = rightText.split("%").length - 1;
    if (percentCount > 3) {
        score += 20.0;
    }
    System.out.println("End calculateNutritionTableScore");
    return score;
}

private static class TableScore {
    Rect region;
    double score;
    
    TableScore(Rect region, double score) {
        this.region = region;
        this.score = score;
    }
}

private List<Rect> detectTableRegions(Mat image) {
    System.out.println("Enter detectTableRegions");
    List<Rect> tableRegions = new ArrayList<>();
    
    // Convert to grayscale
    Mat gray = new Mat();
    Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);
    
    // Apply adaptive thresholding
    Mat binary = new Mat();
    Imgproc.adaptiveThreshold(gray, binary, 255,
            Imgproc.ADAPTIVE_THRESH_MEAN_C,
            Imgproc.THRESH_BINARY_INV, 15, 2);
    
    // Detect lines
    Mat horizontal = new Mat();
    Mat vertical = new Mat();
    
    Mat horizontalStructure = Imgproc.getStructuringElement(
            Imgproc.MORPH_RECT, new Size(binary.cols()/30, 1));
    Mat verticalStructure = Imgproc.getStructuringElement(
            Imgproc.MORPH_RECT, new Size(1, binary.rows()/30));
    
    Imgproc.erode(binary, horizontal, horizontalStructure);
    Imgproc.dilate(horizontal, horizontal, horizontalStructure);
    
    Imgproc.erode(binary, vertical, verticalStructure);
    Imgproc.dilate(vertical, vertical, verticalStructure);
    
    // Combine lines
    Mat combined = new Mat();
    Core.add(horizontal, vertical, combined);
    
    // Find contours
    List<MatOfPoint> contours = new ArrayList<>();
    Imgproc.findContours(combined, contours, new Mat(),
            Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
    
    // Filter contours
    for (MatOfPoint contour : contours) {
        Rect rect = Imgproc.boundingRect(contour);
        double area = Imgproc.contourArea(contour);
        
        if (area > 500) { // Minimum area threshold
            tableRegions.add(rect);
        }
    }
    System.out.println("End detectTableRegions");
    return tableRegions;
}

private String performOCR(Mat image) {
    nu.pattern.OpenCV.loadLocally();
    System.out.println("enter performOCR");
    try {

        BufferedImage bufferedImage = matToBufferedImage(image);
        System.out.println("enter performOCR");
        return tesseract.doOCR(bufferedImage);
    } catch (Exception e) {
        return "";
    }
}
private BufferedImage matToBufferedImage(Mat mat) {
    System.out.println("enter matToBufferedImage");
    // Convert OpenCV Mat to BufferedImage for OCR
    int type = BufferedImage.TYPE_BYTE_GRAY;
    if (mat.channels() > 1) {
        type = BufferedImage.TYPE_3BYTE_BGR;
    }
    BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
    byte[] data = new byte[mat.channels() * mat.cols() * mat.rows()];
    mat.get(0, 0, data);
    
    if (type == BufferedImage.TYPE_BYTE_GRAY) {
        DataBufferByte buffer = new DataBufferByte(data, data.length);
        WritableRaster raster = Raster.createInterleavedRaster(buffer, mat.cols(), mat.rows(), mat.cols(), 1, new int[]{0}, null);
        image.setData(raster);
    } else {
        byte[] b = new byte[data.length/3];
        byte[] g = new byte[data.length/3];
        byte[] r = new byte[data.length/3];
        for (int i=0; i<data.length/3; i++) {
            b[i] = data[3*i];
            g[i] = data[3*i+1];
            r[i] = data[3*i+2];
        }
        DataBufferByte buffer = new DataBufferByte(data, data.length);
        WritableRaster raster = Raster.createInterleavedRaster(buffer, mat.cols(), mat.rows(), mat.cols()*3, 3, new int[]{0,1,2}, null);
        image.setData(raster);
    }
    System.out.println("end matToBufferedImage");
    return image;
}

}