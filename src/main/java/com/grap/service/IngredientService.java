package com.grap.service;

import com.grap.dao.IIngredientsDAO;
import com.grap.dto.IngredientsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class IngredientService {

    @Autowired
    IIngredientsDAO ingredientsDAO;


    private List<IngredientsDTO> fetchIngredientsList(String searchTerm) throws Exception{
        List<IngredientsDTO> ingredientList;

        ingredientList = ingredientsDAO.fetch();

        try{
            if (ingredientList != null) {
                return ingredientList;
            }
        } catch (NullPointerException e) {
            System.out.print("NullPointerException Caught for nowPlayingMovies.fetch()");
        }
        return Collections.emptyList();
    }
}
