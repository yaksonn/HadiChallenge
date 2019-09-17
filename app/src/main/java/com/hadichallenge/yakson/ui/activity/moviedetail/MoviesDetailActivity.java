package com.hadichallenge.yakson.ui.activity.moviedetail;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hadichallenge.yakson.R;
import com.hadichallenge.yakson.adapters.CastRecyclerAdapter;
import com.hadichallenge.yakson.helpers.ImageLoadHelper;
import com.hadichallenge.yakson.models.CastModel;
import com.hadichallenge.yakson.models.MovieModel;
import com.hadichallenge.yakson.models.MovieResultModel;
import com.hadichallenge.yakson.models.MovieTvSeriesCreditsModel;
import com.hadichallenge.yakson.models.MovieTvSeriesGenresModel;
import com.hadichallenge.yakson.network.core.ApiConstant;
import com.hadichallenge.yakson.ui.activity.BaseActivity;
import com.ms.square.android.expandabletextview.ExpandableTextView;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import static com.hadichallenge.yakson.network.core.ApiConstant.MOVIE;


public class MoviesDetailActivity extends BaseActivity {

    @BindView(R.id.backdropImageView)
    ImageView backdropImageView;
    @BindView(R.id.posterImageView)
    ImageView posterImageView;
    @BindView(R.id.expandTextView)
    ExpandableTextView expandTextView;
    @BindView(R.id.genresTextView)
    TextView genresTextView;
    @BindView(R.id.movieRatingTextView)
    TextView movieRatingTextView;
    @BindView(R.id.movieTitleTextView)
    TextView movieTitleTextView;
    @BindView(R.id.castRecyclerview)
    RecyclerView castRecyclerview;
    @BindView(R.id.movieRatingBar)
    RatingBar movieRatingBar;

    private MoviesDetailViewModel moviesDetailViewModel = new MoviesDetailViewModel();
    private ArrayList<CastModel> movieTvSeriesCreditsModels = new ArrayList<>();
    private MovieResultModel movieResultModel;
    private CastRecyclerAdapter castRecyclerAdapter;
    private Integer videoId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_movies;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviesDetailViewModel = ViewModelProviders.of(this).get(MoviesDetailViewModel.class);
        getDataFromIntent();
        initRecyclerViewCast();
        bindToViewModel();
    }

    private void getDataFromIntent() {
        videoId = getIntent().getIntExtra("videoId", 0);
        fetchDetail();
        fetchCredits();
    }

    private void bindToViewModel() {
        moviesDetailViewModel.movieResponseDetail.observe(this, movies -> {
            if (movies != null) {
                movieResultModel = movies.movieResultModels;
                setFields(movieResultModel);
            }
        });

        moviesDetailViewModel.movieCredit.observe(this, movies -> {
            if (movies != null) {
                movieTvSeriesCreditsModels = movies.castModel.getCast();
                initRecyclerAdapterCast(movieTvSeriesCreditsModels);
            }
        });

    }

    private void setFields(MovieResultModel movieModel) {

        ImageLoadHelper.getInstance().loadImage(backdropImageView,
                ApiConstant.IMAGE_PREFIX + movieModel.getBackdropPath());

        ImageLoadHelper.getInstance().loadImage(posterImageView,
                ApiConstant.IMAGE_PREFIX + movieModel.getPosterPath());

        movieTitleTextView.setText(movieModel.getTitle());
        expandTextView.setText(movieModel.getOverview());
        movieRatingTextView.setText(Double.toString(movieModel.getVoteAverage()));
        movieRatingBar.setRating(Float.parseFloat(Double.toString(movieModel.getVoteAverage() / 2)));
        setGenres(movieModel.getGenresModels());

    }

    private void setGenres(ArrayList<MovieTvSeriesGenresModel> genres) {
        StringBuilder genresBuilder = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            genresBuilder.append(genres.get(i).getName());
            if (i != genres.size() - 1) {
                genresBuilder.append(" , ");
            }
        }
        genresTextView.setText(genresBuilder.toString());
    }

    private void initRecyclerViewCast() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        castRecyclerview.setHasFixedSize(true);
        castRecyclerview.setNestedScrollingEnabled(false);
        castRecyclerview.setLayoutManager(linearLayoutManager);
    }

    private void initRecyclerAdapterCast(ArrayList<CastModel> movieResultModels) {
        castRecyclerAdapter = new CastRecyclerAdapter(movieResultModels, (view, position) -> {

        });
        castRecyclerview.setAdapter(castRecyclerAdapter);
    }

    @OnClick({R.id.shareImageView, R.id.backIcon})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.shareImageView:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, movieResultModel.getTitle());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, movieResultModel.getOverview());
                startActivity(Intent.createChooser(sharingIntent, "Hadi Challenge"));
                break;
            case R.id.backIcon:
                finish();
                break;
        }
    }

    private void fetchDetail() {
        MovieModel.MovieDetail.Request request = new MovieModel.MovieDetail.Request(
                MOVIE,
                videoId,
                ApiConstant.KEY,
                ApiConstant.LANGUAGE
        );
        moviesDetailViewModel.getMoviesDetail(request);
    }

    private void fetchCredits() {
        MovieTvSeriesCreditsModel.Credits.Request request = new MovieTvSeriesCreditsModel.Credits.Request(
                MOVIE,
                videoId,
                ApiConstant.KEY,
                ApiConstant.LANGUAGE
        );
        moviesDetailViewModel.getCredit(request);
    }

}
