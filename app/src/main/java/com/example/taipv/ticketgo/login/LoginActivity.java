package com.example.taipv.ticketgo.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.taipv.ticketgo.R;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
//    private Bundle getFacebookData(JSONObject object) {
//        Bundle bundle = new Bundle();
//
//        try {
//            String id = object.getString("id");
//            URL profile_pic;
//            try {
//                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
//                Log.i("profile_pic", profile_pic + "");
//                bundle.putString("profile_pic", profile_pic.toString());
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//                return null;
//            }
//
//            bundle.putString("idFacebook", id);
//            if (object.has("first_name"))
//                bundle.putString("first_name", object.getString("first_name"));
//            if (object.has("last_name"))
//                bundle.putString("last_name", object.getString("last_name"));
//            if (object.has("email"))
//                bundle.putString("email", object.getString("email"));
//            if (object.has("gender"))
//                bundle.putString("gender", object.getString("gender"));
//
//
//            prefUtil.saveFacebookUserInfo(object.getString("first_name"),
//                    object.getString("last_name"),object.getString("email"),
//                    object.getString("gender"), profile_pic.toString());
//
//        } catch (Exception e) {
//            Log.d(TAG, "BUNDLE Exception : "+e.toString());
//        }

    }
