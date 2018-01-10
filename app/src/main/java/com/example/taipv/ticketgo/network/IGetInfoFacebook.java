package com.example.taipv.ticketgo.network;

import com.example.taipv.ticketgo.model.GetInfoFB;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/8/2018
 * Email: tai97nd@gmail.com
 */

public interface IGetInfoFacebook {
    @GET("api/user")
    Call<GetInfoFB> getFacebook(
            @Query("api_token") String api_token);
}
