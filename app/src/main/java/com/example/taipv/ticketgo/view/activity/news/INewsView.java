package com.example.taipv.ticketgo.view.activity.news;

import android.support.v4.app.Fragment;

import com.example.taipv.ticketgo.view.activity.inf.IBasicView;

/**
 * Created by taipv on 12/26/2017.
 */

public interface INewsView extends IBasicView{
    void showProgressBar(int type);
    Fragment getFragment();
}
