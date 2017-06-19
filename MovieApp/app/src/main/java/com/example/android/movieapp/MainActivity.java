package com.example.android.movieapp;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.Fragment;


public class MainActivity extends AppCompatActivity implements MoviePosterClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_main);
        }catch (Exception e){
            Log.e("mainerror","oncreateview",e);
            throw e;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_setting){
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void moviePosterClicked(Movie movieItem) {
        View detailFragmentView = findViewById(R.id.fragment_mainactivity_detailfragment);
        if (detailFragmentView != null){ //tablet view
            //Attach detailFragment to the MainActivity
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setMovie(movieItem);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_mainactivity_detailfragment, detailFragment);
           // ft.addToBackStack(null);
           // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commitAllowingStateLoss();

        }
        else { //phone view
            Intent intent = new Intent(MainActivity.this,DetailActivity.class);
            intent.putExtra(DetailActivity.MOVIE_CLICKED, movieItem);
            startActivity(intent);
        }
    }
}
