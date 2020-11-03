package com.grap.service;

import com.grap.dao.IRecipeDAO;
import com.grap.dto.RecipeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        // nowPlayingMovies = nowPlayingDAO.fetch("src\\test\\resources\\nowplayingmovieresults.json");
        recipes = recipeDAO.fetch();

        try{
            if (recipes != null) {
                return recipes;
            }
        } catch (NullPointerException  e) {
            System.out.print("NullPointerException Caught for recipes.fetch()");
        }

        return Collections.emptyList();
    }
}
