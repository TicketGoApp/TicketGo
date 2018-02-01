package vn.ticketgo.taipv.ticketgo.model.server;

import vn.ticketgo.taipv.sdk.callbacks.AbsICmd;
import vn.ticketgo.taipv.ticketgo.model.GetInfoFB;
import vn.ticketgo.taipv.ticketgo.network.ApiFactory;
import vn.ticketgo.taipv.ticketgo.network.IGetInfoFacebook;

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
