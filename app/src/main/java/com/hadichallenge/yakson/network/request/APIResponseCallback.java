package com.hadichallenge.yakson.network.request;

public interface APIResponseCallback<T> {
    void onComplete(T response);
    void onFailure(String message, Integer statusCode);
}

