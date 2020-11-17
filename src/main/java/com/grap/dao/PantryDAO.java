package com.grap.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.grap.dto.PantryDTO;
import com.grap.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class PantryDAO implements IPantryDAO{

    @Autowired
    FirebaseService firebaseService;

    // https://www.youtube.com/watch?v=ScsID2yPB8k
    // Firebase Reference: https://firebase.google.com/docs/firestore/query-data/get-data
    // Thanks Umer
    @Override
    public List<PantryDTO> fetch(String pantries) throws ExecutionException, InterruptedException {
        List<PantryDTO> pantry = new ArrayList<>();

        ApiFuture<QuerySnapshot> querySnapshot = firebaseService.getFirestore()
                .collection("Users")
                .get();

        List<QueryDocumentSnapshot> documents = null;
        documents = querySnapshot.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            pantry.add(document.toObject(PantryDTO.class));
        }

        return pantry;
    }

    // Firebase Reference: https://firebase.google.com/docs/firestore/query-data/get-data
    @Override
    public List<PantryDTO> fetchAll(String email) throws ExecutionException, InterruptedException {
        List<PantryDTO> pantries = new ArrayList<>();

        ApiFuture<QuerySnapshot> querySnapshot = firebaseService.getFirestore()
                .collection("users")
                .document(email)
                .collection("pantry")
                .get();

        List<QueryDocumentSnapshot> documents = null;
        documents = querySnapshot.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            pantries.add(document.toObject(PantryDTO.class));
        }

        return pantries;
    }

    // Firebase Reference: https://firebase.google.com/docs/firestore/manage-data/add-data
    @Override
    public void save(Map<String, String> pantryData, String email, String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = firebaseService.getFirestore()
                .collection("users")
                .document(email)
                .collection("pantry")
                .document(id)
                .set(pantryData);

        System.out.println("Pantry with " + id + "added at time: " + writeResult.get().getUpdateTime());
    }
}