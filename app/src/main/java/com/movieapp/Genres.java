package com.movieapp;

import com.google.gson.annotations.SerializedName;

public class Genres {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
