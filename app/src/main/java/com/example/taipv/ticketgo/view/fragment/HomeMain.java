package com.example.taipv.ticketgo.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.adapter.ViewpagerAdapter;
import com.example.taipv.ticketgo.model.Fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/11/2018
 * Email: tai97nd@gmail.com
 */

public class HomeMain extends BasicFragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragments> list;
    ViewpagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager(view);
    }

    private void initViewPager(View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.pager);
        list = new ArrayList<>();
        list.add(new Fragments(Home.newInstance("events_hot"), "Event Hot"));
        list.add(new Fragments(Home.newInstance("events"), "All Event"));
        adapter = new ViewpagerAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
