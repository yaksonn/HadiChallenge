package com.hadichallenge.yakson.network.request;


import com.hadichallenge.yakson.models.MovieModel;
import com.hadichallenge.yakson.models.MovieResultModel;
import com.hadichallenge.yakson.models.MovieTvSeriesCreditsModel;
import com.hadichallenge.yakson.network.core.ApiEndPoints;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET(ApiEndPoints.API_GET_MOVIE_OR_TV)
    Call<MovieModel> getMoviesOrTvSeries(
            @QueryMap Map<String, String> params
    );

    @GET(ApiEndPoints.API_GET_MOVIE_OR_TV_DETAIL)
    Call<MovieResultModel> getDetail(
            @Path("movie") String movie,
            @Path("movie_id") Integer movie_id,
            @QueryMap Map<String, String> params
    );

    @GET(ApiEndPoints.API_GET_MOVIE_OR_TV_CREDITS)
    Call<MovieTvSeriesCreditsModel> getCredits(
            @Path("movie") String movie,
            @Path("movie_id") Integer movie_id,
            @QueryMap Map<String, String> params
    );

}
