package com.grap.dao;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class FirebaseDAO implements IFirebaseDAO {

    @Override
    public void requestLoginFirebase() {
        serviceAccount();
    }

    public void createFirebaseEntry(String collectionDocumentPath){
        serviceAccount();
        Firestore db = FirestoreClient.getFirestore();
        LocalDate current = LocalDate.now();

        db.collection(collectionDocumentPath).document(String.valueOf(current));

    }

    private void serviceAccount() {
        FileInputStream serviceFirebaseAccount =
                null;
        try {
            serviceFirebaseAccount = new FileInputStream("src/test/resources/service_account_pk.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseOptions options = null;
        try {
            assert serviceFirebaseAccount != null;
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceFirebaseAccount))
                    .setDatabaseUrl("https://grap-c2990.firebaseio.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert options != null;
        FirebaseApp.initializeApp(options);
    }
}
