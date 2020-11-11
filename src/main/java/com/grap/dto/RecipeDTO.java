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

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("Time")
    @Expose
    private String readyInMinutes;

    @SerializedName("servings")
    @Expose
    private String servings;

    @SerializedName("sourceUrl")
    @Expose
    private String sourceUrl;
}