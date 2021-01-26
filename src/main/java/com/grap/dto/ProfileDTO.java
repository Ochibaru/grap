package com.grap.dto;

import com.google.firebase.database.annotations.NotNull;
import lombok.Data;

public @Data class ProfileDTO{
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String password;
    private String matchingPassword;

    private String email;

}