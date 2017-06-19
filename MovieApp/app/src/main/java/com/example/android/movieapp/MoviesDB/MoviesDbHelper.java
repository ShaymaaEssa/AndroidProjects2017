package com.example.android.movieapp.MoviesDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by goeic admin on 11-04-2017.
 */

public class MoviesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Movies.db";
    private static final int DATABASE_VERSION  = 1;

    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIES_TABLE = "CREATE TABLE "+
                MoviesContract.MovieDetailEntry.TABLE_NAME + " ( "+
                MoviesContract.MovieDetailEntry._ID + " Integer primary key AutoIncrement, "+
                MoviesContract.MovieDetailEntry.COLUMN_TITLE+ " Text not null, "+
                MoviesContract.MovieDetailEntry.COLUMN_ID + " Text not null, " +
                MoviesContract.MovieDetailEntry.COLUMN_OVERVIEW + " Text not null, " +
                MoviesContract.MovieDetailEntry.COLUMN_VOTE_AVERAGE + " Text not null, "+
                MoviesContract.MovieDetailEntry.COLUMN_RELEASE_DATE + " Text not null, " +
                MoviesContract.MovieDetailEntry.COLUMN_BACKDROP_PATH + " Text not null, " +
                MoviesContract.MovieDetailEntry.COLUMN_POSTER_PATH + " Text not null " +"); ";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists "+ MoviesContract.MovieDetailEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
