package vn.ticketgo.taipv.ticketgo.view.activity.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.ticketgo.view.activity.BaseActivity;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/15/2018
 * Email: tai97nd@gmail.com
 */

public class EventDetailActivity extends BaseActivity implements View.OnClickListener{
    Button btnBookNow;
    Bundle bundle;
    ImageView imgLogo,imgSaveTicket,imgShareTicket,imgAddTicket;
    TextView tvTitleEvent,tvTimeEvent,tvDateEvent,tvLocationEvent,tvPriceEvent;
    ShareDialog shareDialog;
    ShareLinkContent shareLinkContent;
    String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vn.ticketgo.taipv.ticketgo.R.layout.activity_detail);
        init();
        shareDialog = new ShareDialog(this);
        event();
    }

    private void event() {
        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPay();
            }

            private void goToPay() {
                Intent intent=new Intent(EventDetailActivity.this,PayEvent.class);
                    intent.putExtra("putBundle",bundle);
                    startActivity(intent);
            }
        });
    }

    private void init() {
        btnBookNow=findViewById(vn.ticketgo.taipv.ticketgo.R.id.btnBookNow);
         bundle=getIntent().getBundleExtra("MyTicketBundle");
        String name=bundle.getString("name");
        String image="https://ticketgo.vn/"+bundle.getString("image");
        String startDate=bundle.getString("startdate");
        String endDate=bundle.getString("enddate");
        String location=bundle.getString("location");
        String price=bundle.getString("price");
         url=bundle.getString("url");
        int position=bundle.getInt("position",-1);
        String subTime=startDate.substring(11,16)+" - "+endDate.substring(11,16);
        String subDate;

        if(startDate.substring(0,11).equals(endDate.substring(0,11))){
            subDate=startDate.substring(0,11);
        }else {
            subDate=startDate.substring(0,10)+" - "+endDate.substring(0,10);
        }
        imgAddTicket=findViewById(vn.ticketgo.taipv.ticketgo.R.id.img_addCalendar);
        imgShareTicket=findViewById(vn.ticketgo.taipv.ticketgo.R.id.img_share_ticket);
        imgSaveTicket=findViewById(vn.ticketgo.taipv.ticketgo.R.id.img_save_ticket);
        imgLogo=findViewById(vn.ticketgo.taipv.ticketgo.R.id.img_logo);
        tvTitleEvent=findViewById(vn.ticketgo.taipv.ticketgo.R.id.tv_titleEvent);
        tvDateEvent=findViewById(vn.ticketgo.taipv.ticketgo.R.id.tv_date_event);
        tvTimeEvent=findViewById(vn.ticketgo.taipv.ticketgo.R.id.tv_timeEvent);
        tvLocationEvent=findViewById(vn.ticketgo.taipv.ticketgo.R.id.tv_location_event);
        tvPriceEvent=findViewById(vn.ticketgo.taipv.ticketgo.R.id.tvPriceEvent);
        Glide.with(this).load(image).into(imgLogo);
        tvTitleEvent.setText(name);
        tvTimeEvent.setText(subTime);
        tvDateEvent.setText(subDate);
        tvLocationEvent.setText(location);
        tvPriceEvent.setText(price);
        MyApplication.log("intent name",name+"\n"+image+"\n"+startDate+"\n"+endDate+"\n"+location+"\n"+price+"\n"+position);
        imgAddTicket.setOnClickListener(this);
        imgShareTicket.setOnClickListener(this);
        imgSaveTicket.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case vn.ticketgo.taipv.ticketgo.R.id.img_share_ticket:
                if (shareDialog.canShow(ShareLinkContent.class)) {
                    shareLinkContent = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse(url))
                            .build();
                }
                shareDialog.show(shareLinkContent);
                break;
        }
    }
}
