package com.example.taipv.ticketgo.view.activity.CodeQR;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taipv.sdk.commons.Constants;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.util.PrefUtil;
import com.example.taipv.ticketgo.view.activity.login.LoginActivity;
import com.facebook.login.LoginManager;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.List;

import butterknife.ButterKnife;
import info.androidhive.barcode.BarcodeReader;

public class QRCodeActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener{
    public static String TAG = "xxx";
    TextView textView;
    BarcodeReader barcodeReader;
    Button logout;
    PrefUtil prefUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String token=intent.getStringExtra("token");
        Toast.makeText(this, "" + name+token, Toast.LENGTH_SHORT).show();
//        Utils.getApp();
        prefUtil=new PrefUtil(this);
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);
        logout=findViewById(R.id.btn_logout_fb);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                Intent intent1=new Intent(QRCodeActivity.this, LoginActivity.class);
                prefUtil.clearToken();
                startActivity(intent1);
            }
        });
//        QRCodeWriter writer = new QRCodeWriter();
//        try {
//            BitMatrix bitMatrix = writer.encode("http://download.union.ucweb.com/trackv2/UCBrowser_V11.0.0.1016_android_pf139_(en-us)_inumpatch_(Build171201201643).apk?analyze_params=26623751937474163315a42240758fef&pub=chenxiang@ajaykumarbrowser&subpub=", BarcodeFormat.QR_CODE, 512, 512);
//            int width = bitMatrix.getWidth();
//            int height = bitMatrix.getHeight();
//            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//            for (int x = 0; x < width; x++) {
//                for (int y = 0; y < height; y++) {
//                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
//                }
//            }
//            ((ImageView) findViewById(R.id.imgQR)).setImageBitmap(bmp);
//
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }

    }


    @Override
    public void onScanned(Barcode barcode) {
        barcodeReader.playBeep();
        Intent intent = new Intent(QRCodeActivity.this, TicketResultActivity.class);
            intent.putExtra(Constants.QRCODE,barcode.displayValue);
            startActivity(intent);
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getApplicationContext(), "Camera permission denied!", Toast.LENGTH_LONG).show();
    }
//go to HomeScreen of device
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
