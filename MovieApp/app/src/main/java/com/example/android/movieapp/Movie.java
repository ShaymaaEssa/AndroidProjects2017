package com.example.android.movieapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOSTAFA on 01/04/2017.
 */

public class Movie implements Parcelable{
    private String poster_path;
    private String overview;
    private String release_date;
    private String id;
    private String title ;
    private String vote_average;
    private String backdrop_path;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Movie(String poster_path, String overview, String release_date, String id, String title, String vote_average,String backdrop_path) {
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.id = id;
        this.title = title;
        this.vote_average = vote_average;
        this.backdrop_path = backdrop_path;
    }



    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    //Parcing Movie object so we can send it in intent
    public Movie(Parcel in) {
        String[] data = new String[7];
        in.readStringArray(data);
        this.poster_path = data[0];
        this.overview = data[1];
        this.release_date = data[2];
        this.id = data[3];
        this.title = data[4];
        this.vote_average = data[5];
        this.backdrop_path = data[6];

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{this.poster_path,this.overview,this.release_date,this.id,this.title,this.vote_average,this.backdrop_path});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
