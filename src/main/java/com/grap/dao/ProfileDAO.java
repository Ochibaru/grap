package com.grap.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuthException;
import com.grap.dto.ProfileDTO;
import com.grap.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class ProfileDAO implements IProfileDAO{

    @Autowired
    FirebaseService firebaseService;

    // Firebase Reference: https://firebase.google.com/docs/firestore/query-data/get-data
    @Override
    public List<ProfileDTO> fetch(String email) throws ExecutionException, InterruptedException {
        List<ProfileDTO> profile = new ArrayList<>();

        ApiFuture<QuerySnapshot> querySnapshot = firebaseService.getFirestore()
                .collection("users")
                .document(email)
                .collection("profile")
                .get();

        List<QueryDocumentSnapshot> documents = null;
        documents = querySnapshot.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            profile.add(document.toObject(ProfileDTO.class));
        }

        return profile;
    }


    /**
     * Add a document to a collection using a map.
     *
     * @return document data
     */
//    Map<String, Object> addSimpleDocumentAsMap() throws Exception {
//        // [START fs_add_doc_as_map]
//        // [START firestore_data_set_from_map]
//        // Create a Map to store the data we want to set
//        Map<String, Object> docData = new HashMap<>();
//        docData.put("name", "Los Angeles");
//        docData.put("state", "CA");
//        docData.put("country", "USA");
//        docData.put("regions", Arrays.asList("west_coast", "socal"));
//        // Add a new document (asynchronously) in collection "cities" with id "LA"
//        ApiFuture<WriteResult> future = db.collection("cities").document("LA").set(docData);
//        // ...
//        // future.get() blocks on response
//        System.out.println("Update time : " + future.get().getUpdateTime());
//        // [END firestore_data_set_from_map]
//        // [END fs_add_doc_as_map]
//        return docData;
//    }

//    /** Partially update fields of a document using a map (field => value). */
//    void updateAndCreateIfMissing() throws Exception {
//        // [START fs_update_create_if_missing]
//        // [START firestore_data_set_doc_upsert]
//        //asynchronously update doc, create the document if missing
//        Map<String, Object> update = new HashMap<>();
//        update.put("capital", true);
//
//        ApiFuture<WriteResult> writeResult =
//                db
//                        .collection("cities")
//                        .document("BJ")
//                        .set(update, SetOptions.merge());
//        // ...
//        System.out.println("Update time : " + writeResult.get().getUpdateTime());
//        // [END firestore_data_set_doc_upsert]
//        // [END fs_update_create_if_missing]
//    }

    // Firebase Reference: https://firebase.google.com/docs/firestore/query-data/get-data
//    @Override
//    public List<ProfileDTO> fetch(String uid) throws ExecutionException, InterruptedException, FirebaseAuthException {
//        List<ProfileDTO> profile = new ArrayList<>();
//        // String email = firebaseService.getUser(uid).getEmail();
//
//        // System.out.println("Got user with pantry of " + email);
//
//
//        ApiFuture<QuerySnapshot> querySnapshot = firebaseService.getFirestore()
//                .collection("users")
//                .document(firebaseService.getUser(uid).getEmail())
//                .collection("profile")
//                .get();
//
//        List<QueryDocumentSnapshot> documents = null;
//        documents = querySnapshot.get().getDocuments();
//
//        for (QueryDocumentSnapshot document : documents) {
//            profile.add(document.toObject(ProfileDTO.class));
//        }
//
//        return profile;
//    }

//    @Override
//    public void setProfile(String email, String id) throws ExecutionException, InterruptedException {
//        ApiFuture<WriteResult> writeResult = firebaseService.getFirestore()
//                .collection("users")
//                .document(email)
//                .collection("profile")
//                .document(id)
//
//        System.out.println("profile with " + id + "added at time: " + writeResult.get().getUpdateTime());
//    }

    // Firebase Reference: https://firebase.google.com/docs/firestore/manage-data/add-data
//    @Override
//    public void setProfile(ProfileDTO profile, String email, String id) throws ExecutionException, InterruptedException {
//        ApiFuture<WriteResult> writeResult = firebaseService.getFirestore()
//                .collection("users")
//                .document(email)
//                .collection("profile")
//                .document(id)
//                .set(profile);
//
//        System.out.println("profile with " + id + " added at time: " + writeResult.get().getUpdateTime());
//    }

    @Override
    public void saveProfile(ProfileDTO profile, String email, String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = firebaseService.getFirestore()
                .collection("users")
                .document(email)
                .collection("profile")
                .document(id)
                .set(profile, SetOptions.merge());

        System.out.println("profile with " + id + " added at time: " + writeResult.get().getUpdateTime());
    }

}