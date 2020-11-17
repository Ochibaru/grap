package com.grap.service;

import com.grap.dao.IPantryDAO;
import com.grap.dto.PantryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class PantryService implements IPantryService {
    @Autowired
    private IPantryDAO pantryDAO;

    @Override
    public List<PantryDTO> fetch(String pantries) throws ExecutionException, InterruptedException {
        return pantryDAO.fetch(pantries);
    }

    @Override
    public List<PantryDTO> fetchAll(String email) throws ExecutionException, InterruptedException {
        return pantryDAO.fetchAll(email);
    }

    @Override
    public void save(Map<String, String> pantryData, String email, String id) throws ExecutionException, InterruptedException {
        pantryDAO.save(pantryData, email, id);
    }
}