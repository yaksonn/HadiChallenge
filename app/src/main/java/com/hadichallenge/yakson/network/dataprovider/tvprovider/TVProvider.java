package com.hadichallenge.yakson.network.dataprovider.tvprovider;

import com.hadichallenge.yakson.network.request.ApiInterface;
import com.hadichallenge.yakson.network.request.NetworkService;

public class TVProvider {

    private static final TVProvider ourInstance = new TVProvider();
    private static ApiInterface router = NetworkService.getInstance().getApi();

    public static TVProvider getInstance() {
        return ourInstance;
    }

//    public void getMoviesTv(MovieModel.Movie.Request requestParams,
//                          final APIResponseCallback<MovieModel> callback) {
//
//        Map<String, String> params = new HashMap<>();
//        params.put("api_key", requestParams.api_key);
//        params.put("language", requestParams.language);
//        params.put("page", String.valueOf(requestParams.page));
//
//        APIRequest<MovieModel> APIRequest = new APIRequest<>();
//
//        APIRequest.make(router.getMoviesOrTvSeries(requestParams.type, requestParams.category,
//                params), new APIResponseCallback<MovieModel>() {
//            @Override
//            public void onComplete(MovieModel response) {
//                callback.onComplete(response);
//            }
//
//            @Override
//            public void onFailure(String message, Integer statusCode) {
//                callback.onFailure(message, statusCode);
//            }
//        });
//    }
}
