package com.example.mohitkumar.trialapp.data;

import com.example.mohitkumar.trialapp.data.loginsignup.LUser;
import com.example.mohitkumar.trialapp.data.loginsignup.SUser;
import com.example.mohitkumar.trialapp.data.loginsignup.User;
import com.example.mohitkumar.trialapp.data.mainpage.GlobalFeedResponse;
import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;
import com.example.mohitkumar.trialapp.data.comment.Comment;
import com.example.mohitkumar.trialapp.data.comment.CommentResponse;
import com.example.mohitkumar.trialapp.data.comment.PostComment;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @POST("api/users/login")
    Call<User> login(@Body LUser user);

    @POST("api/users")
    Call<User> signUp(@Body SUser user);

    @GET("api/articles")
    Observable<GlobalFeedResponse> getGlobalFeed(@Query("limit") long page, @Query("offset") long offset);

    @GET("api/articles/{slug}")
    Call<SingleArticle> getSingleArticle(@Path("slug") String slug);

    @GET("api/articles/{slug}/comments")
    Call<CommentResponse> getCommentsArticle(@Path("slug") String slug);

    @POST("api/articles/{slug}/comments")
    Call<Comment> postComment(@Path("slug") String slug, @Body PostComment comment);

    @POST("api/articles/{slug}/favorite")
    Call<SingleArticle> favoriteArticleInCommentActivity(@Path("slug") String slug);

    @DELETE("api/articles/{slug}/favorite")
    Call<SingleArticle> unFavoriteArticle(@Path("slug") String slug);

    @POST("api/articles")
    Call<SingleArticle> postArticle(@Body WriteArticle article);

    @GET("api/profiles/{username}")
    Call<ProfileResponse> getProfile(@Path("username") String username, @Body String string);

    @PUT("api/user")
    Call<User> updateProfile(@Body String object);

    @GET("api/articles/feed")
    Observable<GlobalFeedResponse> getMyFeed(@Query("limit") long page, @Query("offset") long offset);

    @POST("api/articles/{slug}/favorite")
    Observable<SingleArticle> favoriteArticle(@Path("slug") String slug);


}
