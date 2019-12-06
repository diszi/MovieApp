package com.movieapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("3/search/movie?api_key=43a7ea280d085bd0376e108680615c7f&language=en-US&query=GET&page=1&include_adult=false")
    Call<Result> getMovies();


    @GET("3/movie/{id}?api_key=43a7ea280d085bd0376e108680615c7f&language=en-US&query=GET&page=1&include_adult=false")
    Call<Movie> getMovieByID(@Path("id") String id);
}
