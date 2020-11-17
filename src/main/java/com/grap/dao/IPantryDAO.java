package com.grap.dao;

import com.grap.dto.PantryDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IPantryDAO {

//    List<PantryDTO> fetch() throws Exception;

    // https://www.youtube.com/watch?v=ScsID2yPB8k
    // Firebase Reference: https://firebase.google.com/docs/firestore/query-data/get-data
    List<PantryDTO> fetch(String pantries) throws ExecutionException, InterruptedException;
}
