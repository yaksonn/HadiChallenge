package com.hadichallenge.yakson.ui.fragment.movies;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hadichallenge.yakson.helpers.DialogHelper;
import com.hadichallenge.yakson.models.MovieModel;
import com.hadichallenge.yakson.network.dataprovider.movieprovider.MoviesProvider;
import com.hadichallenge.yakson.network.request.APIResponseCallback;

public class MoviesViewModel extends ViewModel {

    public MutableLiveData<MovieModel.Movie.Response> movieResponseTop = new MutableLiveData<>();


    public MutableLiveData<String> error = new MutableLiveData<>();


    public void getMovies(MovieModel.Movie.Request request) {

        DialogHelper.showLoadingDialog();
        APIResponseCallback<MovieModel> callback = new APIResponseCallback<MovieModel>() {
            @Override
            public void onComplete(MovieModel response) {
                MovieModel.Movie.Response res = new MovieModel.Movie.Response(response);
                movieResponseTop.postValue(res);
                DialogHelper.hideLoadingDialog();
            }

            @Override
            public void onFailure(String message, Integer statusCode) {
                error.postValue(message);
                DialogHelper.hideLoadingDialog();
            }
        };
        MoviesProvider.getInstance().getMovies(request, callback);
    }


}
