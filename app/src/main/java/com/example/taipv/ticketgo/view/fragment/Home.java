package com.example.taipv.ticketgo.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blankj.utilcode.util.Utils;
import com.example.taipv.MyApplication;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.adapter.HomeAdapter;
import com.example.taipv.ticketgo.model.GetTicket;
import com.example.taipv.ticketgo.model.GetTicketHighlight;
import com.example.taipv.ticketgo.presenter.HomePre.HomePresenter;
import com.example.taipv.ticketgo.view.activity.inf.IHomeView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Home extends BasicFragment implements IHomeView{
    private static final String TAG = "xxx";
    HomePresenter homePresenter;
    HomeAdapter homeAdapter;
    RecyclerView recyclerView;
    List<GetTicketHighlight>listT;
    public static Home newInstance() {

        Bundle args = new Bundle();
        
        Home fragment = new Home();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePresenter=new HomePresenter(this);
        initRecyclerview(view);
        homePresenter.getHome();

    }

    private void initRecyclerview(View view) {
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getFragment().getContext()));
//        List<GetTicketHighlight>list=new ArrayList<>();
//        GetTicketHighlight ticketHighlight=new GetTicketHighlight();
//        ticketHighlight.setPrice("59999");
//        ticketHighlight.setImage("https://api.androidhive.info/json/movies/thor_ragnarok.jpg");
//        ticketHighlight.setTitle("Toi Dep Trai");
//        list.add(ticketHighlight);
//        homeAdapter=new HomeAdapter(getContext(),list);
//        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    public void onGetSuscess(List<GetTicketHighlight> list) {
        initFragment(list);
        closeProgressBar();
    }

    private void initFragment(List<GetTicketHighlight> list) {
        homeAdapter=new HomeAdapter(getContext(),list);
        homeAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    public void onLogoutSuccess(boolean isSuccess) {



    }

    @Override
    public  void showProgressBar(int type) {
        String message;

        switch (type) {
            case 1:
                message = getString(R.string.doing_load_data);
                break;
            case 3:
                message = getString(R.string.doing_mbr_delete_hr);
                break;
            default:
                message = getString(R.string.doing_do);
                break;
        }
        showProgressBar(false, false, message);

    }
    @Override
    public Fragment getFragment() {
        return this;
    }
}
