package com.example.testofcliantandserverjavafx;

import java.io.Serializable;

public class BMIData implements Serializable {

    private double weight;
    private double height;
    private double BMI;

    public BMIData(double weight, double height) {
        this.weight = weight;
        this.height = height;
        BMI = weight/(height*height);
    }

    public double getBMI() {
        return BMI;
    }
}
