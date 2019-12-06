package com.movieapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.movieapp.R.color.colorPrimary;
import static com.movieapp.R.drawable.button_shape;

public class MovieDetailsActivity extends AppCompatActivity {


    @BindView(R.id.movie_language)
    TextView language;
    @BindView(R.id.movie_popularity)
    TextView popularity;
    @BindView(R.id.movie_status)
    TextView status;
    @BindView(R.id.movie_overview)
    TextView overView;

    @BindView(R.id.movie_title)
    TextView title;
    @BindView(R.id.movie_budget)
    TextView budget;

    @BindView(R.id.linearLayout3)
    LinearLayout linearLayout;

    private Movie getMovie=null;
    private List<Genres> genresList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        ButterKnife.bind(this);



        getMovie = (Movie)getIntent().getSerializableExtra(Movie.SERIALIZABLE_MOVIE);


        getMovieDetails(getMovie.getId());

    }

    private void getMovieDetails(Long id){

        APIService api = RetrofitClient.getApiService();
        Call<Movie> callMovieDetails = api.getMovieByID(id.toString());
        callMovieDetails.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (!response.isSuccessful()) {
                    showErrorMsg("Error in loading Movie details");
                    return;
                }
                Movie movie = response.body();
                genresList = movie.getGenresList();
                showMovieDetails(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                System.out.println(" ERROR in call");
            }
        });

    }

    private void showMovieDetails(Movie movie){

        title.setText(movie.getTitle());
        status.setText(movie.getStatus());
        popularity.setText(movie.getPopularity().toString());
        language.setText(movie.getOriginal_lang());
        overView.setText(movie.getOverview());
        budget.setText(movie.getBudget().toString());
        showGenres();

    }


    private void showGenres(){

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(5,5, 5, 5);

        for (int i=0;i<genresList.size();i++){
            final Button btn = new Button(this);
            btn.setText(genresList.get(i).getName());

            btn.setBackgroundResource(R.drawable.button_shape);
            btn.setTextColor(getResources().getColor(R.color.colorButtonText));
            btn.setLayoutParams(params);
            linearLayout.addView(btn);
        }

    }

    private void showErrorMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }


}
