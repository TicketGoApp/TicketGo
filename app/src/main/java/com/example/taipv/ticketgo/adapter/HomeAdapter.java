package com.example.taipv.ticketgo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taipv on 1/3/2018
 */

public class    HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG ="xxx" ;
    Context context;
    List<GetEventHot>listTicket;
    private boolean isHideFirstLine;
     ItemClickListener itemClickListener;
    private final int EMPTY_TYPE = 0;
    private final int ITEM_TYPE = 1;
    public HomeAdapter(Context context, List<GetEventHot> listTicket) {
        this.context = context;
        this.listTicket = listTicket;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
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
        if(holder instanceof ItemHoder){
            ItemHoder itemHoder= (ItemHoder) holder;
                final GetEventHot ticket=listTicket.get(position);
                itemHoder.setData(ticket);
                MyApplication.log("adapter",listTicket.get(4).getName());
                itemHoder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClick(0,ticket);
                    }
                });
                itemHoder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClick(-1,ticket);
                    }
                });
        }else if(holder instanceof EmptyHolder){
            EmptyHolder emptyHolder= (EmptyHolder) holder;
            emptyHolder.setData();
        }
    }

    @Override
    public int getItemCount() {
        return listTicket.size()>0 ? listTicket.size() : 1 ;
    }

    private class ItemHoder extends RecyclerView.ViewHolder {
        private ImageView imgTicket;
        private TextView tvTitle,tvDescript,tvTagPrice,tvTagCity;
        public ItemHoder(View itemView) {
            super(itemView);
            imgTicket=itemView.findViewById(R.id.img_ticket);
            tvTitle=itemView.findViewById(R.id.tv_title_ticket);
            tvDescript=itemView.findViewById(R.id.tv_decription);
            tvTagCity=itemView.findViewById(R.id.tv_city);
            tvTagPrice=itemView.findViewById(R.id.tv_price);
        }

        public void setData(GetEventHot ticket) {
//            tvTagPrice.setText(ticket.getAddress());
//            MyApplication.log("GetPriceAdapter",ticket.getPrice());
//            tvTagCity.setText("Ho Chi Minh");
            tvDescript.setText(ticket.getEvent_local_name());
            tvTitle.setText(ticket.getName());
String image="https://ticketgo.vn/"+ticket.getLogo();
            Glide.with(context)
                    .load(image)
                    .into(imgTicket);
        }
    }
    private class EmptyHolder extends RecyclerView.ViewHolder {
        private TextView tvMessage;
        public EmptyHolder(View itemView) {
            super(itemView);
            tvMessage=itemView.findViewById(R.id.tv_message);
        }

        public List<GetEventHot> getListData() {
            return listTicket;
        }

        private void setData(){
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
}
