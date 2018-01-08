package com.example.taipv.ticketgo.view.activity.inf.profile;


import android.support.v4.app.Fragment;

import com.example.taipv.ticketgo.model.GetTicketHighlight;
import com.example.taipv.ticketgo.view.activity.inf.IBasicView;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/4/2018
 * Email: tai97nd@gmail.com
 */

public interface IProfileView extends IBasicView{
    void showProgressBar();
    void onGetSuscess();
    void onLogoutSuccess(boolean isSuccess);
    void showProgressBar(int type);
    Fragment getFragment();
}
