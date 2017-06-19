package com.example.android.movieapp;

/**
 * Created by MOSTAFA on 29/04/2017.
 */

public class TrailerData {
    String name;
    String key;

    public TrailerData(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
