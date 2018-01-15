package com.example.taipv.ticketgo.model.server;

import android.util.Log;

import com.example.taipv.MyApplication;
import com.example.taipv.sdk.callbacks.AbsICmd;
import com.example.taipv.ticketgo.model.GetEvent;
import com.example.taipv.ticketgo.model.GetEvents;
import com.example.taipv.ticketgo.model.GetEventHot;
import com.example.taipv.ticketgo.network.ApiFactory;
import com.example.taipv.ticketgo.network.IGetEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/4/2018
 * Email: tai97nd@gmail.com
 */

public abstract class HomeModel extends AbsICmd {
    public HomeModel() {
        run();
    }

    @Override
    protected void invoke() {
        IGetEvent getTicket = ApiFactory.getAPIFB(IGetEvent.class);
        Call<GetEvent> call = getTicket.getEvent(1);
        call.enqueue(new Callback<GetEvent>() {
            @Override
            public void onResponse(Call<GetEvent> call, Response<GetEvent> response) {
                if (response.isSuccessful()) {
                    HomeModel.this.onSuccess(response.body().getGetEvents().getEvents());
//                    HomeModel.this.onSuccessAll(response.body().getGetEvents().getEvents());
//                    Log.d("GetEvents", "onResponse: "+response.body().getEventHot().get(0).getName());
                } else {
                    MyApplication.log("GetEvents", "null");
                }
            }

            @Override
            public void onFailure(Call<GetEvent> call, Throwable t) {
                Log.w("getevent", "onFailure: ");
            }
        });


        Call<GetEvent> calls = getTicket.getEventHot(1);
        calls.enqueue(new Callback<GetEvent>() {
            @Override
            public void onResponse(Call<GetEvent> call, Response<GetEvent> response) {
                if (response.isSuccessful()) {
                    HomeModel.this.onSuccessAll(response.body().getGetEventsHot().getEvents());

                } else {
                    MyApplication.log("GetEvents", "null");

                }
            }

            @Override
            public void onFailure(Call<GetEvent> call, Throwable t) {

            }
        });

    }

    @Override
    protected void exception(String message) {

    }

    protected abstract void onSuccess(List<GetEventHot> list);

    protected abstract void onSuccessAll(List<GetEventHot> list);


}
