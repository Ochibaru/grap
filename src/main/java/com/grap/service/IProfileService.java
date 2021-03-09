package com.grap.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.grap.dto.PantryDTO;
import com.grap.dto.ProfileDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IProfileService {

//    List<ProfileDTO> fetch(String profile) throws ExecutionException, InterruptedException;

    List<ProfileDTO> fetch(String profile) throws ExecutionException, InterruptedException, FirebaseAuthException;

    void saveProfile(ProfileDTO profile, String email, String id) throws ExecutionException, InterruptedException;

//    void setProfile(ProfileDTO profile) throws ExecutionException, InterruptedException;
}
