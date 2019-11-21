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

    @SerializedName("bold")
    private String bold;

    @SerializedName("fontColor")
    private String fontColor;

    @SerializedName("fontStyle")
    private String fontStyle;

    @SerializedName("fontSize")
    private String fontSize;

    @SerializedName("italic")
    private String italic;

    @SerializedName("underline")
    private String underline;

    public Poetry() {
    }

    protected Poetry(Parcel in) {
        poetryId = in.readString();
        title = in.readString();
        genre = in.readString();
        body = in.readString();
        bold = in.readString();
        fontColor = in.readString();
        fontStyle = in.readString();
        fontSize = in.readString();
        italic = in.readString();
        underline = in.readString();
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

    public String getBold() {
        return bold;
    }

    public void setBold(String bold) {
        this.bold = bold;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getItalic() {
        return italic;
    }

    public void setItalic(String italic) {
        this.italic = italic;
    }

    public String getUnderline() {
        return underline;
    }

    public void setUnderline(String underline) {
        this.underline = underline;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
