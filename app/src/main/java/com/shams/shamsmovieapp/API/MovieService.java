package com.shams.shamsmovieapp.API;

import com.shams.shamsmovieapp.Model.MovieDetails;
import com.shams.shamsmovieapp.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    String Img_base_url = "https://image.tmdb.org/t/p";
    String Img_Size_Small= "/w185";
    String Img_Size_Banner= "/w780";
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key")String apiKey);

    //For bring Movie Details
    @GET("movie/{movie_id}?append_to_response=videos,credits,reviews")
    Call<MovieDetails> getMovieDetail(@Path("movie_id") int id , @Query("api_key") String api_key);
}
