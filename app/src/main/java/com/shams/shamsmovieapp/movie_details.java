package com.shams.shamsmovieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.shams.shamsmovieapp.API.ApiClient;
import com.shams.shamsmovieapp.API.MovieService;
import com.shams.shamsmovieapp.Model.Genre;
import com.shams.shamsmovieapp.Model.MovieDetails;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class movie_details extends AppCompatActivity {
    public static final String EXTRA_MOVIE_ID="movie_id";
    private static final int default_ID=-1;
    ImageView banner,poster;
    TextView txt_title,txt_release_date,txt_vote,txt_language,txt_overview,label_vote;
    ChipGroup geners;
    MovieDetails movieDetails;
    CastAdaptor castAdaptor;
    RecyclerView rv_cast;
    ReviewAdaptor reviewAdaptor;
    RecyclerView rv_review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeLight);
        setContentView(R.layout.activity_movie_details);
        final int movieId= getIntent().getIntExtra(EXTRA_MOVIE_ID,default_ID);

        if(movieId == default_ID){
            return;
        }

        initViews();
        getData(movieId);
    }

    private void initViews(){
        banner= findViewById(R.id.img_movie_backdrop);
        poster=findViewById(R.id.img_poster);
        txt_title=findViewById(R.id.txt_title);
        geners=findViewById(R.id.chipgeners);
        txt_release_date=findViewById(R.id.txt_releaseDate);
        txt_vote=findViewById(R.id.txt_vote);
        txt_language=findViewById(R.id.language);
        txt_overview=findViewById(R.id.txt_overview);
        label_vote=findViewById(R.id.lbl_vote);
        rv_cast=findViewById(R.id.rv_cast);
        rv_review=findViewById(R.id.rv_reviews);
    }

    private void getData(int movieId){
        ApiClient.getsInstance().getMovieDetail(movieId,"f21c60af031232aed2efcf6ed8f147d7")
                .enqueue(new Callback<MovieDetails>() {
                    @Override
                    public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                        if(response.isSuccessful()){
                            //take respor comping and put it in movieDetials Object
                            movieDetails= response.body();
                            setDatatoView();
                            setCastAdaptor();
                            setReviewAdaptor();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieDetails> call, Throwable t) {

                    }
                });

    }

    private void setDatatoView(){
        String banner_url= MovieService.Img_base_url + MovieService.Img_Size_Banner + movieDetails.getBackdropPath();
        Picasso.get().load(banner_url).into(banner);

        String poster_url= MovieService.Img_base_url + MovieService.Img_Size_Small + movieDetails.getPosterPath();
        Picasso.get().load(poster_url).into(poster);

        txt_title.setText(movieDetails.getTitle());

        //Chips & Genres
        for (Genre g: movieDetails.getGenres()) {
            Chip chip = new Chip(this);
            chip.setText(g.getName());
            chip.setChipStrokeWidth(3);
            chip.setChipStrokeColor(ColorStateList.valueOf(
                    ContextCompat.getColor(this,R.color.colorPrimary)
            ));
            chip.setChipBackgroundColorResource(android.R.color.transparent);
            geners.addView(chip);
        }
        txt_release_date.setText(movieDetails.getReleaseDate());
        txt_vote.setText(String.valueOf(movieDetails.getVoteAverage()));
        txt_language.setText(movieDetails.getOriginalLanguage());
        txt_overview.setText(movieDetails.getOverview());
        label_vote.setText(String.valueOf(movieDetails.getVoteCount()));
    }

    private void setCastAdaptor(){
        castAdaptor = new CastAdaptor(movieDetails.getCredits().getCast());
        rv_cast.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        rv_cast.setAdapter(castAdaptor);
    }
    private void setReviewAdaptor(){
        reviewAdaptor= new ReviewAdaptor(movieDetails.getReviews().getResults());
        rv_review.setLayoutManager(new LinearLayoutManager(this));
        rv_review.setAdapter(reviewAdaptor);
    }
}