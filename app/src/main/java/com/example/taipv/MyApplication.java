package com.example.taipv;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.Utils;
import com.example.taipv.ticketgo.R;

public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        Utils.init(this);
    }
    public static void log(String title,String content){
        Log.e(title,content);
    }
    public static void toast(String content){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();

    }
}
