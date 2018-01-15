package com.example.taipv.ticketgo.view.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.taipv.MyApplication;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.view.activity.BaseActivity;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/15/2018
 * Email: tai97nd@gmail.com
 */

public class EventDetailActivity extends BaseActivity {
    Button btnBookNow;
    ImageView imgLogo;
    TextView tvTitleEvent,tvTimeEvent,tvDateEvent,tvLocationEvent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        event();
    }

    private void event() {
        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPay();
            }

            private void goToPay() {
                startActivity(new Intent(EventDetailActivity.this,PayEvent.class));
            }
        });
    }

    private void init() {
        btnBookNow=findViewById(R.id.btnBookNow);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String image="https://ticketgo.vn/"+intent.getStringExtra("image");
        String startDate=intent.getStringExtra("startdate");
        String endDate=intent.getStringExtra("enddate");
        String location=intent.getStringExtra("location");
        String subTime=startDate.substring(11,16)+" - "+endDate.substring(11,16);
        String subDate;
        if(startDate.substring(0,11).equals(endDate.substring(0,11))){
            subDate=startDate.substring(0,11);
        }else {
            subDate=startDate.substring(0,10)+" - "+endDate.substring(0,10);
        }
        imgLogo=findViewById(R.id.img_logo);
        tvTitleEvent=findViewById(R.id.tv_titleEvent);
        tvDateEvent=findViewById(R.id.tv_date_event);
        tvTimeEvent=findViewById(R.id.tv_timeEvent);
        tvLocationEvent=findViewById(R.id.tv_location_event);
        Glide.with(this).load(image).into(imgLogo);
        tvTitleEvent.setText(name);
        tvTimeEvent.setText(subTime);
        tvDateEvent.setText(subDate);
        tvLocationEvent.setText(location);
        MyApplication.log("intent name",name+"\n"+image+"\n"+startDate+"\n"+endDate+"\n"+location);

    }
}
