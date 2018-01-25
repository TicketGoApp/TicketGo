package com.example.taipv.ticketgo.view.fragment;
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

import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.adapter.CategoryCardAdapter;
import com.example.taipv.ticketgo.model.GetCategoryCard;
import com.example.taipv.ticketgo.view.activity.inf.IPointView;

import java.util.ArrayList;
import java.util.List;

public class MyOder extends BasicFragment implements IPointView {
    TextView tvNameCard,tvPointCard,tvMemberCrate;
    RecyclerView rcvCard;
    GetCategoryCard categoryCard;
    List<GetCategoryCard>list;
    CategoryCardAdapter adapter;
    private String[]listcate={"Go Point là gì?","Lợi ích Go Point?","Cách quy đổi Go Point?","Cách kiếm Go Point?",};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_oder,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initRecycleView();
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getFragment().getContext());
        rcvCard.setHasFixedSize(true);
        rcvCard.setLayoutManager(linearLayoutManager);
        list=new ArrayList<>();
        for(int i=0;i<listcate.length;i++){
            categoryCard=new GetCategoryCard(listcate[i]);
            list.add(categoryCard);
        }
        adapter=new CategoryCardAdapter(list,getContext());
        rcvCard.setAdapter(adapter);
    }

    private void init(View view) {
        tvNameCard=view.findViewById(R.id.tv_name_card);
        tvPointCard=view.findViewById(R.id.tv_point_point);
        tvMemberCrate=view.findViewById(R.id.member_since);
        rcvCard=view.findViewById(R.id.rcv_card_category);

    }

    @Override
    public void showProgressBar(int type) {

    }

    @Override
    public Fragment getFragment() {
        return this;
    }
}
