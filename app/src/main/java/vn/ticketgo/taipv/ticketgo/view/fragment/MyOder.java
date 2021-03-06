package vn.ticketgo.taipv.ticketgo.view.fragment;

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
import android.widget.TextView;

import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.sdk.callbacks.ItemClickListener;
import vn.ticketgo.taipv.ticketgo.adapter.CategoryCardAdapter;
import vn.ticketgo.taipv.ticketgo.model.GetCategoryCard;
import vn.ticketgo.taipv.ticketgo.util.SharedUtils;
import vn.ticketgo.taipv.ticketgo.view.activity.inf.IPointView;
import vn.ticketgo.taipv.ticketgo.view.activity.point.EarnPoint;
import vn.ticketgo.taipv.ticketgo.view.activity.point.PointBenefit;
import vn.ticketgo.taipv.ticketgo.view.activity.point.RedeemPoint;
import vn.ticketgo.taipv.ticketgo.view.activity.point.WhatIsPoint;

import java.util.ArrayList;
import java.util.List;

public class MyOder extends BasicFragment implements IPointView {
    TextView tvNameCard, tvPointCard, tvMemberCrate,tvTitleCard;
    RecyclerView rcvCard;
    GetCategoryCard categoryCard;
    List<GetCategoryCard> list;
    CategoryCardAdapter adapter;
    String name;
    private static String TAG = "MyOder.class";
    private String[] listcate = {"Go Point là gì?", "Lợi ích Go Point?", "Cách quy đổi Go Point?", "Cách kiếm Go Point?",};



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(vn.ticketgo.taipv.ticketgo.R.layout.fragment_my_oder, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initRecycleView();
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getFragment().getContext());
        rcvCard.setHasFixedSize(true);
        rcvCard.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        for (int i = 0; i < listcate.length; i++) {
            categoryCard = new GetCategoryCard(listcate[i]);
            list.add(categoryCard);
        }
        adapter = new CategoryCardAdapter(list, getContext());
        adapter.setiPassPos(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object object) {
                switch (position){
                    case 0:
            MyApplication.log(TAG,"ahihi");
                        startActivity(new Intent(getActivity(),WhatIsPoint.class));

                        break;
                    case 1:
                        MyApplication.log(TAG,name);
                        startActivity(new Intent(getActivity(),PointBenefit.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(),RedeemPoint.class));
                        break;

                    case 3:
                startActivity(new Intent(getActivity(),EarnPoint.class));
                        break;
                }
            }
        });
        rcvCard.setAdapter(adapter);
    }

    private void init(View view) {
//        MyApplication.log(TAG, SharedUtils.getInstance().getStringValue("name"));

        tvNameCard = view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.tv_name_card);
        tvPointCard = view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.tv_point_point);
        tvMemberCrate = view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.member_since);
        rcvCard = view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.rcv_card_category);
        tvTitleCard=view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.tv_card_title_name);
        tvNameCard.setText(SharedUtils.getInstance().getStringValue("name"));
        tvTitleCard.setText(SharedUtils.getInstance().getStringValue("name"));
    }


    @Override
    public void showProgressBar(int type) {

    }

    @Override
    public Fragment getFragment() {
        return this;
    }


}