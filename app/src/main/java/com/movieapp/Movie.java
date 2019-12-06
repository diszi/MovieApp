package com.movieapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    public static String SERIALIZABLE_MOVIE="Movie_Serializable";

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private Double rate;


    @SerializedName("status")
    private String status;

    @SerializedName("popularity")
    private Float popularity;


    @SerializedName("id")
    private Long id;

    @SerializedName("original_language")
    private String original_lang;

    @SerializedName("overview")
    private String overview;

    @SerializedName("genres")
    private List<Genres> genresList;

    @SerializedName("backdrop_path")
    private String imagepath;

    @SerializedName("budget")
    private Long budget;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("production_companies")
    private List<Companies> productionCompanies;

    public String getOriginal_title() {
        return original_title;
    }

    public String getTitle() {
        return title;
    }


    public Double getRate() {
        return rate;
    }

    public String getStatus() {
        return status;
    }

    public Float getPopularity() {
        return popularity;
    }

    public Long getId() {
        return id;
    }

    public String getOriginal_lang() {
        return original_lang;
    }

    public String getOverview() {
        return overview;
    }

    public List<Genres> getGenresList() {
        return genresList;
    }

    public String getImagepath() {
        return imagepath;
    }

    public Long getBudget() {
        return budget;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Companies> getProductionCompanies() {
        return productionCompanies;
    }
}
