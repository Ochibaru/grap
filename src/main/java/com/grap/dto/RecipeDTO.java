package com.grap.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

public @Data
class RecipeDTO{

    @SerializedName("calories")
    @Expose
    private Float calories;
    @SerializedName("label")
    @Expose
    private String label;


//    @SerializedName("ingredients")
//    @Expose
//    private int ingredients;
//    @SerializedName("calories")
//    @Expose
//    private String calories;
//
//
//    public int getIngredients() {
//        return ingredients;
//    }
//
//    public String getCalories() {
//        return calories;
//    }
//
    @Override
    public String toString() {
        return label;

    }
}