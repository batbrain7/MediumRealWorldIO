package com.example.mohitkumar.trialapp.data;


import com.example.mohitkumar.trialapp.data.LoginSignUp.SUser;
import com.example.mohitkumar.trialapp.data.LoginSignUp.SignUp;
import com.example.mohitkumar.trialapp.data.LoginSignUp.User;
import com.example.mohitkumar.trialapp.data.MainPage.GlobalFeedResponse;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @GET("api/articles")
    Observable<GlobalFeedResponse> getGlobalFeed(@Query("limit") long page, @Query("offset") long offset);

    @GET("api/articles/{slug}")
    Call<SingleArticle> getSingleArticle(@Path("slug") String slug);

    //@GET("/api/articles/{slug}/comments")

}
