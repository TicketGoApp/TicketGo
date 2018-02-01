package vn.ticketgo.taipv.ticketgo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;
import java.util.List;

import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.ticketgo.R;
import vn.ticketgo.taipv.ticketgo.model.GetAddress;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/30/2018
 * Email: tai97nd@gmail.com
 */

public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "AddressAdapter.class";
    List<GetAddress>getList;

    public AddressAdapter() {
        getList=new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        return new ItemHolder(inflater.inflate(R.layout.item_address,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyApplication.log(TAG,getList.size()+"");
            if(getList.size()>0){
                GetAddress getAddress=getList.get(position);
                MyApplication.log(TAG,getList.get(0).getCity());
                ItemHolder itemHolder= (ItemHolder) holder;
                itemHolder.setData(getAddress);

            }else {
                MyApplication.log(TAG,"List null");
            }
    }

    @Override
    public int getItemCount() {
        return getList.size();
    }
    class ItemHolder extends RecyclerView.ViewHolder {
        TextView tvUser,tvPhone,tvAddress,tvEmail;
        public ItemHolder(View itemView) {

            super(itemView);
            tvAddress=itemView.findViewById(R.id.tv_address_address);
            tvUser=itemView.findViewById(R.id.tv_user_address);
            tvPhone=itemView.findViewById(R.id.tv_phone_address);
            tvEmail=itemView.findViewById(R.id.tv_email_address);
        }
        public void setData(GetAddress getAddress){
            tvUser.setText(getAddress.getName());
            tvPhone.setText(getAddress.getPhone());
            tvAddress.setText(getAddress.getAddress());
            tvEmail.setText(getAddress.getEmail());
        }
    }
    public void addItem(List<GetAddress>list){
        getList.addAll(list);
        notifyDataSetChanged();
    }
    public void addItem(GetAddress getAddress){
        getList.add(getAddress);
        MyApplication.log(TAG,getAddress.getCity());
        notifyDataSetChanged();
    }
}
