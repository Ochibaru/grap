package com.grap.dao;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitializer {

    @PostConstruct
    public void  initialize() throws IOException {

        FileInputStream serviceAccount = new FileInputStream("src/test/resources/service_account_pk.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://grap-c2990.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
