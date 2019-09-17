package com.hadichallenge.yakson.network.request;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIRequest<Element> {

    public void make(Call<Element> call, APIResponseCallback<Element> callback) {

        call.enqueue(new Callback<Element>() {

            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onComplete(response.body());
                    } else {
                        callback.onFailure("body is null", response.code());
                    }
                } else {
                    callback.onFailure("error", response.code());
                }
            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {
                t.getMessage();
                callback.onFailure(t.getMessage(), 404);
            }
        });
    }
}
