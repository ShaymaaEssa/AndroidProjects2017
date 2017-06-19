package com.example.android.movieapp.MoviesDB;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by goeic admin on 16-04-2017.
 */

public class MoviesContentProvider extends ContentProvider {
    MoviesDbHelper moviesDbHelper;

    public static final int MOVIES = 100;
    public static final int MOVIES_WITH_ID = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_MOVIES, MOVIES);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_MOVIES + "/#", MOVIES_WITH_ID);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        moviesDbHelper = new MoviesDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = moviesDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor retCursor;
        switch (match) {
            case MOVIES:
                retCursor = db.query(MoviesContract.MovieDetailEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;


            case MOVIES_WITH_ID:
                String id = uri.getPathSegments().get(1);
                String mSelection = MoviesContract.MovieDetailEntry.COLUMN_ID + " = ? ";
                String[] mSelectionArgs = new String[]{id};
                retCursor = db.query(MoviesContract.MovieDetailEntry.TABLE_NAME,projection,mSelection,mSelectionArgs,null,null,sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("unknown uri " + uri);


        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = moviesDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case MOVIES:
                long id = db.insert(MoviesContract.MovieDetailEntry.TABLE_NAME, null, contentValues);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(MoviesContract.MovieDetailEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("failed to insert row into " + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("unknown uri " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selections, String[] selectionsArgs) {
        SQLiteDatabase db = moviesDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        int num = 0;
        switch (match) {
            case MOVIES_WITH_ID:
                String id = uri.getPathSegments().get(1);
                String mSelection = MoviesContract.MovieDetailEntry.COLUMN_ID + " =?";
                String[] mSelectionArgs = new String[]{id};
                num = db.delete(MoviesContract.MovieDetailEntry.TABLE_NAME, mSelection, mSelectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("unknown uri " + uri);

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
