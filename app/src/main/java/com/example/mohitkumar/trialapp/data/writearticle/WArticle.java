package com.example.mohitkumar.trialapp.data.writearticle;

import java.util.List;

import lombok.Data;

@Data
public class WArticle {
    public String title;
    public String description;
    public String body;
    public List<String> tagList;
}
