package com.grap.dao;

import com.grap.dto.PantryDTO;

import java.io.IOException;
import java.util.List;

public interface IPantryDAO {

    List<PantryDTO> fetchAllPantryItems() throws IOException;

}
