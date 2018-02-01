package vn.ticketgo.taipv.ticketgo.view.fragment.homefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.ticketgo.taipv.ticketgo.adapter.ViewpagerAdapter;
import vn.ticketgo.taipv.ticketgo.model.Fragments;
import vn.ticketgo.taipv.ticketgo.view.fragment.BasicFragment;

import java.util.ArrayList;
import java.util.List;

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
        return inflater.inflate(vn.ticketgo.taipv.ticketgo.R.layout.main_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager(view);
    }

    private void initViewPager(View view) {
        tabLayout = view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.tab_layout);
        viewPager = view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.pager);
        list = new ArrayList<>();
        list.add(new Fragments(Home.newInstance("events_hot"), "Event Hot"));
        list.add(new Fragments(HomeAll.newInstance("events"), "All Event"));
        adapter = new ViewpagerAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
