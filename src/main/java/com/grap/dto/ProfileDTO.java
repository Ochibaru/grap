package com.grap.dto;

import com.google.firebase.database.annotations.NotNull;
import lombok.Data;

public @Data class ProfileDTO{
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String password;
    private String matchingPassword;

    @NotNull
    private String email;

}