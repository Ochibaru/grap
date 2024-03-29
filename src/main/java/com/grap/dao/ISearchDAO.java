package com.grap.dao;

import com.grap.dto.RecipeDTO;

import java.util.List;

public interface ISearchDAO {
    List<RecipeDTO> fetch(String searchTerm) throws Exception;
}
