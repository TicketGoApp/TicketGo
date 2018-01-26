package com.example.taipv.ticketgo.view.activity.inf;

import android.support.v4.app.Fragment;

import com.example.taipv.ticketgo.model.GetEventHot;
import com.example.taipv.ticketgo.model.GetInfoFB;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/25/2018
 * Email: tai97nd@gmail.com
 */

public interface IPointView {
    void showProgressBar(int type);
    Fragment getFragment();
}
