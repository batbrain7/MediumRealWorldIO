package com.example.mohitkumar.trialapp.data;

import android.util.Log;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public final class CreateService {
    
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                                                                .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient client = new OkHttpClient.Builder()
                                                    .addInterceptor(interceptor)
                                                    .build();

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://conduit.productionready.io/api/");


    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = retrofitBuilder.build();
        }
        return retrofit;
    }

    private static API api = getInstance().create(API.class);

    public static API getApi() {
        return api;
    }

}
