package com.movieapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements Filterable{

    private List<Movie> moviesList = new ArrayList();
    private ArrayList<Movie> moviesFullList ;
    private MainActivity activity;

    MainAdapter(MainActivity activity){
        this.activity=activity;
    }

    public void setMovies(List<Movie> movies) {

        this.moviesList.clear();
        this.moviesList.addAll(movies);
        moviesFullList = new ArrayList<>(this.moviesList);
        this.notifyDataSetChanged();

    }



    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_row,viewGroup,false);
        return new MainAdapter.MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {
        final Movie movie = moviesList.get(i);
        mainViewHolder.bind(movie);
        mainViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.launchMovieDetails(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (moviesList !=null && moviesList.size() >0){
            return moviesList.size();
        }else{
            return 0;
        }
    }




        public Filter getFilter() {
            return senderFilter;
        }



        private Filter senderFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                ArrayList<Movie> filteredList = new ArrayList<>();
                if (constraint==null || constraint.length() == 0){
                    filteredList.addAll(moviesFullList);
                }else{
                    String filteredPattern  =  constraint.toString().toLowerCase();

                    for (Movie movie : moviesFullList){
                        if (movie.getTitle().toLowerCase().contains(filteredPattern)){

                            filteredList.add(movie);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                moviesList.clear();
                moviesList.addAll((ArrayList) results.values);
                notifyDataSetChanged();
            }
        };



    static class MainViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.movie_title)
        TextView title;
        @BindView(R.id.movie_rate)
        TextView rate;
        @BindView(R.id.image_movie)
        ImageView movieImage;

        MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        private void bind(Movie movie) {
            title.setText(movie.getTitle());
            rate.setText(movie.getRate().toString());
            String newURL = "https://image.tmdb.org/t/p/w500/" + movie.getImagepath();
            new ImageAsyncTask((movieImage)).execute(newURL);


        }


    }

}
