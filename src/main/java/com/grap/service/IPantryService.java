package com.grap.service;

import com.grap.dto.PantryDTO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface IPantryService {

    List<PantryDTO> fetch(String pantries) throws ExecutionException, InterruptedException;

    List<PantryDTO> fetchAll(String email) throws ExecutionException, InterruptedException;

    void saveCategory(PantryDTO pantry, String email, String id) throws ExecutionException, InterruptedException;

//    void saveItem(PantryDTO pantry, String email, String id) throws ExecutionException, InterruptedException;

    void deleteCategory(String email, String id) throws ExecutionException, InterruptedException;
}