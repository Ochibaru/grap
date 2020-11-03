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

    @Override
    public String toString() {
        return label + calories + image;
    }
}