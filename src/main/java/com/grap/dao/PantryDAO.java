package com.grap.dao;


import com.grap.dto.PantryDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PantryDAO implements  IPantryDAO {

    @Autowired
    private NetworkDAO networkDAO;

    @Override
    public List<PantryDTO> fetchAllPantryItems() throws Exception {
        List<PantryDTO> pantries = new ArrayList<>();
        return getPantriesDTOS(pantries, rawJson);

    }

    private List<PantryDTO> getPantriesDTOS(List<PantryDTO> pantries, String rawJson) {
        JSONObject obj = new JSONObject(rawJson);
        JSONArray pantry = obj.getJSONArray("hits");
        return pantries;
    }
}





