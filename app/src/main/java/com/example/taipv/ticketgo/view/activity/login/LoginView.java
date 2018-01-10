package com.example.taipv.ticketgo.view.activity.login;

import com.example.taipv.ticketgo.view.activity.inf.IBasicView;

/**
 * Created by taipv on 12/28/2017.
 */

public interface LoginView extends IBasicView{
    void networkError();
    void loginFailure();
    void loginSussess();
    void showProgressBar();
    void hideProgressBar();
    void loginned();
}
