package com.grap.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.grap.dto.ProfileDTO;
import com.grap.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProfileDAO {
    @Autowired
    FirebaseService firebaseService;

    public void saveProfile(ProfileDTO profile, String uid) throws FirebaseAuthException {
        String email = profile.getEmail();
        String phoneNumber = profile.getPhoneNumber();
        String password = profile.getPassword();
        String displayName = profile.getDisplayName();
        String photoURL = profile.getPhotoURL();

        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(uid)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setPassword(password)
                .setDisplayName(displayName)
                .setPhotoUrl(photoURL);

        UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
        System.out.println("Successfully updated user: " + userRecord.getUid());
    }

}
