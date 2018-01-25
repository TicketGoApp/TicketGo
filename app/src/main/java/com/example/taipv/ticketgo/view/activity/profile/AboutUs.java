package com.example.taipv.ticketgo.view.activity.profile;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.taipv.sdk.commons.Constants;
import com.example.taipv.sdk.social.AboutPage;
import com.example.taipv.ticketgo.R;


public class AboutUs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        LinearLayout linearLayout=findViewById(R.id.linea_about_us);
        View homePage=new AboutPage(this)
                .addFacebook(Constants.Facebook)
                .addYoutube(Constants.Youtube)
                .addTwitter(Constants.Twiter)
//                .addPlayStore(Constants.PlayStore)
                .create();
        linearLayout.addView(homePage);
    }
}
