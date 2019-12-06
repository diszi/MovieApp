package com.movieapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.moview_recyclerView)
    RecyclerView searchableRecyclerView;
    @BindView(R.id.searchField)
    SearchView searchView;
    @BindView(R.id.act_toolbar)
    Toolbar compToolbar;

    private MainAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        compToolbar.setTitle("Movies");
        compToolbar.setTitleTextColor(getResources().getColor(R.color.colorButtonText));

        this.setupRecyclerView();

        loadMovies();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });



    }


    private void setupRecyclerView() {
        Context context = getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new MainAdapter(this);
        this.searchableRecyclerView.setLayoutManager(layoutManager);
        this.searchableRecyclerView.setAdapter(this.adapter);

    }


    private void loadMovies(){


        APIService api = RetrofitClient.getApiService();

        Call<Result> callMovie = api.getMovies();



        callMovie.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (!response.isSuccessful()) {
                    showErrorMsg("Error in loading Movies");
                    return;
                }

                Result moviesResult = response.body();
                List<Movie> movieList = moviesResult.getMovieList();
                adapter.setMovies(movieList);

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                showErrorMsg("Error in call");
            }
        });
    }

    private void showErrorMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }


    public void launchMovieDetails(Movie movie){

        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(Movie.SERIALIZABLE_MOVIE,movie);
        startActivity(intent);
    }
}
