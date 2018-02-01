package vn.ticketgo.taipv;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.Utils;

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
