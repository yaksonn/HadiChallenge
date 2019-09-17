package com.hadichallenge.yakson.ui.activity.moviedetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hadichallenge.yakson.helpers.DialogHelper;
import com.hadichallenge.yakson.models.MovieModel;
import com.hadichallenge.yakson.models.MovieResultModel;
import com.hadichallenge.yakson.models.MovieTvSeriesCreditsModel;
import com.hadichallenge.yakson.network.dataprovider.MoviesDetailProvider;
import com.hadichallenge.yakson.network.request.APIResponseCallback;

public class MoviesDetailViewModel extends ViewModel {

    public MutableLiveData<MovieModel.MovieDetail.Response> movieResponseDetail = new MutableLiveData<>();
    public MutableLiveData<MovieTvSeriesCreditsModel.Credits.Response> movieCredit = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();

    public void getMoviesDetail(MovieModel.MovieDetail.Request request) {

        DialogHelper.showLoadingDialog();
        APIResponseCallback<MovieResultModel> callback = new APIResponseCallback<MovieResultModel>() {
            @Override
            public void onComplete(MovieResultModel response) {

                MovieModel.MovieDetail.Response res = new MovieModel.MovieDetail.Response(response);
                movieResponseDetail.postValue(res);
                DialogHelper.hideLoadingDialog();
            }

            @Override
            public void onFailure(String message, Integer statusCode) {
                error.postValue(message);
                DialogHelper.hideLoadingDialog();
            }
        };
        MoviesDetailProvider.getInstance().getDetail(request, callback);

    }

    public void getCredit(MovieTvSeriesCreditsModel.Credits.Request request) {

        DialogHelper.showLoadingDialog();
        APIResponseCallback<MovieTvSeriesCreditsModel> callback = new APIResponseCallback<MovieTvSeriesCreditsModel>() {
            @Override
            public void onComplete(MovieTvSeriesCreditsModel response) {

                MovieTvSeriesCreditsModel.Credits.Response res = new MovieTvSeriesCreditsModel.Credits.Response(response);
                movieCredit.postValue(res);
                DialogHelper.hideLoadingDialog();
            }

            @Override
            public void onFailure(String message, Integer statusCode) {
                error.postValue(message);
                DialogHelper.hideLoadingDialog();
            }
        };
        MoviesDetailProvider.getInstance().getCredits(request, callback);
    }

}
