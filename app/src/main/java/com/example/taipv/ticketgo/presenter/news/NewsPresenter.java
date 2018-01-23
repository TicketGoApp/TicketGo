package com.example.taipv.ticketgo.presenter.news;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.taipv.sdk.commons.Constants;
import com.example.taipv.ticketgo.model.PostNews;
import com.example.taipv.ticketgo.model.server.NewsModel;
import com.example.taipv.ticketgo.presenter.BasicPresenter;
import com.example.taipv.ticketgo.view.activity.inf.IBasicView;
import com.example.taipv.ticketgo.view.activity.news.INewsView;

import java.util.List;

/**
 * Created by taipv on 12/26/2017.
 */

public class NewsPresenter extends BasicPresenter {
    INewsView view;
    public NewsPresenter(INewsView basicView) {
        super(basicView);
        this.view=basicView;
    }

    private void iCmd(Object...objects){
        switch ((int)objects[0]){
            case 1:
                new NewsModel((int) objects[1]) {
                    @Override
                    public void onSuccess(List<PostNews> list) {
                            view.onSuccess(list);
                    }

                    @Override
                    protected void getFB() {

                    }
                };
                break;
        }
    }
    public void getPostNews(int page){
        if (!NetworkUtils.isConnected()) {
            showError(Constants.ERROR_NO_INTERNET);
            return;
        }
//        view.showProgressBar(1);
        iCmd(1,page);
    }
    @Override
    protected void getSessionSuccess(Object... params) {

    }
}
