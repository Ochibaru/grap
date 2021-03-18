package com.grap.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.grap.dao.IPantryDAO;
import com.grap.dao.IProfileDAO;
import com.grap.dto.PantryDTO;
import com.grap.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProfileService implements IProfileService {

    @Autowired
    private IProfileDAO profileDAO;

//    @Override
//    public List<ProfileDTO> fetch(String profile) throws ExecutionException, InterruptedException {
//        return profileDAO.fetch(profile);
//    }

    @Override
    public List<ProfileDTO> fetch(String profile) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return profileDAO.fetch(profile);
    }

    @Override
    public void saveProfile(ProfileDTO profile, String email, String id) throws ExecutionException, InterruptedException {
        profileDAO.saveProfile(profile, email, id);
    }

//    @Override
//    public void setProfile(ProfileDTO profile) throws ExecutionException, InterruptedException {
//        profileDAO.saveProfile(profile);
//    }
}