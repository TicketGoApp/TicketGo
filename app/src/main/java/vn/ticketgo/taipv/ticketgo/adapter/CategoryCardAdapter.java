package vn.ticketgo.taipv.ticketgo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.sdk.callbacks.ItemClickListener;
import vn.ticketgo.taipv.ticketgo.model.GetCategoryCard;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/25/2018
 * Email: tai97nd@gmail.com
 */

public class CategoryCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<GetCategoryCard>list;
    Context context;
    ItemClickListener iPassPos;
    public CategoryCardAdapter(List<GetCategoryCard> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        return new ItemHolder(layoutInflater.inflate(vn.ticketgo.taipv.ticketgo.R.layout.item_category_card,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(list.size()>0){

            final GetCategoryCard categoryCard=list.get(position);
            ItemHolder itemHolder= (ItemHolder) holder;
            itemHolder.setData(categoryCard);
            itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyApplication.toast(list.get(position).getCategory());
                    iPassPos.onItemClick(position,categoryCard);
                }
            });
        }else {
            Toast.makeText(context, "NULL", Toast.LENGTH_SHORT).show();
        }
    }
    public void setiPassPos(ItemClickListener iPassPos){
        this.iPassPos=  iPassPos;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class ItemHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        public ItemHolder(View itemView) {
            super(itemView);
            tvCategory=itemView.findViewById(vn.ticketgo.taipv.ticketgo.R.id.tv_category_card);

        }
        public void setData(GetCategoryCard categoryCard){
            tvCategory.setText(categoryCard.getCategory());
        }

    }
}
