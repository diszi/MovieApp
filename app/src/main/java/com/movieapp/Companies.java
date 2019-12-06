package com.movieapp;

import com.google.gson.annotations.SerializedName;

public class Companies {

    @SerializedName("name")
    private String name;

    @SerializedName("origin_coutry")
    private String country;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
