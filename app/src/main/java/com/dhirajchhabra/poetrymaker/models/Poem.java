package com.dhirajchhabra.poetrymaker.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Poem {

    @SerializedName("beforeWords")
    private ArrayList<String> beforeWords;
    @SerializedName("afterWords")
    private ArrayList<String> afterWords;

    public Poem() {
    }

    public ArrayList<String> getBeforeWords() {
        return beforeWords;
    }

    public void setBeforeWords(ArrayList<String> beforeWords) {
        this.beforeWords = beforeWords;
    }

    public ArrayList<String> getAfterWords() {
        return afterWords;
    }

    public void setAfterWords(ArrayList<String> afterWords) {
        this.afterWords = afterWords;
    }
}
