package vn.ticketgo.taipv.ticketgo.view.fragment.homefragment.pay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.ticketgo.taipv.ticketgo.R;
import vn.ticketgo.taipv.ticketgo.view.fragment.BasicFragment;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/30/2018
 * Email: tai97nd@gmail.com
 */

public class PaymentMethods extends BasicFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.payment_methob,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
