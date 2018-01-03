package com.example.taipv.ticketgo.presenter;

import com.example.taipv.sdk.commons.Constants;
import com.example.taipv.ticketgo.model.server.GetSessionModel;
import com.example.taipv.ticketgo.view.activity.inf.IBasicView;

/**
 * Created by taipv on 12/27/2017.
 */

public abstract class BasicPresenter {
    private IBasicView basicView;

    public BasicPresenter(IBasicView basicView) {
        this.basicView = basicView;
    }

    protected void showError(int errorCode, Object... params) {
        if (errorCode == Constants.ERROR_SESSION)
            getNewSession(params);
        else
            basicView.onError(errorCode);
    }

    protected void getNewSession(final Object... params) {

    }

    protected abstract void getSessionSuccess(Object... params);
}