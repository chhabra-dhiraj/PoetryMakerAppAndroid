package com.dhirajchhabra.poetrymaker.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Poem {

    @SerializedName("sentencesList")
    private ArrayList<SentenceDetails> sentences;

    public Poem() {
    }

    public ArrayList<SentenceDetails> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<SentenceDetails> sentences) {
        this.sentences = sentences;
    }
}
