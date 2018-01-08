package com.example.taipv.ticketgo.model.server;

import com.example.taipv.MyApplication;
import com.example.taipv.sdk.callbacks.AbsICmd;
import com.example.taipv.ticketgo.model.GetListTicket;
import com.example.taipv.ticketgo.model.GetTicketHighlight;
import com.example.taipv.ticketgo.network.ApiFactory;
import com.example.taipv.ticketgo.network.IGetTicketHighlight;

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
        IGetTicketHighlight getTicket=ApiFactory.getAPI(IGetTicketHighlight.class);
        Call<List<GetTicketHighlight>>call=getTicket.getTicketHigh();
        call.enqueue(new Callback<List<GetTicketHighlight>>() {
            @Override
            public void onResponse(Call<List<GetTicketHighlight>> call, Response<List<GetTicketHighlight>> response) {
                HomeModel.this.onSuccess(response.body());
                MyApplication.log("test",response.body().get(1).getTitle());
            }
            @Override
            public void onFailure(Call<List<GetTicketHighlight>> call, Throwable t) {
            }
        });
    }

    @Override
    protected void exception(String message) {

    }
    protected abstract void onSuccess(List<GetTicketHighlight> list);

}
