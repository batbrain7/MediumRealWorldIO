package com.example.mohitkumar.trialapp.data;


import com.example.mohitkumar.trialapp.data.LoginSignUp.User;
import com.example.mohitkumar.trialapp.data.MainPage.GlobalFeedResponse;
import com.example.mohitkumar.trialapp.data.Settings.ProfileResponse;
import com.example.mohitkumar.trialapp.data.comment.Comment;
import com.example.mohitkumar.trialapp.data.comment.Comments;
import com.example.mohitkumar.trialapp.data.comment.PostComment;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @GET("api/articles")
    Observable<GlobalFeedResponse> getGlobalFeed(@Query("limit") long page, @Query("offset") long offset);

    @GET("api/articles/{slug}")
    Call<SingleArticle> getSingleArticle(@Path("slug") String slug);

    @GET("/api/articles/{slug}/comments")
    Call<Comments> getCommentsArticle(@Path("slug") String slug);

    @POST("/api/articles/{slug}/comments")
    Call<Comment> postComment(@Path("slug") String slug, @Body PostComment comment);

    @POST("/api/articles")
    Call<SingleArticle> postArticle(@Body WriteArticle article);

    @GET("/api/profiles/{username}")
    Call<ProfileResponse> getProfile(@Path("username") String username, @Body String string);

    @PUT("/api/user")
    Call<User> updateProfile(@Body String object);

}
