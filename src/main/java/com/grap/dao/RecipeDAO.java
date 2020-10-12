package com.grap.dao;

import com.grap.dto.RecipeDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeDAO implements IRecipeDAO{
    @Autowired
    private NetworkDAO networkDAO;

    @Override
    public List<RecipeDTO> fetch() throws Exception{
        List<RecipeDTO> recipes = new ArrayList<>();
        String rawJson = networkDAO.request("https://api.edamam.com/search?app_id=47c17ed5&app_key=b53ffdd94b4b532aee16d1502bca8359&q=pizza");
        return getRecipesDTOS(recipes, rawJson);
    }

    private List<RecipeDTO> getRecipesDTOS(List<RecipeDTO> recipes, String rawJson) throws Exception {
        JSONObject obj = new JSONObject(rawJson);
        JSONArray recipeBook = obj.getJSONArray("hits");

        for (int i = 0; i < recipeBook.length(); i++) {

            // JSON Data
            JSONObject jsonRecipe = recipeBook.getJSONObject(i);
            JSONObject recipe = jsonRecipe.getJSONObject("recipe");
            RecipeDTO recipeDTO = new RecipeDTO();

            Float calories = recipe.getFloat("calories");
            String label = recipe.getString("label");
            String image = recipe.getString("image");

            // populate the DTO with this information
            recipeDTO.setCalories(calories);
            recipeDTO.setLabel(label);
            recipeDTO.setImage(image);

            // add the populated movie to our collection
            recipes.add(recipeDTO);
        }
        return recipes;
    }
}
