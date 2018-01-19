package com.example.taipv.ticketgo.view.fragment.homefragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.taipv.MyApplication;
import com.example.taipv.sdk.callbacks.ItemClickListener;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.adapter.HomeAdapter;
import com.example.taipv.ticketgo.model.GetEventHot;
import com.example.taipv.ticketgo.presenter.HomePre.HomePresenter;
import com.example.taipv.ticketgo.util.EndlessRecyclerViewScrollListener;
import com.example.taipv.ticketgo.view.activity.HomeActivity;
import com.example.taipv.ticketgo.view.activity.inf.IHomeView;
import com.example.taipv.ticketgo.view.fragment.BasicFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeAll extends BasicFragment implements IHomeView, HomeActivity.OnBackPressedListener {
    private static final String TAG = "xxx";
    private EndlessRecyclerViewScrollListener scrollListener;
    HomePresenter homePresenter;
    HomeAdapter homeAdapter;
    RecyclerView recyclerView;
    List<GetEventHot> getList;

    public static HomeAll newInstance(String titlePager) {

        Bundle args = new Bundle();
        args.putString("titlePager", titlePager);
        HomeAll fragment = new HomeAll();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return view;
    }

    private void init() {
        String event = "";
        try {
            event = getArguments().getString("titlePager");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePresenter = new HomePresenter(this);
        getList = new ArrayList<>();
        homePresenter.getHome(1);
        homeAdapter=new HomeAdapter(getContext());
        initRecyclerview(view);
        ((HomeActivity) getActivity()).setOnBackPressedListener(this);
    }


    private void initRecyclerview(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getFragment().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeAdapter);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(page);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    private void loadNextDataFromApi(final int page) {
//        Toast.makeText(getContext(), "Say Load" + page, Toast.LENGTH_SHORT).show();
        showProgressBar(1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                homePresenter.getHome(page);
                closeProgressBar();


            }
        },1000);

    }

    @Override
    public void onGetSuscess(List<GetEventHot> list) {


    }

    @Override
    public void onGetSuccessAll(List<GetEventHot> list) {
        initFragment(list);
        closeProgressBar();
    }

    private void initFragment(List<GetEventHot> list) {
//        this.getList.addAll(list);
        homeAdapter.addItem(list);
        homeAdapter.notifyDataSetChanged();
        Log.d(TAG, "initFragment: " + getList.size());
        homeAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object object) {
                MyApplication.log("say ok", position + "");
            }
        });
    }

    @Override
    public void onLogoutSuccess(boolean isSuccess) {


    }

    @Override
    public void showProgressBar(int type) {
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


    @Override
    public void doBack() {
//MyApplication.toast("say meomeo");
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startActivity(startMain);
    }

//    public boolean allowBackPressed() {
//    }
}