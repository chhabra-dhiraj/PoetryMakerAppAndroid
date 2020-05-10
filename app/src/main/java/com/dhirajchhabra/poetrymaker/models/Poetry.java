package com.dhirajchhabra.poetrymaker.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Poetry implements Parcelable {

    @SerializedName("poetryId")
    private String poetryId;

    @SerializedName("title")
    private String title;

    @SerializedName("genre")
    private String genre;

    @SerializedName("body")
    private String body;

    public Poetry() {
    }

    protected Poetry(Parcel in) {
        poetryId = in.readString();
        title = in.readString();
        genre = in.readString();
        body = in.readString();
    }

    public static final Creator<Poetry> CREATOR = new Creator<Poetry>() {
        @Override
        public Poetry createFromParcel(Parcel in) {
            return new Poetry(in);
        }

        @Override
        public Poetry[] newArray(int size) {
            return new Poetry[size];
        }
    };

    public String getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(String poetryId) {
        this.poetryId = poetryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poetryId);
        dest.writeString(title);
        dest.writeString(genre);
        dest.writeString(body);
    }
}
