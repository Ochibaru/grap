package com.grap.dao;

import com.grap.dto.NutritionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NutritionDAO implements INutritionDAO {

    @Autowired
    private NetworkDAO networkDAO;

    @Override
    public void fetchNutritionDAO(String searchNutrition) throws Exception {
        NutritionDTO nutritionDTO = new NutritionDTO();

        //networkDAO.requestJSON();
        String uri = nutritionDTO.getUri();

        return;
    }
}