package com.example.taipv.ticketgo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.taipv.MyApplication;
import com.example.taipv.sdk.callbacks.ItemClickListener;

import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.model.GetEventHot;
import com.example.taipv.ticketgo.view.activity.home.EventDetailActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/** 
 * Created by taipv on 1/3/2018 
 */


public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "xxx";
    Context context;
    List<GetEventHot> listTicket;
    private boolean isHideFirstLine;
    private ItemClickListener itemClickListener;
    private final int EMPTY_TYPE = 0;
    private final int ITEM_TYPE = 1;
    public HomeAdapter(Context context) {
        this.context = context;
        listTicket=new ArrayList<>();
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
//            case EMPTY_TYPE:
//                return new EmptyHolder(layoutInflater.inflate(R.layout.item_empty, parent, false));
            case ITEM_TYPE:
                return new ItemHoder(layoutInflater.inflate(R.layout.item_home_fragment, parent, false));
            default:
                return new ItemHoder(layoutInflater.inflate(R.layout.item_home_fragment, parent, false));

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if(listTicket.size()>0){

        if (holder instanceof ItemHoder) {
            ItemHoder itemHoder = (ItemHoder) holder;
                final GetEventHot ticket = listTicket.get(position);

            itemHoder.setData(ticket);
            MyApplication.log("adapter", listTicket.get(4).getName()+listTicket.get(0).getInformation_name());

            itemHoder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(position,ticket);

                    MyApplication.toast(position+"");
                }
            });

            itemHoder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(position,ticket);
                    MyApplication.toast("Toast"+position);
                    Intent intent = new Intent(context, EventDetailActivity.class);
                    Bundle mbundle=new Bundle();
                    mbundle.putString("name", ticket.getName());
                    mbundle.putString("startdate", ticket.getStart_date());
                    mbundle.putString("enddate", ticket.getEnd_date());
                    mbundle.putString("location", ticket.getEvent_local_name() + ", " + ticket.getAddress());
                    mbundle.putString("image", ticket.getLogo());
                    mbundle.putInt("position",position);
                    intent.putExtra("MyTicketBundle",mbundle);

                    DecimalFormat format = new DecimalFormat("###,###,###");
                    if(ticket.getTickets_name().get(0).getPrice()==0){

                        intent.putExtra("price","Miễn phí");
                    }else {
                        intent.putExtra("price",String.valueOf("Từ " + format.format(ticket.getTickets_name().get(0).getPrice()) + " VNĐ"));
                    }
                    context.startActivity(intent);
                }
            });

        } else if (holder instanceof EmptyHolder) {
            EmptyHolder emptyHolder = (EmptyHolder) holder;
            emptyHolder.setData();
        }
            }

    }

    @Override
    public int getItemCount() {
        return listTicket.size() > 0 ? listTicket.size() : 1;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    private class ItemHoder extends RecyclerView.ViewHolder {
        CardView cardView;
        private ImageView imgTicket;
        private TextView tvTitle, tvDescript, tvTagPrice;
        private TextView tvCityTag;

        public ItemHoder(View itemView) {
            super(itemView);
            imgTicket = itemView.findViewById(R.id.img_ticket);
            tvTitle = itemView.findViewById(R.id.tv_title_ticket);
            tvDescript = itemView.findViewById(R.id.tv_decription);
            tvCityTag = itemView.findViewById(R.id.tv_location);
            tvTagPrice = itemView.findViewById(R.id.tv_price);
            cardView = itemView.findViewById(R.id.card_view);
        }

        public void setData(final GetEventHot ticket) {
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            if(ticket.getTickets_name().get(0).getPrice()==0){
                tvTagPrice.setText("Miễn phí");
            }else {
                tvTagPrice.setText("Từ " + formatter.format(ticket.getTickets_name().get(0).getPrice()) + " VNĐ");
            }
//            MyApplication.log("GetPriceAdapter",ticket.getPrice());
            tvDescript.setText(ticket.getEvent_local_name());
            tvTitle.setText(ticket.getName());
            tvCityTag.setText(ticket.getCity_name());
            Log.d(TAG, "TAG CITY: " + ticket.getCity_name());
            String image = "https://ticketgo.vn/" + ticket.getLogo();
            Glide.with(context)
                    .load(image)
                    .into(imgTicket);

        }
    }

    private class EmptyHolder extends RecyclerView.ViewHolder {
        private TextView tvMessage;

        public EmptyHolder(View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message);
        }

        public List<GetEventHot> getListData() {
            return listTicket;
        }

        private void setData() {
            tvMessage.setText("Danh sách vé trống");
        }
    }

    public List<GetEventHot> getListData() {
        List<GetEventHot> list = new ArrayList<>();

        for (GetEventHot ticketList : listTicket) {

            list.add(ticketList);

        }
        return list;
    }
    public void addItem(List<GetEventHot>list){
        listTicket.addAll(list);
        notifyDataSetChanged();
    }
}