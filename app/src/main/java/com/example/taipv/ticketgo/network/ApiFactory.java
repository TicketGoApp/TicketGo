package com.example.taipv.ticketgo.network;


import com.example.taipv.sdk.commons.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by taipv on 12/27/2017.
 */

public class ApiFactory {
    public static <T> T getAPI(Class<T>tClass){
        return getTicketRetrofit().create(tClass);
    }

    private static Retrofit getTicketRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}