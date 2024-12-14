package com.utem.healthyLifeStyleApp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TextDetectionService {

    private static final String EAST_MODEL_PATH = "src/main/resources/models/frozen_east_text_detection.pb";

    public String detectText(Mat image){
        Mat orig = image.clone();
        Size oriSize = image.size();

        Size newSize = new Size(320,320);
        Mat resizedImage = new Mat();
        Imgproc.resize(orig, resizedImage, newSize);

        //Load the EAST Model which is pre-trained to detect text regions in images
        Net net = Dnn.readNetFromTensorflow(EAST_MODEL_PATH);

        //Prepare input for the Model
        Mat blob = Dnn.blobFromImage(resizedImage, 1.0, newSize, new Scalar(123.68, 116.78, 103.94), true, false);
        net.setInput(blob);

        //Run the Model
        List<String> layerNames = new ArrayList<>();
        //confidence scores for text presence in each region
        layerNames.add("feature_fusion/Conv_7/Sigmoid");
        //geomeyry information for the bounding boxes
        layerNames.add("feature_fusion/concat_3");

        List<Mat> outs = new ArrayList<>();

        //Run forward pass of the model and store the outputs in the outs list
        net.forward(outs, layerNames);

        //Decode predictions
        Mat scores = outs.get(0);
        Mat geometry = outs.get(1);

        List<Rect> boxes = decodePredictions(scores,geometry,oriSize);


        //Draw bounding boxes
        for(Rect rect: boxes){
            Imgproc.rectangle(orig, rect.tl(), rect.br(),new Scalar(0,255,0),2);
        }

        //Save the output image with bounding boxes
        String outputPath = "output_with_boxes.jpg";
        Imgcodecs.imwrite(outputPath,orig);
        return "Text detection complete. Output saved at: " + outputPath;
    }

    private List<Rect> decodePredictions(Mat scores, Mat geometry, Size originalSize) {
        List<Rect> rectangles = new ArrayList<>();
        
        int height = scores.size(2);
        int width = scores.size(3);
        
        float scoreThresh = 0.5f;
        
        for (int y = 0; y < height; ++y) {
            float[] scoresData = new float[width];
            scores.get(0, 0, scoresData);
            
            float[] x0Data = new float[width];
            float[] x1Data = new float[width];
            float[] x2Data = new float[width];
            float[] x3Data = new float[width];
            float[] anglesData = new float[width];
            
            geometry.get(0, 0, x0Data);
            geometry.get(0, 1, x1Data);
            geometry.get(0, 2, x2Data);
            geometry.get(0, 3, x3Data);
            geometry.get(0, 4, anglesData);
            
            for (int x = 0; x < width; ++x) {
                if (scoresData[x] < scoreThresh) {
                    continue;
                }
                
                float offsetX = x * 4.0f;
                float offsetY = y * 4.0f;
                
                float angle = anglesData[x];
                float cos = (float) Math.cos(angle);
                float sin = (float) Math.sin(angle);
                
                float h = x0Data[x] + x2Data[x];
                float w = x1Data[x] + x3Data[x];
                
                // Using Point instead of Point2f
                Point p0 = new Point(offsetX + cos * x1Data[x] + sin * x0Data[x],
                                   offsetY - sin * x1Data[x] + cos * x0Data[x]);
                Point p1 = new Point(offsetX - cos * x2Data[x] - sin * x0Data[x],
                                   offsetY + sin * x2Data[x] - cos * x0Data[x]);
                Point p2 = new Point(offsetX - cos * x2Data[x] + sin * h,
                                   offsetY + sin * x2Data[x] + cos * h);
                Point p3 = new Point(offsetX + cos * x1Data[x] - sin * h,
                                   offsetY - sin * x1Data[x] - cos * h);
                
                float rW = (float) originalSize.width / width;
                float rH = (float) originalSize.height / height;
                
                int minX = (int) Math.round(Math.min(Math.min(p0.x, p1.x), Math.min(p2.x, p3.x)) * rW);
                int minY = (int) Math.round(Math.min(Math.min(p0.y, p1.y), Math.min(p2.y, p3.y)) * rH);
                int maxX = (int) Math.round(Math.max(Math.max(p0.x, p1.x), Math.max(p2.x, p3.x)) * rW);
                int maxY = (int) Math.round(Math.max(Math.max(p0.y, p1.y), Math.max(p2.y, p3.y)) * rH);
                rectangles.add(new Rect(minX, minY, maxX - minX, maxY - minY));
            }
        }
        
        return rectangles;
    }
}
