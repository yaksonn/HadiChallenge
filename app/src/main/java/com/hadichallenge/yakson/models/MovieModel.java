package com.hadichallenge.yakson.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieModel {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private ArrayList<MovieResultModel> results = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<MovieResultModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieResultModel> results) {
        this.results = results;
    }

    public static class Movie {

        public static class Request {
            public String api_key;
            public String query;
            public Integer page;

            public Request(
                           String api_key, String query, Integer page) {
                this.api_key = api_key;
                this.query = query;
                this.page = page;
            }
        }

        public static class Response {

            public MovieModel movieResultModels;

            public Response(MovieModel movieModel) {
                this.movieResultModels = movieModel;
            }
        }
    }

    public static class MovieDetail {

        public static class Request {

            public String type;
            public Integer movieId;
            public String api_key;
            public String language;

            public Request(String type, Integer movieId, String api_key, String language) {
                this.type = type;
                this.movieId = movieId;
                this.api_key = api_key;
                this.language = language;
            }
        }

        public static class Response {

            public MovieResultModel movieResultModels;

            public Response(MovieResultModel movieModel) {
                this.movieResultModels = movieModel;
            }
        }
    }

}
