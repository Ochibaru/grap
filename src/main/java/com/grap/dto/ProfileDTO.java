package com.grap.dto;

import com.google.firebase.database.annotations.NotNull;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

public @Data class ProfileDTO{
    @NotNull
    @SerializedName("uid")
    private String uid;

    @SerializedName("displayName")
    private String displayName;

    @SerializedName("photoURL")
    private String photoURL;

    @SerializedName("password")
    private String password;
    private String matchingPassword;

    @SerializedName("email")
    private String email;

    @SerializedName("phoneNumber")
    private String phoneNumber;
}