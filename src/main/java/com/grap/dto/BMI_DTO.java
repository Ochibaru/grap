package com.grap.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


public @Data
    class BMI_DTO {

    @SerializedName("weight")
    private double weight;

    @SerializedName("height")
    private double height;

    @SerializedName("age")
    private int age;

    @SerializedName("gender")
    private String gender;

    @SerializedName("bmi")
    private double bmi;

}
