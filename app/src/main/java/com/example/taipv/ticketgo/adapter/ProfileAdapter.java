package com.example.taipv.ticketgo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taipv.MyApplication;
import com.example.taipv.sdk.callbacks.ItemClickListener;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.model.ProfileModel;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/5/2018
 * Email: tai97nd@gmail.com
 */

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    List<ProfileModel>list;
    Context ctx;
    ItemClickListener itemClickListener;

    public ProfileAdapter(List<ProfileModel> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        return new ItemHolder(layoutInflater.inflate(R.layout.item_pro,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ItemHolder){
            final ProfileModel profileModel=list.get(position);

//            MyApplication.log("list2",list.get(8).getTitleProfile()+"");
            ItemHolder itemHolder= (ItemHolder) holder;
            itemHolder.setData(profileModel);
            itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(position,profileModel);
                    MyApplication.toast(position+"");
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        TextView tileProfile;
        ImageView imgIconProfile;
        public ItemHolder(View inflate) {
            super(inflate);
            tileProfile=inflate.findViewById(R.id.tv_title_prof);
            imgIconProfile=inflate.findViewById(R.id.img_icon_prof);
        }
        public void setData(ProfileModel profileModel){
            tileProfile.setText(profileModel.getTitleProfile());
            imgIconProfile.setImageResource(profileModel.getIconProfile());
        }
    }
}
