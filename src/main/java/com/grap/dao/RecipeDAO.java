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
public class RecipeDAO implements IRecipeDAO {
    @Autowired
    private NetworkDAO networkDAO;

    @Override
    public List<RecipeDTO> fetch() throws Exception {
        List<RecipeDTO> recipes = new ArrayList<>();
        String rawJson = networkDAO.request("https://api.edamam.com/search?app_id=47c17ed5&app_key=b53ffdd94b4b532aee16d1502bca8359&q=");
        return getRecipesDTOS(recipes, rawJson);
    }

    @Override
    public List<RecipeDTO> fetch(String filepath) throws Exception {
        List<RecipeDTO> recipes = new ArrayList<>();

        String rawJson = "";
        try {
            rawJson = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getRecipesDTOS(recipes, rawJson);
    }

    private List<RecipeDTO> getRecipesDTOS(List<RecipeDTO> recipes, String rawJson) throws Exception {
        //String queryParam = rawJson + "pizza";
        JSONObject obj = new JSONObject(rawJson);
        JSONArray recipeBook = obj.getJSONArray("hits");
        // allFood.json attempt to place all local json to one file, also need to change RecipeService
        // JSONArray recipeBook = obj.getJSONArray("all");

        for (int i = 0; i < recipeBook.length(); i++) {

            // JSON Data
            JSONObject jsonRecipe = recipeBook.getJSONObject(i);
            JSONObject recipe = jsonRecipe.getJSONObject("recipe");
            RecipeDTO recipeDTO = new RecipeDTO();

            // allFood.json attempt to place all local json to one file, also need to change RecipeService
//            JSONObject jsonRecipe = recipeBook.getJSONObject(i);
//            JSONArray recipeType = jsonRecipe.getJSONArray("hits");
//            JSONObject type = recipeType.getJSONObject(i);
//            JSONObject recipe = type.getJSONObject("recipe");
//            RecipeDTO recipeDTO = new RecipeDTO();

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

    @Override
    public List<RecipeDTO> fetchSpoonData(String searchTerm) throws Exception{
        List<RecipeDTO> spoonRecipes = new ArrayList<>();
        String endpoint = "https://api.spoonacular.com/recipes/search?";
        String api = "apiKey=20f0acb7d3ac49f0aa61782da562de2e&cuisine=";
        String rawJson = networkDAO.request(endpoint + api);
        return getSpoonRecipesDTOS(spoonRecipes, rawJson + searchTerm);

//        List<RecipeDTO> searchResults = new ArrayList<>();
//        String endpoint = "https://api.edamam.com/search?app_id=47c17ed5";
//        String api = "&app_key=b53ffdd94b4b532aee16d1502bca8359&q=";
//        String rawJson = networkDAO.request(endpoint + api + searchTerm);
    }


    @Override
    public List<RecipeDTO> fetchSpoon() throws Exception{
        List<RecipeDTO> spoonRecipes = new ArrayList<>();
        String rawJson = networkDAO.request("https://api.spoonacular.com/recipes/search?apiKey=20f0acb7d3ac49f0aa61782da562de2e&cuisine=american");
        return getSpoonRecipesDTOS(spoonRecipes, rawJson);
    }

    @Override
    public List<RecipeDTO> fetchSpoon(String filepath) throws Exception{
        List<RecipeDTO> spoonRecipes = new ArrayList<>();

        String rawJson = "";
        try{
            rawJson = new String ( Files.readAllBytes( Paths.get(filepath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return getSpoonRecipesDTOS(spoonRecipes, rawJson);
    }

    private List<RecipeDTO> getSpoonRecipesDTOS(List<RecipeDTO> spoonRecipes, String rawJson) throws Exception {
        JSONObject obj = new JSONObject(rawJson);
        JSONArray recipeBook = obj.getJSONArray("results");

        for (int i = 0; i < recipeBook.length(); i++) {

            // JSON Data
            JSONObject recipe = recipeBook.getJSONObject(i);
            RecipeDTO recipeDTO = new RecipeDTO();

            String title = recipe.getString("title");
            Integer readyInMinutes = recipe.getInt("readyInMinutes");
            String image = recipe.getString("image");
            Integer servings = recipe.getInt("servings");
            String sourceUrl = recipe.getString("sourceUrl");

            // Populate the DTO with this information
            recipeDTO.setTitle(title);
            recipeDTO.setReadyInMinutes(readyInMinutes);
            recipeDTO.setImage(image);
            recipeDTO.setServings(servings);
            recipeDTO.setSourceUrl(sourceUrl);

            // add the populated movie to our collection
            spoonRecipes.add(recipeDTO);
        }
        return spoonRecipes;
    }
}