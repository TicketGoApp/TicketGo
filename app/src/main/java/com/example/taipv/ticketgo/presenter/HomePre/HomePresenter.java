package com.example.taipv.ticketgo.presenter.HomePre;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.taipv.MyApplication;
import com.example.taipv.sdk.commons.Constants;
import com.example.taipv.ticketgo.model.GetEventHot;
import com.example.taipv.ticketgo.model.server.HomeModel;
import com.example.taipv.ticketgo.presenter.BasicPresenter;
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
    private void iCmd(final Object...objects){
        switch ((int)objects[0]){
            case 1:
                new HomeModel((int)objects[1]){

                    @Override
                    protected void getFB() {

                    }

                    @Override
                    protected void onSuccess(List<GetEventHot> obj) {
                        view.onGetSuscess(obj);
//                        MyApplication.log("Lít",obj.get(1).getName());
                    }

                    @Override
                    protected void onSuccessAll(List<GetEventHot> list) {
                        view.onGetSuccessAll(list);
                    }
                };
        }
    }

    @Override
    protected void getSessionSuccess(Object... params) {

    }
    public void getHome(int page){
        if (!NetworkUtils.isConnected()) {
            showError(Constants.ERROR_NO_INTERNET);
            return;
        }
//        view.showProgressBar(1);
        iCmd(1,page);
    }
}
