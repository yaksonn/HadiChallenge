package com.hadichallenge.yakson.models;

import java.util.ArrayList;

public class MovieTvSeriesCreditsModel {
    private ArrayList<CastModel> cast;

    public ArrayList<CastModel> getCast() {
        return cast;
    }

    public void setCast(ArrayList<CastModel> cast) {
        this.cast = cast;
    }


    public static class Credits {

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

            public MovieTvSeriesCreditsModel castModel;

            public Response(MovieTvSeriesCreditsModel castModel) {
                this.castModel = castModel;
            }
        }
    }
}
