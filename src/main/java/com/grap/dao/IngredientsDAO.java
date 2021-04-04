package com.grap.dao;

import com.grap.dto.IngredientsDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientsDAO implements IIngredientsDAO {

    @Override
    public List<IngredientsDTO> fetch() throws Exception{
        List<IngredientsDTO> searchIngredientsResults = new ArrayList<>();
        return getIngredientsList(searchIngredientsResults);
    }


    private List<IngredientsDTO> getIngredientsList(List<IngredientsDTO> searchIngredientsResults){
        JSONObject obj = new JSONObject(fetchIngredientList());
        JSONArray ingredients = obj.getJSONArray("recipes");

        JSONObject obj2 = obj.getJSONObject("ingredients");
        JSONArray recipes = obj2.getJSONArray("ingredients");
        //System.out.println("Ingredient Search List: " + ingredients.toString());
        for (int i = 0; i < recipes.length(); i++) {

            JSONObject jsonIngredient = recipes.getJSONObject(i);
            IngredientsDTO ingredientsDTO = new IngredientsDTO();
            System.out.println("Ingredient Search List: " + recipes.toString());
            String ingredientsString = jsonIngredient.getString("ingredients");

            ingredientsDTO.setIngredients(ingredientsString);
            searchIngredientsResults.add(ingredientsDTO);
        }

        for (int i = 0; i < ingredients.length(); i++) {

            JSONObject jsonIngredient = ingredients.getJSONObject(i);
            IngredientsDTO ingredientsDTO = new IngredientsDTO();
            System.out.println("Ingredient Search List: " + ingredients.toString());
            String ingredientsString = jsonIngredient.getString("ingredients");

            ingredientsDTO.setIngredients(ingredientsString);
            searchIngredientsResults.add(ingredientsDTO);
        }
        return searchIngredientsResults;
    }

    private String fetchIngredientList(){
        String rawJson = "";
        try{
            rawJson = new String ( Files.readAllBytes( Paths.get("src\\main\\resources\\static\\data\\formatingredients.json") ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return rawJson;
    }
}
