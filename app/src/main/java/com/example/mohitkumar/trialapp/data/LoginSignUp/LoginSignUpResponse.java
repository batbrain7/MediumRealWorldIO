package com.example.mohitkumar.trialapp.data.LoginSignUp;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class LoginSignUpResponse {

    @Expose(serialize = true)
    public String email;

    @Expose(serialize = true)
    public String token;

    @Expose(serialize = true)
    public String username;

    @Expose(serialize = true)
    public String bio;

    @Expose(serialize = true)
    public String image;
}
