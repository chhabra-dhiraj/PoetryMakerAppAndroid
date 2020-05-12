package com.dhirajchhabra.poetrymaker.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SentenceDetails {

    @SerializedName("author")
    private String author;
    @SerializedName("poemName")
    private String poemName;
    @SerializedName("sentence")
    private String sentence;
    @SerializedName("age")
    private String age;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPoemName() {
        return poemName;
    }

    public void setPoemName(String poemName) {
        this.poemName = poemName;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
