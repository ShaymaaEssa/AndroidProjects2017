package com.example.android.movieapp;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    final static String MOVIE_CLICKED = "movieItem";
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = this.getActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        movie = intent.getParcelableExtra(MOVIE_CLICKED);

        DetailFragment detailFragment = (DetailFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_detailactivity_detailfragment);
        detailFragment.setMovie(movie);

    }
}
