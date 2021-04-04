package com.grap.dao;
import com.google.firebase.auth.FirebaseAuthException;
import com.grap.dto.ProfileDTO;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IProfileDAO {

    List<ProfileDTO> fetch(String email) throws ExecutionException, InterruptedException;

//    List<ProfileDTO> fetch(String uid) throws ExecutionException, InterruptedException, FirebaseAuthException;

    void saveProfile(ProfileDTO profile, String email, String id) throws ExecutionException, InterruptedException;
}