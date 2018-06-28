package com.codepath.flixster;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flixster.models.Config;
import com.codepath.flixster.models.Movie;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Viewholder> {

    //list of movies
    ArrayList<Movie> movies;
    //config needed for image urls
    Config config;
    //context for rendering
    Context context;


    //initialize list of movies
    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }


    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @NonNull
    @Override //creates and inflates a new view
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get the context and create the inflater
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //create the view using the item_movie layout
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        //return a new Viewholder

        return new Viewholder(movieView);
    }

    //binds an inflated view to a new item
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        //get the movieData at the specified position
        Movie movie = movies.get(position);
        //populate the view with the movie data
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

       //build url for poster image
        String imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());

        //load image using glide
        GlideApp.with(context)
                .load(imageUrl)
                .transform(new RoundedCornersTransformation( 25, 0))
                .placeholder(R.drawable.flicks_movie_placeholder)
                .error(R.drawable.flicks_movie_placeholder)
                .into(holder.ivPosterImage);




    }

    //returns the total number of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //create the viewholder static inner class
    public static class Viewholder extends RecyclerView.ViewHolder {
        //track view objects
        ImageView ivPosterImage;
        TextView tvTitle;
        TextView tvOverview;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            //lookup view objects by id
            ivPosterImage = (ImageView) itemView.findViewById(R.id.ivPosterImage);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
