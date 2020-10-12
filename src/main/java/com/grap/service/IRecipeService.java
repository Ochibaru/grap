package com.grap.service;

import com.grap.dto.RecipeDTO;

import java.util.List;

public interface IRecipeService {
    List<RecipeDTO> fetchRecipes() throws Exception;
}
