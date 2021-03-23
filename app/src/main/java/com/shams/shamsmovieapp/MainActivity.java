package com.shams.shamsmovieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.shams.shamsmovieapp.API.ApiClient;
import com.shams.shamsmovieapp.Model.MovieResponse;
import com.shams.shamsmovieapp.Model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdaptor.onClick{
    List<Result> movies;
    RecyclerView recyclerView;
    MovieAdaptor adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initObj();
        getData();
    }
    private void initObj(){
        recyclerView= findViewById(R.id.rv);
        movies= new ArrayList<>();
        adaptor= new MovieAdaptor(movies,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adaptor);
    }
    private void getData(){
        ApiClient.getsInstance().getPopularMovies("f21c60af031232aed2efcf6ed8f147d7")
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if(response.isSuccessful()){
                            movies.clear();
                            movies.addAll(response.body().getResults());
                            adaptor.notifyDataSetChanged();
                        }else{
                            Log.d("Crashe","Not Successful"+response.errorBody());
                        }
                    }
                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        Log.e("Crash","onFailure: ",t);
                    }
                });
    }
    @Override
    public void MovieSelected(int position) {
        Intent i= new Intent(this,movie_details.class);
        i.putExtra(movie_details.EXTRA_MOVIE_ID,movies.get(position).getId());
        startActivity(i);
    }
}