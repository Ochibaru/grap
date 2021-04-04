package com.grap.dto;

import lombok.Data;

public @Data class IngredientsDTO {

    private String ingredients;

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString(){
        return "Ingredients List: " + ingredients;
    }
}
