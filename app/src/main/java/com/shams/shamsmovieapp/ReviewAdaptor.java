package com.shams.shamsmovieapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.shams.shamsmovieapp.Model.ReviewResult;

import java.util.List;

public class ReviewAdaptor extends RecyclerView.Adapter<ReviewAdaptor.ReviewsViewHolder> {
    private List<ReviewResult> reviewsList;

    public ReviewAdaptor(List<ReviewResult> reviewsList) {
        this.reviewsList = reviewsList;
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAuthor;
        TextView txt_author,txt_content;
        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAuthor=itemView.findViewById(R.id.img_author);
            txt_author=itemView.findViewById(R.id.txt_author);
            txt_content=itemView.findViewById(R.id.txt_content);
        }
    }
    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);
        return new ReviewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {
        holder.txt_author.setText(reviewsList.get(position).getAuthor());
        holder.txt_content.setText(reviewsList.get(position).getContent());

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color= generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound(reviewsList.get(position).getAuthor().substring(0,1),color);
        holder.imgAuthor.setImageDrawable(drawable);

    }
    @Override
    public int getItemCount() {
       if(reviewsList != null)
           return reviewsList.size();
       else
           return 0;
    }
}
