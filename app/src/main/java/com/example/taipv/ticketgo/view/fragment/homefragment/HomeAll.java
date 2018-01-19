package com.example.taipv.ticketgo.view.fragment.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taipv.sdk.callbacks.ItemClickListener;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.adapter.HomeAdapter;
import com.example.taipv.ticketgo.model.GetEventHot;
import com.example.taipv.ticketgo.presenter.HomePre.HomePresenter;
import com.example.taipv.ticketgo.view.activity.HomeActivity;
import com.example.taipv.ticketgo.view.activity.inf.IHomeView;
import com.example.taipv.ticketgo.view.fragment.BasicFragment;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/11/2018
 * Email: tai97nd@gmail.com
 */

public class HomeAll extends BasicFragment implements IHomeView, HomeActivity.OnBackPressedListener {
    private static final String TAG = "xxx";
    HomePresenter homePresenter;
    HomeAdapter homeAdapter;
    RecyclerView recyclerView;

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
        initRecyclerview(view);
        homePresenter.getHome();
        ((HomeActivity) getActivity()).setOnBackPressedListener(this);
    }


    private void initRecyclerview(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getFragment().getContext()));
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
        homeAdapter = new HomeAdapter(getContext(), list);
        homeAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object object) {

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
