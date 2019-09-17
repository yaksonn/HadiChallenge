package com.hadichallenge.yakson.ui.fragment.movies;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.hadichallenge.yakson.HadiLiveChallengeApp;
import com.hadichallenge.yakson.R;
import com.hadichallenge.yakson.adapters.MovieSearchResultRecyclerAdapter;
import com.hadichallenge.yakson.models.MovieModel;
import com.hadichallenge.yakson.models.MovieResultModel;
import com.hadichallenge.yakson.network.core.ApiConstant;
import com.hadichallenge.yakson.ui.activity.BaseActivity;
import com.hadichallenge.yakson.ui.activity.moviedetail.MoviesDetailActivity;
import com.hadichallenge.yakson.ui.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MoviesFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, MovieSearchResultRecyclerAdapter.OnScroolToBottom {

    public static final String FRAGMENT_NAME = "moviesFragment";

    private int currentPage = 1;
    private String queryTextFromSearch = "";
    private MenuItem mSearchItem;
    private SearchView searchView;

    @BindView(R.id.topRatedRecyclerView)
    RecyclerView topRatedRecyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    private MoviesViewModel movieViewModel = new MoviesViewModel();
    private ArrayList<MovieResultModel> movieResultArrayList = new ArrayList<>();
    private MovieSearchResultRecyclerAdapter moviesTopRatedRecyclerAdapter;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        movieViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        bindToViewModel();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movies;
    }

    private void bindToViewModel() {

        movieViewModel.movieResponseTop.observe(this, movies -> {

            if (movies != null) {
                fillListAndNotifyAdapter(movies.movieResultModels.getResults());
            }
        });
        movieViewModel.error.observe(this, s -> Log.d("Result : ", "çalışmıyorfjkenbjfhgrdnfıjksnmklmel"));
    }

    private void initViews() {
        swipeRefreshLayout.setOnRefreshListener(this);
        initRecyclerView();
    }

    private void initRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(HadiLiveChallengeApp.getInstance().getCurrentActivity());

        moviesTopRatedRecyclerAdapter = new MovieSearchResultRecyclerAdapter(movieResultArrayList,this, (view, position) -> {
            startActivity(new Intent(BaseActivity.currentActivity, MoviesDetailActivity.class)
                    .putExtra("videoId", movieResultArrayList.get(position).getId()));
        });

        topRatedRecyclerView.setLayoutManager(layoutManager);
        topRatedRecyclerView.setAdapter(moviesTopRatedRecyclerAdapter);
        moviesTopRatedRecyclerAdapter.notifyDataSetChanged();
    }


    private void fillListAndNotifyAdapter(ArrayList<MovieResultModel> movieResultModels) {

        movieResultArrayList.addAll(movieResultModels);
        moviesTopRatedRecyclerAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        mSearchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) mSearchItem.getActionView();
        searchView.setIconified(true);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                mSearchItem.collapseActionView();




                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                if (query.length() > 3) {

                    Handler mHandler = new Handler();

                    new Thread(() -> mHandler.post(() -> {

                        if (!swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(true);
                        }

                        if (!query.equals(queryTextFromSearch)) {
                            movieResultArrayList.clear();
                        }
                        currentPage = 1;
                        queryTextFromSearch = query;
                        loadNextDataFromApi(query, currentPage);

                    })).start();
                }
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void loadNextDataFromApi(String queryText, Integer page) {
        fetchMoviesTop(queryText, page);
    }

    private void fetchMoviesTop(String queryText, Integer page) {

        if (!swipeRefreshLayout.isRefreshing()) {

            swipeRefreshLayout.setRefreshing(true);
        }

        MovieModel.Movie.Request request = new MovieModel.Movie.Request(
                ApiConstant.KEY,
                queryText, page
        );
        movieViewModel.getMovies(request);
    }

    @Override
    public void onRefresh() {

        currentPage = 1; // reset

        topRatedRecyclerView.setVisibility(View.GONE);

        loadNextDataFromApi(queryTextFromSearch, currentPage);
    }

    @Override
    public void onScrollToBottom(boolean visible) {

        if (visible) {

            currentPage++;

            loadNextDataFromApi(queryTextFromSearch, currentPage);
        }
    }
}
