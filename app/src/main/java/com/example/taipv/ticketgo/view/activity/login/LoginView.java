package com.example.taipv.ticketgo.view.activity.login;

/**
 * Created by taipv on 12/28/2017.
 */

public interface LoginView {
    void networkError();
    void loginFailure();
    void loginSussess();
    void showProgressBar();
    void hideProgressBar();
    void loginned();
}
