package com.example.taipv.ticketgo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.taipv.MyApplication;
import com.example.taipv.sdk.callbacks.IPassPos;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.model.TicketName;
import com.example.taipv.ticketgo.view.activity.home.PayEvent;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/16/2018
 * Email: tai97nd@gmail.com
 */

public class BookNumberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    IPassPos iPassPos;
    private Context context;
    private List<TicketName> listTicket;
    private int positi = -1;
    int totalMoney=-1;
//    private TicketName ticketName;

    public BookNumberAdapter(Context context, List<TicketName> listTicket, int position) {
        this.context = context;
        this.listTicket = listTicket;
        this.positi = position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ItemHoder(layoutInflater.inflate(R.layout.item_ticket_book, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemHoder itemHoder = (ItemHoder) holder;
        TicketName ticketName = listTicket.get(position);

        itemHoder.setData(ticketName);
    }
    public void setiPassPos(IPassPos iPassPos){
        this.iPassPos=iPassPos;
    }
    @Override
    public int getItemCount() {
        return listTicket.size() > 0 ? listTicket.size() : 1;
    }

    class ItemHoder extends RecyclerView.ViewHolder {
        private TextView tvTypeTicket, tvNumberTicket;
        private Button btnIncrease, btnReduce;

        ItemHoder(View itemView) {
            super(itemView);
            tvNumberTicket = itemView.findViewById(R.id.tv_number_ticket);
            tvTypeTicket = itemView.findViewById(R.id.tv_type_ticket);
            btnIncrease = itemView.findViewById(R.id.btn_increase);
            btnReduce = itemView.findViewById(R.id.btn_reduced);
            event();
        }

        private void event() {
            final int[] count = {0};

            btnIncrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count[0]++;
                    tvNumberTicket.setText(String.valueOf(count[0]));
                    btnReduce.setBackgroundColor(Color.TRANSPARENT);
    iPassPos.passPos(count[0]);

                }
            });
            btnReduce.setBackgroundColor(Color.GRAY);
            btnReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(count[0] >0){
                        count[0]--;
                        btnReduce.setBackgroundColor(Color.TRANSPARENT);
                        tvNumberTicket.setText(String.valueOf(count[0]));
                        if(count[0] ==0){
                            btnReduce.setBackgroundColor(Color.GRAY);
                            count[0] =0;
                        }
                    }
                }
            });
        }

        public void setData(TicketName ticketName) {
            tvTypeTicket.setText(ticketName.getPrice() + "");
            int money=ticketName.getPrice()*Integer.parseInt(tvNumberTicket.getText().toString());

            MyApplication.log("money",tvNumberTicket.getText().toString());
//                MyApplication.log("getSoLuong",getEventHot.getTickets_name().get(0).getPrice()+"123");

        }
    }
}
