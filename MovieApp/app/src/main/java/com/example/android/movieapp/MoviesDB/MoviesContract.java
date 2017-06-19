package com.example.android.movieapp.MoviesDB;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by goeic admin on 11-04-2017.
 */

public class MoviesContract {

    //for the content provider
    public static final String AUTHORITY = "com.example.android.movieapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_MOVIES = "movies";

    public static final class MovieDetailEntry implements BaseColumns{
        //for the content provider
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String TABLE_NAME = "MovieDetail";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE ="title";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_RELEASE_DATE = "release_date";
    }
}