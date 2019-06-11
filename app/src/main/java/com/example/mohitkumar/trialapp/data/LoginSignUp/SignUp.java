package com.example.mohitkumar.trialapp.data.LoginSignUp;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class SignUp {

    @Expose(serialize = true)
    public String email;

    @Expose(serialize = true)
    public String password;

    @Expose(serialize = true)
    public String username;

}
