
package com.grap.dto;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

public @Data
class  PantryDTO {

//    @SerializedName("ingredients")
//    private String ingredients;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("name")
    private String name;

    @SerializedName("measurement")
    private Double measurement;
}