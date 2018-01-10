package com.example.taipv.ticketgo.model.server;

import android.util.Log;

import com.example.taipv.MyApplication;
import com.example.taipv.sdk.callbacks.AbsICmd;
import com.example.taipv.ticketgo.model.GetInfoFB;
import com.example.taipv.ticketgo.network.ApiFactory;
import com.example.taipv.ticketgo.network.IGetInfoFacebook;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/10/2018
 * Email: tai97nd@gmail.com
 */

public abstract class InfoFacebook extends AbsICmd {
    String token;
    public InfoFacebook(String token) {
        this.token=token;
        run();
    }
    @Override
    protected void invoke() {
        IGetInfoFacebook getInfo = ApiFactory.getAPIFB(IGetInfoFacebook.class);
        Call<GetInfoFB>call=getInfo.getFacebook(token);
        call.enqueue(new Callback<GetInfoFB>() {
            @Override
            public void onResponse(Call<GetInfoFB> call, Response<GetInfoFB> response) {
                if(response.isSuccessful()){
                        if(response.body()!=null){
                            MyApplication.log("Facebookinfo",response.body().getName());
                            InfoFacebook.this.onSuccess(response.body());
                        }
                }else {
                    MyApplication.log("Facebookinfo","null");
                }
            }

            @Override
            public void onFailure(Call<GetInfoFB> call, Throwable t) {
                Log.d("xxx", "onFailure: "+t );
            }
        });
    }

    @Override
    protected void exception(String message) {

    }
    protected abstract void onSuccess(GetInfoFB getInfoFB);
}
