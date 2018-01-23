package com.example.taipv.ticketgo.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.taipv.MyApplication;
import com.example.taipv.sdk.commons.Constants;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.model.PostNews;
import com.example.taipv.ticketgo.util.EndlessRecyclerViewScrollListener;
import com.example.taipv.ticketgo.view.activity.news.WebViewNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/19/2018
 * Email: tai97nd@gmail.com
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<PostNews> listPost;
    Context context;
    private boolean loadmore = true;
    private final int ITEM = 1;
    private final int LOAD = 2;

    public NewsAdapter(Context context) {
        this.context = context;
        listPost = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM) {
            return new ItemHolder(inflater.inflate(R.layout.item_news, parent, false));
        } else {
            return new LoadMore(inflater.inflate(R.layout.item_loadmore, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (listPost.size() > 0) {


            if (holder instanceof ItemHolder) {
                PostNews postNews = listPost.get(position);
                ItemHolder itemHolder = (ItemHolder) holder;
                itemHolder.setData(postNews,position);

            } else {
                if (loadmore) {
                    holder.itemView.setVisibility(View.VISIBLE);
                } else {
                    holder.itemView.setVisibility(View.GONE);
                }
            }
        } else {
            MyApplication.log("recyclerview", "get failre");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position < listPost.size() ? ITEM : LOAD;
    }

    @Override
    public int getItemCount() {
        return listPost.size() + 1;
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        ImageView imgNews;
        TextView tvCategory, tvTitleNews;
        CardView cardView;

        public ItemHolder(View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.img_news);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvTitleNews = itemView.findViewById(R.id.tv_title_news);
            cardView = itemView.findViewById(R.id.card_view_news);
        }

        public void setData(PostNews postNews, final int pos) {
            String image = Constants.URL_TICKET + postNews.getImage();
            Glide.with(context).load(image).into(imgNews);
            tvTitleNews.setText(postNews.getTitle());
            tvCategory.setText(postNews.getCategory());
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,WebViewNews.class);
                    intent.putExtra("url",listPost.get(pos).getLinkPost());
                    MyApplication.log("URL",listPost.get(pos).getLinkPost());
                    context.startActivity(intent);
                }
            });
        }
    }

    class LoadMore extends RecyclerView.ViewHolder {

        public LoadMore(View itemView) {
            super(itemView);
        }
    }

    public void setLoadmore(boolean loadmore) {
        this.loadmore = loadmore;
        notifyItemChanged(listPost.size());
    }

    public void addItem(List<PostNews> list) {
        listPost.addAll(list);
        notifyDataSetChanged();
    }

    public void setListData(List<PostNews> list) {
        listPost.clear();
        listPost.addAll(list);
        notifyDataSetChanged();
    }
}
