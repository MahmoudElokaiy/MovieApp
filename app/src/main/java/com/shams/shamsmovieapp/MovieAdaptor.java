package com.shams.shamsmovieapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shams.shamsmovieapp.API.MovieService;
import com.shams.shamsmovieapp.Model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdaptor extends RecyclerView.Adapter<MovieAdaptor.VH> {
    private List<Result> movieList;
    private onClick click;
    public MovieAdaptor(List<Result> movieList, onClick click) {
        this.movieList = movieList;
        this.click = click;
    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Result res= movieList.get(position);
        holder.txt_title.setText(res.getTitle());

        String img_url= MovieService.Img_base_url + MovieService.Img_Size_Small + res.getPosterPath();
        Picasso.get().load(img_url).into(holder.img_poster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.MovieSelected(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
    class VH extends RecyclerView.ViewHolder{
        ImageView img_poster;
        TextView txt_title;
        public VH(@NonNull View itemView) {
            super(itemView);
            img_poster=itemView.findViewById(R.id.img_movie);
            txt_title=itemView.findViewById(R.id.txtTitle);
        }
    }
    interface onClick{
        void MovieSelected(int position);
    }
}
