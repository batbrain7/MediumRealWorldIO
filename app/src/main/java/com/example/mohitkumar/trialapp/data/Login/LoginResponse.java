package com.example.mohitkumar.trialapp.data.Login;

public class LoginResponse {

    String email;
    String token;
    String username;
    String bio;
    String image;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LoginResponse(String email, String token, String username, String bio, String image) {

        this.email = email;
        this.token = token;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }
}
