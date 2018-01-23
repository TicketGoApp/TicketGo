package com.example.taipv.ticketgo.network;

import com.example.taipv.ticketgo.model.GetPost;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/19/2018
 * Email: tai97nd@gmail.com
 */

public interface GetNews {
    @GET("api/vi/posts")
    Call<GetPost>getPost(@Query("page")int page);
}
