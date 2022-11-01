package com.example.scsebuddy.requestsresults;

import java.util.ArrayList;

public class ForumPost {
    //    private String code;
//    private String title; //change
//    private String aus;
//    private int favorite;
//    private int year;

    private String datePublished;
    private String title;
    private String description;
    private String name;
    private String topicID;
    private String email;
    private ArrayList <String> tags;

    public String getEmail() {
        return email;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getTopicID() {
        return topicID;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
