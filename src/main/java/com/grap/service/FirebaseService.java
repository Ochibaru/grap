package com.grap.service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firestore.v1.WriteResult;
import com.grap.dto.PantryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService implements IFirebaseService {

//    @Autowired
//    private Firestore firestore;
//
//    private CollectionReference getTestingCollection(){
//        return firestore.collection("Testing");
//    }

    @Override
    public void requestLoginFirebase() {

    }

    // Path to Firebase service account private key json
    private final String serviceAccountPath = "./serviceAccount.json";


    // Umers Attempt made by RJ
    // @PostConstruct - Initialize this class automatically once SpringBoot has finished starting
    @PostConstruct
    public void initialize() {
        try {
            // Store service account credentials
            FileInputStream serviceAccountFile = new FileInputStream(serviceAccountPath);
            GoogleCredentials serviceCredentials = GoogleCredentials.fromStream(serviceAccountFile);

            // Configure Firebase for Stara database
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(serviceCredentials)
                    .setDatabaseUrl("https://grap-c2990.firebaseio.com")
                    .build();

            // Initialize Firebase for Stara
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    public UserRecord getUser(String uid) throws FirebaseAuthException {
        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);

        System.out.println("Got user with pantry of " + userRecord.getEmail());

        return userRecord;
    }
}
