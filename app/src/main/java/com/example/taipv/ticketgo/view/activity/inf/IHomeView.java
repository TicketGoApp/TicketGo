package com.example.taipv.ticketgo.view.activity.inf;

import android.support.v4.app.Fragment;

import com.example.taipv.ticketgo.model.GetEventHot;

import java.util.List;

/**
 * Author: Lê Công Long Vũ
 * Date: 10/17/2017
 * Email: leconglongvu@gmail.com
 */
public interface IHomeView extends IBasicView{
    void onGetSuscess(List<GetEventHot> list);
    void onLogoutSuccess(boolean isSuccess);
    void showProgressBar(int type);
    Fragment getFragment();
}