package vn.ticketgo.taipv.ticketgo.model;

import vn.ticketgo.taipv.sdk.callbacks.AbsICmd;
import vn.ticketgo.taipv.sdk.commons.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by taipv on 12/27/2017.
 */

public abstract class  GetTicket extends AbsICmd {
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
