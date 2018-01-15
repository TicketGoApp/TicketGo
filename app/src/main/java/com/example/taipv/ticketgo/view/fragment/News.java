package com.example.taipv.ticketgo.view.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.ToastUtils;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.view.activity.news.INewsView;

public class News extends BasicFragment {
INewsView iNewsView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final WebView webView = view.findViewById(R.id.webview);
        CustomWebClient customWebClient = new CustomWebClient();
        webView.setWebViewClient(customWebClient);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://ticketgo.vn/blog");
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
