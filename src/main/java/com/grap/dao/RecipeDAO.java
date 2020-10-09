package com.grap.dao;

import com.grap.dto.RecipeDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    @Override
    public List<RecipeDTO> fetch(String filepath) throws Exception{
        List<RecipeDTO> recipes = new ArrayList<>();

        String rawJson = "";
        try{
            rawJson = new String ( Files.readAllBytes( Paths.get(filepath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return getRecipesDTOS(recipes, rawJson);
    }


    private List<RecipeDTO> getRecipesDTOS(List<RecipeDTO> recipes, String rawJson) throws Exception {
        JSONObject obj = new JSONObject(rawJson);
        JSONArray movies = obj.getJSONArray("hits");
        //JSONObject recipe = movies.getJSONObject("recipe");

        for (int i = 0; i < movies.length(); i++) {

            // JSON Data
            JSONObject jsonRecipe = movies.getJSONObject(i);
            // Movie object that will be populated from JSON data
            RecipeDTO recipeDTO = new RecipeDTO();

            JSONObject recipe = jsonRecipe.getJSONObject("recipe");
//            Float calories = jsonRecipe.getFloat("calories");
            String label = recipe.getString("label");


            // populate the DTO with this information
//            recipeDTO.setCalories(calories);
            recipeDTO.setLabel(label);

            // add the populated movie to our collection
            recipes.add(recipeDTO);
        }

        return recipes;
    }

}
