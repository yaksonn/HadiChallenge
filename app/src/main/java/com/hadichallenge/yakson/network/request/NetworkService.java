package com.hadichallenge.yakson.network.request;

import com.hadichallenge.yakson.network.core.ApiConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService instance;
    private Retrofit mRetrofit;
    private ApiInterface mApi;

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    private NetworkService() {
        create();
    }

    private void create() {

        Gson gson = new GsonBuilder()
                .create();

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader(ApiConstant.CONTENT_TYPE, ApiConstant.API_CONTENT_TYPE_JSON);
                return chain.proceed(builder.build());
            }
        });

        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.API_PATH)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mApi = mRetrofit.create(ApiInterface.class);
    }

    public ApiInterface getApi() {
        return mApi;
    }

}
