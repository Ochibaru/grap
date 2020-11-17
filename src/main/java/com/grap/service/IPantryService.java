package com.grap.service;

import com.grap.dto.PantryDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IPantryService {

    List<PantryDTO> fetch(String pantries) throws ExecutionException, InterruptedException;
}
