package com.grap.dao;

import com.grap.dto.RecipeDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchDAO implements ISearchDAO{

    @Autowired
    private NetworkDAO networkDAO;

    // May have to encode if user enters a special character
    @Override
    public List<RecipeDTO> fetch(String searchTerm) throws Exception {
        List<RecipeDTO> searchResults = new ArrayList<>();
        String endpoint = "https://api.edamam.com/search?app_id=47c17ed5";
        String api = "&app_key=b53ffdd94b4b532aee16d1502bca8359&q=";
        String rawJson = networkDAO.request(endpoint + api + searchTerm);
        return getSearchRecipesDTOS(searchResults, rawJson);
    }

    private List<RecipeDTO> getSearchRecipesDTOS(List<RecipeDTO> searchResults, String rawJson) {
        JSONObject obj = new JSONObject(rawJson);
        JSONArray recipes = obj.getJSONArray("hits");

        for (int i = 0; i < recipes.length(); i++) {

            // JSON Data
            JSONObject jsonRecipe = recipes.getJSONObject(i);
            JSONObject recipe = jsonRecipe.getJSONObject("recipe");

            // Skip over results that don't have a poster_path
//            if(!(jsonRecipe.get("poster_path") instanceof String)) {
//                continue;
//            }

            // Recipe object that will be populated from JSON data
            RecipeDTO recipeDTO = new RecipeDTO();

            Float calories = recipe.getFloat("calories");
            String label = recipe.getString("label");
            String image = recipe.getString("image");

            // Populate the DTO with this information
            recipeDTO.setCalories(calories);
            recipeDTO.setLabel(label);
            recipeDTO.setImage(image);

            // Add the populated movie to our collection
            searchResults.add(recipeDTO);
        }
        return searchResults;
    }
}
