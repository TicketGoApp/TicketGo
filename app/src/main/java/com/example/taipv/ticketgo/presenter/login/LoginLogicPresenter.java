package com.example.taipv.ticketgo.presenter.login;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.Utils;
import com.example.taipv.ticketgo.view.activity.login.LoginView;
import com.facebook.login.Login;

/**
 * Created by taipv on 12/26/2017.
 */

public class LoginLogicPresenter implements LoginImplPresenter {
    LoginView loginView;

    public LoginLogicPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void checkLogin(String user, String Password) {

        if(NetworkUtils.isConnected()){
            if (!user.equals("admin") || !Password.equals("admin")) {
                loginView.loginFailure();
            }else {
                loginView.loginSussess();
            }
        }else {
            loginView.networkError();
        }
    }
}
