package com.hadichallenge.yakson.network.dataprovider.movieprovider;

import com.hadichallenge.yakson.models.MovieModel;
import com.hadichallenge.yakson.models.MovieResultModel;
import com.hadichallenge.yakson.models.MovieTvSeriesCreditsModel;
import com.hadichallenge.yakson.network.request.APIRequest;
import com.hadichallenge.yakson.network.request.APIResponseCallback;
import com.hadichallenge.yakson.network.request.ApiInterface;
import com.hadichallenge.yakson.network.request.NetworkService;

import java.util.HashMap;
import java.util.Map;

public class MoviesDetailProvider {

    private static final MoviesDetailProvider ourInstance = new MoviesDetailProvider();
    private static ApiInterface router = NetworkService.getInstance().getApi();

    public static MoviesDetailProvider getInstance() {
        return ourInstance;
    }

    public void getDetail(MovieModel.MovieDetail.Request requestParams,
                          final APIResponseCallback<MovieResultModel> callback) {

        Map<String, String> params = new HashMap<>();
        params.put("api_key", requestParams.api_key);
        params.put("language", requestParams.language);

        APIRequest<MovieResultModel> APIRequest = new APIRequest<>();

        APIRequest.make(router.getDetail(requestParams.type, requestParams.movieId,
                params), new APIResponseCallback<MovieResultModel>() {
            @Override
            public void onComplete(MovieResultModel response) {
                callback.onComplete(response);
            }

            @Override
            public void onFailure(String message, Integer statusCode) {
                callback.onFailure(message, statusCode);
            }
        });
    }

    public void getCredits(MovieTvSeriesCreditsModel.Credits.Request requestParams,
                           final APIResponseCallback<MovieTvSeriesCreditsModel> callback) {

        Map<String, String> params = new HashMap<>();
        params.put("api_key", requestParams.api_key);
        params.put("language", requestParams.language);

        APIRequest<MovieTvSeriesCreditsModel> APIRequest = new APIRequest<>();

        APIRequest.make(router.getCredits(requestParams.type, requestParams.movieId,
                params), new APIResponseCallback<MovieTvSeriesCreditsModel>() {
            @Override
            public void onComplete(MovieTvSeriesCreditsModel response) {
                callback.onComplete(response);
            }

            @Override
            public void onFailure(String message, Integer statusCode) {
                callback.onFailure(message, statusCode);
            }
        });
    }
}
