package com.grap.service;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService implements IFirebaseService {

    @Autowired
    private Firestore firestore;

    private CollectionReference getTestingCollection(){
        return firestore.collection("Testing");
    }

    @Override
    public void requestLoginFirebase() {

    }
}
