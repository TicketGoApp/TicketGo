package vn.ticketgo.taipv.ticketgo.presenter.profilepre;

import android.content.Intent;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.Utils;
import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.sdk.commons.Constants;
import vn.ticketgo.taipv.ticketgo.model.GetInfoFB;
import vn.ticketgo.taipv.ticketgo.model.GetTokenFB;
import vn.ticketgo.taipv.ticketgo.model.server.InfoFacebook;
import vn.ticketgo.taipv.ticketgo.model.server.TokenFacebook;
import vn.ticketgo.taipv.ticketgo.presenter.BasicPresenter;
import vn.ticketgo.taipv.ticketgo.view.activity.inf.profile.IProfileView;
import vn.ticketgo.taipv.ticketgo.view.activity.login.LoginActivity;

import com.facebook.login.LoginManager;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/4/2018
 * Email: tai97nd@gmail.com
 */

public class ProfilePre extends BasicPresenter {
    IProfileView view;
    public ProfilePre(IProfileView view) {
        super(view);
        this.view=view;
    }
    private void iCmd(Object...objects){
        switch ((int)objects[0]){

            case 8:
                LoginManager.getInstance().logOut();
                new Intent(Utils.getApp().getApplicationContext(), LoginActivity.class);
                break;
            case 9:
                new TokenFacebook((long)objects[1], (String)objects[2], (String)objects[3], (String)objects[4]) {
                    @Override
                    protected void invoke() {

                    }

                    @Override
                    protected void onSuccess(GetTokenFB getTokenFB) {
                        String getToken=getTokenFB.getToken();
                        MyApplication.log("TOKENFB123", getToken);
                        new InfoFacebook(getToken) {
                            @Override
                            protected void onSuccess(GetInfoFB getInfoFB) {
                                    MyApplication.log("Myinfo",getInfoFB.getName()+getInfoFB.getEmail()+getInfoFB.getImage());
                                    view.onGetSuscess(getInfoFB);
                            }

                            @Override
                            protected void getFB() {
                                view.showProgressBar(1);

                            }
                        };
                    }
                };
        }
    }

    @Override
    protected void getSessionSuccess(Object... params) {

    }
    public void getLogout(){
        if (!NetworkUtils.isConnected()) {
            showError(Constants.ERROR_NO_INTERNET);
            return;
        }
        view.showProgressBar(1);
        iCmd(8);
    }

    public void getToken(long id, String email, String name, String image) {
        if (!NetworkUtils.isConnected()) {
            showError(Constants.ERROR_NO_INTERNET);
            return;
        }
        view.showProgressBar(1);
        iCmd(9,id,email,name,image);
    }
    public void getContactUs(){
        iCmd(7);
    }
}
