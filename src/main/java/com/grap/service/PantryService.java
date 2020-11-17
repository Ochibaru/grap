package com.grap.service;

import com.grap.dao.IPantryDAO;
import com.grap.dto.PantryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PantryService implements IPantryService {
    @Autowired
    private IPantryDAO pantryDAO;

    @Override
    public List<PantryDTO> fetch(String pantries) throws ExecutionException, InterruptedException {
        return pantryDAO.fetch(pantries);
    }
}
