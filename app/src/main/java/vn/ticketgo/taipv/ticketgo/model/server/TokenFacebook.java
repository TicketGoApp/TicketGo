package vn.ticketgo.taipv.ticketgo.model.server;


import android.util.Log;

import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.sdk.callbacks.AbsICmd;
import vn.ticketgo.taipv.ticketgo.model.GetTokenFB;
import vn.ticketgo.taipv.ticketgo.network.ApiFactory;
import vn.ticketgo.taipv.ticketgo.network.IGetTokenFB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.GraphRequest.TAG;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/9/2018
 * Email: tai97nd@gmail.com
 */

public abstract class TokenFacebook extends AbsICmd {
    private long id;
    private String name, email, image;

    public TokenFacebook() {
        run();
    }

    public TokenFacebook(long id, String email, String name, String image) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.image = image;
        run();

    }

    @Override
    protected void getFB() {



        IGetTokenFB iGetTokenFB = ApiFactory.getAPIFB(IGetTokenFB.class);
        Call<GetTokenFB> call = iGetTokenFB.getToken(id, email, name, image);
        call.enqueue(new Callback<GetTokenFB>() {
            @Override
            public void onResponse(Call<GetTokenFB> call, Response<GetTokenFB> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null&&response.body().getToken()!=null){
                        TokenFacebook.this.onSuccess(response.body());

                        MyApplication.log("Tile",response.body().getToken()+"");
                        Log.d(TAG, "onResponse: "+"Null" );

                    }else {
                        Log.d(TAG, "onResponse: "+"Null" );
                    }
                }else {
                    Log.d(TAG, "onResponse: failre");
                }

            }

            @Override
            public void onFailure(Call<GetTokenFB> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);

            }
        });
    }

    @Override
    protected void exception(String message) {

    }

    protected abstract void onSuccess(GetTokenFB getTokenFB);
}
