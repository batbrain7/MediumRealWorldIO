package com.example.mohitkumar.trialapp.data.Login;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class User {

    @Expose(serialize = true)
    public LoginResponse user;




}
