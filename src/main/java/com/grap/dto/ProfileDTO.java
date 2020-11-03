package com.grap.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.springframework.lang.NonNull;

public class ProfileDTO{

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String address;

}