package com.grap.dao;

import com.grap.dto.RecipeDTO;

import java.util.List;

public interface IRecipeDAO {
    List<RecipeDTO> fetch() throws Exception;

    List<RecipeDTO> fetch(String filepath) throws Exception;
}
