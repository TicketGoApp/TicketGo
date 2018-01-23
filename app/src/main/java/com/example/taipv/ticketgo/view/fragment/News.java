package com.example.taipv.ticketgo.view.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.ToastUtils;
import com.example.taipv.MyApplication;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.adapter.NewsAdapter;
import com.example.taipv.ticketgo.model.PostNews;
import com.example.taipv.ticketgo.presenter.news.NewsPresenter;
import com.example.taipv.ticketgo.util.EndlessRecyclerViewScrollListener;
import com.example.taipv.ticketgo.view.activity.news.INewsView;

import java.util.List;

public class News extends BasicFragment implements INewsView {
    EndlessRecyclerViewScrollListener endless;
    NewsPresenter presenter;
    NewsAdapter adapter;
    int page=1;
    RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=new NewsPresenter(this);
        presenter.getPostNews(1);
        adapter=new NewsAdapter(getContext());
        initRecyclerView(view);
//        final WebView webView = view.findViewById(R.id.webview);
//        CustomWebClient customWebClient = new CustomWebClient();
//        webView.setWebViewClient(customWebClient);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setJavaScriptEnabled(true);
//        webView.loadUrl("https://ticketgo.vn/blog");
    }

    private void initRecyclerView(View view) {
        recyclerView=view.findViewById(R.id.recycler_view_news);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getFragment().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        endless = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(page);
            }
        };
        recyclerView.addOnScrollListener(endless);
    }

    private void loadNextDataFromApi(final int page) {
        adapter.setLoadmore(true);
//        showProgressBar(1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getPostNews(page);
                closeProgressBar();


            }
        },1000);
    }

    @Override
    public void showProgressBar(int type) {
        String message;

        switch (type) {
            case 1:
                message = getString(R.string.doing_load_data);
                break;
            case 3:
                message = getString(R.string.doing_mbr_delete_hr);
                break;
            default:
                message = getString(R.string.doing_do);
                break;
        }
        showProgressBar(false, false, message);
    }

    @Override
    public void onSuccess(List<PostNews> list) {
        MyApplication.log("tittit",list.get(0).getTitle());
        adapter.addItem(list);
        adapter.setLoadmore(false);
//        closeProgressBar();
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    class CustomWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl("https:ticketgo.vn/blog");
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:var x=document.getElementById(\"header\").setAttribute(\"style\",\"display:none;\");var y=document.getElementsByClassName('footer')[0].style.display='none';var a=document.getElementsByClassName('col-md-4')[3].style.display='none';var c=document.getElementsByClassName('col-md-4')[1].style.display='none';var b=document.getElementsByClassName('col-md-4')[2].style.display='none';var d=document.getElementsByClassName('col-md-4')[0].style.display='none';");
            closeProgressBar();
            ToastUtils.setBgColor(65536);
            ToastUtils.showShort("End");
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            ToastUtils.setBgColor(16776961);
            ToastUtils.showShort("Start");
            showProgressBar(false,false,"Đang tải dữ liệu");

//            iNewsView.showProgressBar(1);


        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
