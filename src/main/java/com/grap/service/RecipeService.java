package com.grap.service;

import com.grap.dao.IRecipeDAO;
import com.grap.dao.RecipeDAO;
import com.grap.dto.RecipeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class RecipeService implements IRecipeService{
    @Autowired
    IRecipeDAO recipeDAO;

    @Override
    public List<RecipeDTO> fetchRecipes() throws Exception{
        List<RecipeDTO> recipes;

        // This is because I am unable to make Network requests at work, uncomment line 27 and comment line 26 to switch to the actual network requests
        recipes = recipeDAO.fetch("src\\main\\resources\\static\\data\\pizza.json");
//      recipes = recipeDAO.fetch();

        // allFood.json attempt to place all local json to one file, also need to change RecipeService
        // recipes = recipeDAO.fetch("src\\main\\resources\\static\\data\\allFood.json");

        try{
            if (recipes != null) {
                return recipes;
            }
        } catch (NullPointerException  e) {
            System.out.print("NullPointerException Caught for recipes.fetch()");
        }

        return Collections.emptyList();
    }

    @Override
    public List<RecipeDTO> fetchSpoonRecipes() throws Exception{
        List<RecipeDTO> spoonRecipes;

        // This is because I am unable to make Network requests at work, uncomment line 27 and comment line 26 to switch to the actual network requests
        spoonRecipes = recipeDAO.fetchSpoon("src\\main\\resources\\static\\data\\american.json");
        // spoonRecipes = recipeDAO.fetchSpoon();

        // allFood.json attempt to place all local json to one file, also need to change RecipeService
        // recipes = recipeDAO.fetch("src\\main\\resources\\static\\data\\allFood.json");

        try{
            if (spoonRecipes != null) {
                return spoonRecipes;
            }
        } catch (NullPointerException  e) {
            System.out.print("NullPointerException Caught for recipes.fetch()");
        }

        return Collections.emptyList();
    }

    public List<RecipeDTO> fetchSpoonRecipesFromPantry(String url) throws Exception{
        List<RecipeDTO> spoonRecipesFromPantry = new ArrayList<>();
        String blank = "";

        spoonRecipesFromPantry = recipeDAO.fetch(url,blank);

        try{
            if (spoonRecipesFromPantry != null) {
                return spoonRecipesFromPantry;
            }
        } catch (NullPointerException  e) {
            System.out.print("NullPointerException Caught for recipes.fetch()");
        }

        return Collections.emptyList();
    }


}