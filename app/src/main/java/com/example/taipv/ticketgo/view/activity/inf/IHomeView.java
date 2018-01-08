package com.example.taipv.ticketgo.view.activity.inf;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.taipv.ticketgo.model.GetTicketHighlight;

import java.util.List;

/**
 * Author: Lê Công Long Vũ
 * Date: 10/17/2017
 * Email: leconglongvu@gmail.com
 */
public interface IHomeView extends IBasicView{
    void onGetSuscess(List<GetTicketHighlight> list);
    void onLogoutSuccess(boolean isSuccess);
    void showProgressBar(int type);
    Fragment getFragment();
}