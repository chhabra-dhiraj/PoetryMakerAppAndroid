package com.dhirajchhabra.poetrymaker.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("userId")
    private String userId;
    @SerializedName("poetries")
    private ArrayList<Poetry> poetries;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Poetry> getPoetries() {
        return poetries;
    }

    public void setPoetries(ArrayList<Poetry> poetries) {
        this.poetries = poetries;
    }
}
