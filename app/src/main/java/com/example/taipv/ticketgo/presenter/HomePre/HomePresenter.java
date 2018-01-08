package com.example.taipv.ticketgo.presenter.HomePre;

import com.blankj.utilcode.util.NetworkUtils;
import com.bumptech.glide.Glide;
import com.example.taipv.MyApplication;
import com.example.taipv.sdk.commons.Constants;
import com.example.taipv.ticketgo.model.GetTicketHighlight;
import com.example.taipv.ticketgo.model.server.HomeModel;
import com.example.taipv.ticketgo.presenter.BasicPresenter;
import com.example.taipv.ticketgo.view.activity.inf.IBasicView;
import com.example.taipv.ticketgo.view.activity.inf.IHomeView;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/3/2018
 * Email: tai97nd@gmail.com
 */

public class HomePresenter  extends BasicPresenter{
    private IHomeView view;
    public HomePresenter(IHomeView view) {
        super(view);
        this.view=view;
    }
    private void iCmd(Object...objects){
        switch ((int)objects[0]){
            case 1:
                new HomeModel(){

                    @Override
                    protected void onSuccess(List<GetTicketHighlight> obj) {
                        view.onGetSuscess(obj);
                        MyApplication.log("Lít",obj.get(1).getTitle());
                    }
                };
        }
    }

    @Override
    protected void getSessionSuccess(Object... params) {

    }
    public void getHome(){
        if (!NetworkUtils.isConnected()) {
            showError(Constants.ERROR_NO_INTERNET);
            return;
        }
        view.showProgressBar(1);
        iCmd(1);
    }
}
