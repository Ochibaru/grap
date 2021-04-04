package com.grap.dto;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

public @Data
class  PantryDTO {

    @SerializedName("ingredients")
    private String ingredients;

    @SerializedName("id")
    private String id;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("name")
    private String name;

    @SerializedName("measurement")
    private String measurement;


}