package com.example.pmdmentregas3.firebase;

import com.twitter.sdk.android.core.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("users/show.json")
    Call<User> getUserDetails(@Query("screen_name") String screenName);
}
