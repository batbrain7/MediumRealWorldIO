package com.example.mohitkumar.trialapp.data.LoginSignUp;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class SUser {

    @Expose(serialize = true)
   SignUp user;
}
