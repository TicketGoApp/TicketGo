package com.example.taipv.ticketgo.view.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taipv.MyApplication;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.adapter.BookNumberAdapter;
import com.example.taipv.ticketgo.model.GetEventHot;
import com.example.taipv.ticketgo.view.activity.BaseActivity;
import com.example.taipv.ticketgo.view.activity.inf.IHomeView;
import com.example.taipv.ticketgo.view.fragment.homefragment.pay.BookNumberTicket;
import com.example.taipv.ticketgo.view.fragment.homefragment.pay.ProceedPay;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/15/2018
 * Email: tai97nd@gmail.com
 */

public class PayEvent extends BaseActivity {
    int position;
    Button btnPay;
    TextView tvTotalMoney;
    private int checkButton=-1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_event);

        btnPay=findViewById(R.id.btnPay);
        tvTotalMoney=findViewById(R.id.tv_total_money);
        tvTotalMoney.setVisibility(View.INVISIBLE);
        init();
        initFragment(BookNumberTicket.newInstance(position));
        nextFragment();

    }

    public void getTotalMoney(int total) {
        tvTotalMoney.setText("Tổng: "+String.valueOf(total)+" VND");
        tvTotalMoney.setVisibility(View.VISIBLE);
    }

    private void init() {
        Bundle bundle=getIntent().getBundleExtra("putBundle");
        position=bundle.getInt("position");
        MyApplication.log("bundle",position+"");

    }

    private void nextFragment() {
        if(checkButton==1){
            btnPay.setText("Tiếp theo");
            checkButton=2;
            btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadFragment(new ProceedPay());
                    if(checkButton==2){
                        btnPay.setText("Xong rồi");
                    }
                }
            });
        }
    }

    public void setTitleActionBar(String title){
    ActionBar actionBar=getSupportActionBar();
    actionBar.setTitle(title);
}

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_pay,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void initFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_pay,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        checkButton=1;
    }

}
