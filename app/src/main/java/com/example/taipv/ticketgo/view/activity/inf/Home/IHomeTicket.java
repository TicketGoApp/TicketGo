package com.example.taipv.ticketgo.view.activity.inf.Home;

import android.app.Activity;

import com.example.taipv.ticketgo.model.GetTicketHighlight;
import com.example.taipv.ticketgo.view.activity.inf.IBasicView;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/3/2018
 * Email: tai97nd@gmail.com
 */

public interface IHomeTicket extends IBasicView {
    void getSuccess(List<GetTicketHighlight>list);
    Activity getActivity();
}
