package com.grap.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.grap.dto.PantryDTO;
import com.grap.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class PantryDAO implements IPantryDAO{

    @Autowired
    FirebaseService firebaseService;

    // https://www.youtube.com/watch?v=ScsID2yPB8k
    // Firebase Reference: https://firebase.google.com/docs/firestore/query-data/get-data
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

//        ApiFuture<DocumentSnapshot> documentSnapshot = firebaseService.getFirestore()
//                .collection("Users")
//                .document(pantries)
//                .get();
//
//        DocumentSnapshot documents = null;
//        documents = documentSnapshot.get();
//
////        for (DocumentSnapshot document : documents) {
//            pantry.add(documents.toObject(PantryDTO.class));
////        }

        return pantry;
    }
}
