package com.example.mohitkumar.trialapp.data.LoginSignUp;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class User {

    @Expose(serialize = true)
    public LoginSignUpResponse user;




}
