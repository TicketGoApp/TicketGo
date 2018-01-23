package com.example.taipv.ticketgo.view.activity.news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.ToastUtils;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.view.activity.BaseActivity;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/19/2018
 * Email: tai97nd@gmail.com
 */

public class WebViewNews extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_news);

        initView();
    }

    private void initView() {
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        Log.d("", "initView: "+url);
                final WebView webView = findViewById(R.id.webview);
        CustomWebClient customWebClient = new CustomWebClient();
        webView.setWebViewClient(customWebClient);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
    class CustomWebClient extends WebViewClient {


        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:var x=document.getElementById(\"header\").setAttribute(\"style\",\"display:none;\");var y=document.getElementsByClassName('footer')[0].style.display='none';var a=document.getElementsByClassName('col-md-4')[3].style.display='none';var c=document.getElementsByClassName('col-md-4')[1].style.display='none';var b=document.getElementsByClassName('col-md-4')[2].style.display='none';var d=document.getElementsByClassName('col-md-4')[0].style.display='none';");
            closeProgressBar();
            ToastUtils.setBgColor(65536);
//            ToastUtils.showShort("End");
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            ToastUtils.setBgColor(16776961);
//            ToastUtils.showShort("Start");
//            showProgressBar(false,false,"Đang tải dữ liệu");

//            iNewsView.showProgressBar(1);


        }
    }
}
