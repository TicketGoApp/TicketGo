package com.example.taipv.ticketgo.presenter.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.Utils;
import com.example.taipv.MyApplication;
import com.example.taipv.ticketgo.util.PrefUtil;
import com.example.taipv.ticketgo.view.activity.login.LoginView;
import com.facebook.login.Login;

/**
 * Created by taipv on 12/26/2017.
 */

public class LoginLogicPresenter implements LoginImplPresenter {
    LoginView loginView;
    SharedPreferences preferences;
    SharedPreferences preferen;
    PrefUtil prefUtil;

    public LoginLogicPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void checkLogin(String user, String Password) {

        if (!user.equals("admin") || !Password.equals("admin")) {
            loginView.loginFailure();
        } else {
            loginView.loginSussess();
        }

    }

    @Override
    public void CheckNetwork() {
        if (NetworkUtils.isConnected()) {
        } else {
            loginView.networkError();
            return;
        }
    }

    @Override
    public void checkLogined(String name, String pass) {
         preferences=Utils.getApp().getSharedPreferences("mydata", Context.MODE_PRIVATE);
        String userGet=preferences.getString("email","");
        String passGet=preferences.getString("pass","");
        String token=preferences.getString("tokenFB","");

        MyApplication.log("Sharepre",token);
        if(userGet.equals("admin")&&passGet.equals("admin")){
            loginView.loginned();
        }else {
            return;
        }

    }

//    @Override
//    public boolean isLogined() {
//        if (accountPreferences.getString(ACCOUNT_LOGIN, null) != null) {
//            return true;
//        } else return false;
//    }

    @Override
    public void checkLoginFB(String token, Activity activity) {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
//        return prefs.getString("fb_access_token", null);
//        preferences=Utils.getApp().getSharedPreferences("mydata", Context.MODE_PRIVATE);
//        String tokenFB=preferences.getString("tokenFB","TOKEN");
//        MyApplication.log("TOKENFB",tokenFB);
        prefUtil=new PrefUtil(activity);
        String tokenFB = prefUtil.getToken();
                MyApplication.log("TOKENFB",tokenFB);

        if(token.equals(tokenFB)){
            loginView.loginned();
        }else {
            loginView.loginFailure();
            return;
        }
    }
}
