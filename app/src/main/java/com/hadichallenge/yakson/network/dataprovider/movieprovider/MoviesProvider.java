package com.hadichallenge.yakson.network.dataprovider.movieprovider;

import com.hadichallenge.yakson.models.MovieModel;
import com.hadichallenge.yakson.network.request.APIRequest;
import com.hadichallenge.yakson.network.request.APIResponseCallback;
import com.hadichallenge.yakson.network.request.ApiInterface;
import com.hadichallenge.yakson.network.request.NetworkService;

import java.util.HashMap;
import java.util.Map;

public class MoviesProvider {

    private static final MoviesProvider ourInstance = new MoviesProvider();
    private static ApiInterface router = NetworkService.getInstance().getApi();

    public static MoviesProvider getInstance() {
        return ourInstance;
    }

    public void getMovies(MovieModel.Movie.Request requestParams,
                          final APIResponseCallback<MovieModel> callback) {

        Map<String, String> params = new HashMap<>();
        params.put("api_key", requestParams.api_key);
        params.put("query", requestParams.query);
        params.put("page", String.valueOf(requestParams.page));

        APIRequest<MovieModel> APIRequest = new APIRequest<>();

        APIRequest.make(router.getMoviesOrTvSeries(
                params), new APIResponseCallback<MovieModel>() {
            @Override
            public void onComplete(MovieModel response) {
                callback.onComplete(response);
            }

            @Override
            public void onFailure(String message, Integer statusCode) {
                callback.onFailure(message, statusCode);
            }
        });
    }
}
