package com.example.taipv.ticketgo.view.activity.CodeQR;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.example.taipv.sdk.commons.Constants;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.model.TicketQR;
import com.example.taipv.ticketgo.network.ApiFactory;
import com.example.taipv.ticketgo.network.IGetTicketAPI;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TicketResultActivity extends AppCompatActivity {
    private static final String TAG = "xxx";
//    @BindView(R.id.layout_ticket)
    TicketView ticketView;
    Retrofit retrofit;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.txt_error)
    TextView txtError;
//    @BindView(R.id.director)
    TextView txtDirector;
//    @BindView(R.id.name)
    TextView txtName;
//    @BindView(R.id.poster)
    ImageView imgPoster;
//    @BindView(R.id.duration)
    TextView txtDuration;
//    @BindView(R.id.genre)
    TextView txtGenre;
//    @BindView(R.id.rating)
    TextView txtRating;
//    @BindView(R.id.price)
    TextView txtPrice;
//    @BindView(R.id.btn_buy)
    Button btnBuy;
//    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    String barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_result);
        ButterKnife.bind(this);
        Utils.init(getApplication());
        init();
        getQRCode();
        txtName=findViewById(R.id.name);
        ticketView=findViewById(R.id.layout_ticket);
        progressBar=findViewById(R.id.progressBar);
        imgPoster=findViewById(R.id.poster);
        txtDirector=findViewById(R.id.director);
        txtDuration=findViewById(R.id.duration);
        txtGenre=findViewById(R.id.genre);
        txtRating=findViewById(R.id.rating);
        txtPrice=findViewById(R.id.price);
        txtError=findViewById(R.id.txt_error);
        btnBuy=findViewById(R.id.btn_buy);
        searchCodeBar(barcode);
    }

    private void searchCodeBar(String barcode) {
        IGetTicketAPI iGetTicketAPI= ApiFactory.getAPI(IGetTicketAPI.class);

        Call<TicketQR> call = iGetTicketAPI.getTicket(barcode);
        call.enqueue(new Callback<TicketQR>() {
            @Override
            public void onResponse(Call<TicketQR> call, Response<TicketQR> response) {
                if (response.isSuccessful()) {
                    TicketQR ticketQR=response.body();
                    txtName.setText(ticketQR.getName());
                    Log.d(TAG, "onResponse: "+response.body().getName());
//                    String name=response.body().getName();
//                    txtName.setText(name);
                    txtDirector.setText(ticketQR.getDirector());
                    txtDuration.setText(ticketQR.getDuration());
                    txtGenre.setText(ticketQR.getGenre());
                    txtRating.setText("" + ticketQR.getRating());
                    txtPrice.setText(ticketQR.getPrice());
                    btnBuy.setText("BUY NOW");
                    Glide.with(getApplicationContext()).load(ticketQR.getPoster()).into(imgPoster);
                    Log.d(TAG, "onResponse: "+response.body().getPoster());
                    ticketView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TicketQR> call, Throwable t) {
                showNoTicket();
            }
        });

    }

    private void getQRCode() {
        barcode = getIntent().getStringExtra("code");
        if (TextUtils.isEmpty(barcode)) {
            Toast.makeText(this, "Barcode is empty", Toast.LENGTH_SHORT).show();
            ToastUtils.showShort("Barcode is empty");
            finish();
        }

    }

    private void init() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void showNoTicket() {
        txtError.setVisibility(View.VISIBLE);
        ticketView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }
}
