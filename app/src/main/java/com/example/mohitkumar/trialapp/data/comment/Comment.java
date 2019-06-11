package com.example.mohitkumar.trialapp.data.comment;

import com.example.mohitkumar.trialapp.data.MainPage.Author;

import lombok.Data;

@Data
public class Comment {

    public int id;

    public String createdAt;

    public String updatedAt;

    public String body;

    public Author author;

}
