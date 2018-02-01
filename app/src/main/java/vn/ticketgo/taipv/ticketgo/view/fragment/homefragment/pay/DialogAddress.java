package vn.ticketgo.taipv.ticketgo.view.fragment.homefragment.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;

import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.ticketgo.R;
import vn.ticketgo.taipv.ticketgo.adapter.AddressAdapter;
import vn.ticketgo.taipv.ticketgo.model.GetAddress;
import vn.ticketgo.taipv.ticketgo.presenter.HomePre.DialogAddressPre;
import vn.ticketgo.taipv.ticketgo.util.SharedUtils;
import vn.ticketgo.taipv.ticketgo.view.activity.BaseActivity;
import vn.ticketgo.taipv.ticketgo.view.activity.home.PayEvent;
import vn.ticketgo.taipv.ticketgo.view.activity.inf.Home.IDialogAddressView;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/30/2018
 * Email: tai97nd@gmail.com
 */

public class DialogAddress extends BaseActivity implements View.OnClickListener, IDialogAddressView {
    private static final int RESULTCODE = 102;
    private static final String TAG = "DialogAddress.class";
    int count=0;
    Button btnSaveAddress;
    EditText edtName, edtPhone, edtAddress, edtEmail;
    AutoCompleteTextView autoCity;
    CheckBox cbDefault, cbCompany;
    DialogAddressPre dialogAddressPre;
    String address,phone,name,email,city;
    AddressAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_address_user);
        init();
        event();
    }

    private void event() {
        btnSaveAddress.setOnClickListener(this);
    }

    private void init() {

        btnSaveAddress = findViewById(R.id.btn_save_address);
        edtName = findViewById(R.id.edt_name_address);
        edtPhone = findViewById(R.id.edt_phone_address);
        edtAddress = findViewById(R.id.edt_address_address);
        autoCity = findViewById(R.id.actv_tinh_thanh);
        edtEmail = findViewById(R.id.edt_email_address);
        cbDefault = findViewById(R.id.ck_home_default);
        cbCompany = findViewById(R.id.ck_company);
        dialogAddressPre = new DialogAddressPre(this);
        adapter=new AddressAdapter();
    }

    @Override
    public void onClick(View v) {
         name = edtName.getText().toString();
         email = edtEmail.getText().toString();
         phone = edtPhone.getText().toString();
         city = autoCity.getText().toString();
         address = edtAddress.getText().toString();
        dialogAddressPre.checkSave(name, phone, email, city, address);
    }

    @Override
    public void onSuccess() {
        adapter.addItem(new GetAddress(name,phone,email,city,address));
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Lưu thành công"+email, Toast.LENGTH_SHORT).show();
        Intent intent=getIntent();
        intent.putExtra("name",name);
        intent.putExtra("phone",phone);
        intent.putExtra("email",email);
        intent.putExtra("city",city);
        intent.putExtra("address",address);

        SharedUtils.getInstance().putStringValue("name_address",name);
        SharedUtils.getInstance().putStringValue("phone_address",phone);
        SharedUtils.getInstance().putStringValue("email_address",email);
        SharedUtils.getInstance().putStringValue("city_address",city);
        SharedUtils.getInstance().putStringValue("address_address",address);
        count=SharedUtils.getInstance().getIntValue("count")+1;

        SharedUtils.getInstance().putIntValue("count",count);
        MyApplication.log(TAG,count+"");
        setResult(RESULTCODE,intent);
        finish();

//        startActivity(new Intent(this,AddAddress.class));
//
//        new PayEvent().loadFragment(new AddAddress());
////        finish();
    }


    @Override
    public void inputEmpty() {
        Toast.makeText(this, "Bạn hãy nhập đủ các trường.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void inputMatch() {
        Toast.makeText(this, "Bạn nhập sai định dạng", Toast.LENGTH_SHORT).show();
    }
}
