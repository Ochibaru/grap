package com.grap.dao;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.grap.dto.PantryDTO;
import com.grap.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    public void saveCategory(PantryDTO pantry, String email, String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = firebaseService.getFirestore()
                .collection("users")
                .document(email)
                .collection("pantry")
                .document(id)
                .set(pantry);

        System.out.println("Pantry with " + id + "added at time: " + writeResult.get().getUpdateTime());
    }

//    @Override
//    public void saveItem(PantryDTO pantry, String email, String id) throws ExecutionException, InterruptedException {
//        ApiFuture<WriteResult> writeResult = firebaseService.getFirestore()
//                .collection("users")
//                .document(email)
//                .collection("pantry")
//                .document(id)
//                .collection(item)
//                .set(pantry);
//
//        System.out.println("Pantry with " + id + "added at time: " + writeResult.get().getUpdateTime());
//    }

    // Firebase Reference: https://firebase.google.com/docs/firestore/manage-data/delete-data
    @Override
    @CacheEvict(value = "pantry", allEntries = true)
    public void deleteCategory(String email, String id) {
        ApiFuture<WriteResult> writeResult = firebaseService.getFirestore()
                .collection("users")
                .document(email)
                .collection("pantry")
                .document(id)
                .delete();
    }
}