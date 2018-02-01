package vn.ticketgo.taipv.ticketgo.view.activity.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.sdk.callbacks.ItemClickListener;
import vn.ticketgo.taipv.ticketgo.R;
import vn.ticketgo.taipv.ticketgo.adapter.HomeAdapter;
import vn.ticketgo.taipv.ticketgo.model.GetEventHot;
import vn.ticketgo.taipv.ticketgo.presenter.HomePre.HomePresenter;
import vn.ticketgo.taipv.ticketgo.view.activity.BaseActivity;
import vn.ticketgo.taipv.ticketgo.view.activity.inf.IHomeView;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/30/2018
 * Email: tai97nd@gmail.com
 */

public class EventSaved extends BaseActivity implements IHomeView{
    private static final String TAG = "EventSaved.class";
    HomePresenter homePresenter;
    HomeAdapter homeAdapter;
    RecyclerView recyclerView;
    List<GetEventHot> getList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_saved_activity);
        init();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView=findViewById(R.id.rcv_event_saved);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeAdapter);

    }

    private void init() {
        homePresenter=new HomePresenter(this);
        homePresenter.getHome(1);
        homeAdapter=new HomeAdapter(getApplicationContext());
    }

    @Override
    public void onGetSuscess(List<GetEventHot> list) {
        homeAdapter.addItem(list);
        homeAdapter.notifyDataSetChanged();
//        Log.d(TAG, "initFragment: " + getList.size());
        homeAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object object) {
                MyApplication.log("say ok", position + "");
            }
        });
    }

    @Override
    public void onGetSuccessAll(List<GetEventHot> list) {

    }

    @Override
    public void onLogoutSuccess(boolean isSuccess) {

    }

    @Override
    public void showProgressBar(int type) {

    }

    @Override
    public Fragment getFragment() {
        return null;
    }
}
