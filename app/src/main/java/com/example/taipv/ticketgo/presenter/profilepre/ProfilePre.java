package com.example.taipv.ticketgo.presenter.profilepre;

import android.content.Intent;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.Utils;
import com.example.taipv.MyApplication;
import com.example.taipv.sdk.commons.Constants;
import com.example.taipv.ticketgo.model.GetTicketHighlight;
import com.example.taipv.ticketgo.model.server.HomeModel;
import com.example.taipv.ticketgo.presenter.BasicPresenter;
import com.example.taipv.ticketgo.view.activity.inf.profile.IProfileView;
import com.example.taipv.ticketgo.view.activity.login.LoginActivity;
import com.facebook.login.LoginManager;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/4/2018
 * Email: tai97nd@gmail.com
 */

public class ProfilePre extends BasicPresenter {
    IProfileView iProfileView;
    public ProfilePre(IProfileView view) {
        super(view);
        this.iProfileView=view;
    }
    private void iCmd(Object...objects){
        switch ((int)objects[0]){
            case 8:
                LoginManager.getInstance().logOut();
                new Intent(Utils.getApp().getApplicationContext(), LoginActivity.class);
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
        iProfileView.showProgressBar(1);
        iCmd(8);
    }
}
