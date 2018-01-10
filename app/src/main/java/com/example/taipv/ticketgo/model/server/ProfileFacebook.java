package com.example.taipv.ticketgo.model.server;

import com.example.taipv.sdk.callbacks.AbsICmd;
import com.example.taipv.ticketgo.model.GetInfoFB;
import com.example.taipv.ticketgo.network.ApiFactory;
import com.example.taipv.ticketgo.network.IGetInfoFacebook;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/8/2018
 * Email: tai97nd@gmail.com
 */

public abstract class ProfileFacebook extends AbsICmd {
    private String token;

    public ProfileFacebook(String token) {
        this.token = token;
        run();
    }

    @Override
    protected void invoke() {
        IGetInfoFacebook iGetInfoFacebook = ApiFactory.getAPI(IGetInfoFacebook.class);
        Call<GetInfoFB> call = iGetInfoFacebook.getFacebook(token);
        call.enqueue(new Callback<GetInfoFB>() {
            @Override
            public void onResponse(Call<GetInfoFB> call, Response<GetInfoFB> response) {

            }

            @Override
            public void onFailure(Call<GetInfoFB> call, Throwable t) {

            }
        });
    }

    @Override
    protected void exception(String message) {

    }
}
