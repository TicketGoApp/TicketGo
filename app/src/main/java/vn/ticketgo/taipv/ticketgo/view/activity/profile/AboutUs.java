package vn.ticketgo.taipv.ticketgo.view.activity.profile;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import vn.ticketgo.taipv.sdk.commons.Constants;
import vn.ticketgo.taipv.sdk.social.AboutPage;


public class AboutUs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vn.ticketgo.taipv.ticketgo.R.layout.activity_about_us);
        LinearLayout linearLayout=findViewById(vn.ticketgo.taipv.ticketgo.R.id.linea_about_us);
        View homePage=new AboutPage(this)
                .addFacebook(Constants.Facebook)
                .addYoutube(Constants.Youtube)
                .addTwitter(Constants.Twiter)
//                .addPlayStore(Constants.PlayStore)
                .create();
        linearLayout.addView(homePage);
    }
}
