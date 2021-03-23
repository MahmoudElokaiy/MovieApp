package com.shams.shamsmovieapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shams.shamsmovieapp.Model.Cast;

import java.util.List;
import com.shams.shamsmovieapp.API.MovieService;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdaptor extends RecyclerView.Adapter<CastAdaptor.CastViewHolder> {
    List<Cast> casts;

    public CastAdaptor(List<Cast> casts) {
        this.casts = casts;
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView txt_cast_name;
        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.img_cast_profile);
            txt_cast_name=itemView.findViewById(R.id.txt_cast_name);
        }
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast,parent,false);
        return new CastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        String profileImg= MovieService.Img_base_url + MovieService.Img_Size_Small+ casts.get(position).getProfilePath();
        Picasso.get().load(profileImg).into(holder.circleImageView);
        holder.txt_cast_name.setText(casts.get(position).getCharacter());
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }


}
