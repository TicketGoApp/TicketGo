package com.example.taipv.ticketgo.view.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.view.activity.BaseActivity;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/15/2018
 * Email: tai97nd@gmail.com
 */

public class PayEvent extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_event);
        initFragment();
    }

    private void initFragment() {
//        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.frame_container_pay,)
    }
}
