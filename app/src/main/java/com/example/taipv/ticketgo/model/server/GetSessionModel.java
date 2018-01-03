package com.example.taipv.ticketgo.model.server;

import com.example.taipv.sdk.callbacks.AbsICmd;
public abstract class GetSessionModel extends AbsICmd {
//    private BasicModel basicModel = new BasicModel();
//
//    private String json;
//
//    protected GetSessionModel() {
//        String auth = SharedUtils.getInstance().getStringValue(Constants.AUTHENTICATION_ID);
//
//        if (auth != null) {
//            REQ_Authenticate authenticate = new REQ_Authenticate();
//
//            authenticate.setAuthenticationid(auth);
//            authenticate.setService_code(Constants.SERVICE_CODE);
//            json = JsonHelper.toJson(authenticate);
//
//            run();
//        } else {
//            onErrror();
//        }
//    }
//
//    @Override
//    protected void invoke() {
//        String url = basicModel.BASE_NIP + basicModel.AUTHENTICATE;
//
//        basicModel.requestServer.postApi(url, json, new ResponseHandle<RESP_Login>(RESP_Login.class) {
//            @Override
//            protected void onSuccess(RESP_Login obj) {
//
//                SharedUtils.getInstance().putStringValue(Constants.SESSION, obj.getSession());
//                SharedUtils.getInstance().putLongValue(Constants.LOGIN_TIME, obj.getLogin_time());
//                SharedUtils.getInstance().putLongValue(Constants.EXPIRED_TIME, obj.getExpired_time());
//                SharedUtils.getInstance().putIntValue(Constants.TIME_ALIVE, obj.getTime_alive());
//                SharedUtils.getInstance().putIntValue(Constants.DEV_INFO_STATUS, obj.getDev_info_status());
//
//                GetSessionModel.this.onSuccess();
//            }
//
//            @Override
//            protected void onError(Error error) {
//                onErrror();
//            }
//        });
//    }
//
//    @Override
//    protected void exception(String message) {
//        onErrror();
//    }
//
//    protected abstract void onSuccess();
//
//    protected abstract void onErrror();
}