package com.example.taipv.ticketgo.network;

import android.media.session.MediaSession;

import com.example.taipv.ticketgo.model.GetTokenFB;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/9/2018
 * Email: tai97nd@gmail.com
 */

public interface IGetTokenFB {
    @GET("api/facebook")
    Call<GetTokenFB> getToken(
            @Query("facebook_id") long id
            , @Query("email") String email
            , @Query("name") String name
            , @Query("image") String image);
}
