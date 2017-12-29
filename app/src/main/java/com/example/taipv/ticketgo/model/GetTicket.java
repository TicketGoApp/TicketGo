package com.example.taipv.ticketgo.model;

import android.content.Intent;

import com.example.taipv.sdk.callbacks.AbsICmd;
import com.example.taipv.sdk.callbacks.Icmd;
import com.example.taipv.sdk.commons.Constants;
import com.example.taipv.ticketgo.network.ApiFactory;
import com.example.taipv.ticketgo.network.IGetTicketAPI;
import com.example.taipv.ticketgo.presenter.qr.GetTicketPre;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by taipv on 12/27/2017.
 */

public abstract class  GetTicket extends AbsICmd{
    Retrofit retrofit;
 public GetTicket(){
     run();
 }

    @Override
    protected void invoke() {
        retrofit= new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
