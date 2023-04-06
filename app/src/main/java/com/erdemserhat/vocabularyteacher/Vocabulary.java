package com.erdemserhat.vocabularyteacher;

import java.io.Serializable;

public class Vocabulary implements Serializable {
    private String name;
    private String meaning;
    private String exampleSentence;
    private  byte[] photo;

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Vocabulary(String name, String meaning, String exampleSentence) {
        this.name = name;
        this.meaning = meaning;
        this.exampleSentence = exampleSentence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExampleSentence() {
        return exampleSentence;
    }

    public void setExampleSentence(String exampleSentence) {
        this.exampleSentence = exampleSentence;
    }
}
