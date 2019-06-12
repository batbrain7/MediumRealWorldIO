package com.example.mohitkumar.trialapp.data;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public final class Service {
    
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                                                                .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient client = new OkHttpClient.Builder()
                                                    .addInterceptor(interceptor)
                                                    .addInterceptor(new Interceptor() {
                                                        @Override
                                                        public Response intercept(Chain chain) throws IOException {
                                                            Request original = chain.request();
                                                            Request request = original.newBuilder()
                                                                    .addHeader("Content-Type", "application/json")
                                                                    .method(original.method(), original.body())
                                                                    .build();
                                                            return chain.proceed(request);
                                                        }
                                                    })
                                                    .build();

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://conduit.productionready.io/");


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
