package vn.ticketgo.taipv.ticketgo.model.server;


import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.sdk.callbacks.AbsICmd;
import vn.ticketgo.taipv.ticketgo.model.GetPost;
import vn.ticketgo.taipv.ticketgo.model.PostNews;
import vn.ticketgo.taipv.ticketgo.network.ApiFactory;
import vn.ticketgo.taipv.ticketgo.network.GetNews;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/19/2018
 * Email: tai97nd@gmail.com
 */

public abstract class NewsModel extends AbsICmd {
    int page=1;
    public NewsModel(int page){
        this.page=page;
        run();
    }
    @Override
    protected void invoke() {
        GetNews getNews= ApiFactory.getAPIFB(GetNews.class);
        Call<GetPost>call=getNews.getPost(page);
        call.enqueue(new Callback<GetPost>() {
            @Override
            public void onResponse(Call<GetPost> call, Response<GetPost> response) {
                if(response.isSuccessful()){
                    MyApplication.log("getPostNews",response.body().getGetPosts().getData().get(0).getTitle());
                    onSuccess(response.body().getGetPosts().getData());
                }else {
                    MyApplication.log("getPostNew","getFailre");
                }
            }

            @Override
            public void onFailure(Call<GetPost> call, Throwable t) {
                MyApplication.log("getPostNew",t.getMessage());
            }
        });
    }

    @Override
    protected void exception(String message) {

    }
    public abstract void onSuccess(List<PostNews>list);
}
