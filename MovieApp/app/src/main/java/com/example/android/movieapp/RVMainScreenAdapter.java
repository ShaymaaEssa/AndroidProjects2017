package com.example.android.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MOSTAFA on 02/04/2017.
 */

//RecyclerView Main Screen Adapter
public class RVMainScreenAdapter extends RecyclerView.Adapter<RVMainScreenAdapter.RVMainScreenAdapterViewHolder>{

    List<Movie> movies;
    Context context;
    RVItemClickListener rvItemClickListener;

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }



    public RVMainScreenAdapter (List<Movie> movies , Context context , RVItemClickListener rvItemClickListener){
        this.movies = movies;
        this.context = context;
        this.rvItemClickListener = rvItemClickListener;
    }

    @Override
    public RVMainScreenAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_mainscreen,parent,false);
        RVMainScreenAdapterViewHolder holder = new RVMainScreenAdapterViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(RVMainScreenAdapterViewHolder holder, int position) {
        Movie movie = movies.get(position);
        int width= context.getResources().getDisplayMetrics().widthPixels;
        Picasso.with(context)
                .load(context.getString(R.string.image_url) + movies.get(position).getPoster_path())
                .centerCrop().resize(width/2,4*width/5)
                .placeholder(R.drawable.postimg_error)
                .error(R.drawable.postimg_error)
                .into(holder.imageViewPoster);


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class RVMainScreenAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageViewPoster;
        public RVMainScreenAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewPoster = (ImageView)itemView.findViewById(R.id.imageview_rvitem_poster);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            rvItemClickListener.onListItemClick(movies.get(getAdapterPosition()));
        }
    }

    public void setMoviesData (List<Movie>movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    public interface RVItemClickListener {
        void onListItemClick (Movie movie);
    }
}
