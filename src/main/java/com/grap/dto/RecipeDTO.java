package com.grap.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

public @Data
class RecipeDTO{

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("calories")
    @Expose
    private Float calories;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("mealType")
    @Expose
    private String mealType;

    @SerializedName("dishType")
    @Expose
    private String dishType;

    @SerializedName("cuisineType")
    @Expose
    private String cuisineType;
}