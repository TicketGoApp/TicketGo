package vn.ticketgo.taipv.ticketgo.presenter.login;

import android.app.Activity;

/**
 * Created by taipv on 12/28/2017.
 */

public interface LoginImplPresenter {
       void checkLogin(String user,String Password);
       void CheckNetwork();
       void checkLogined(String name,String pass);
       void checkLoginFB(String token, Activity activity);
}
