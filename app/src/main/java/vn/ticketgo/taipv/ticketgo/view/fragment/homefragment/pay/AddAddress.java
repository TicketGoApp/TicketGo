package vn.ticketgo.taipv.ticketgo.view.fragment.homefragment.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.ticketgo.R;
import vn.ticketgo.taipv.ticketgo.adapter.AddressAdapter;
import vn.ticketgo.taipv.ticketgo.model.GetAddress;
import vn.ticketgo.taipv.ticketgo.util.SharedUtils;
import vn.ticketgo.taipv.ticketgo.view.fragment.BasicFragment;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/15/2018
 * Email: tai97nd@gmail.com
 */

public class AddAddress extends BasicFragment {
    private static final int RESULTCODE = 102;
    private static final String TAG = "AddAddress.class";
    CardView cardView;
    AddressAdapter addressAdapter;
    RecyclerView rcvDiaChi;
    String name, phone, email, city, address;
    final private static int REQUEST_CODE_INPUT = 101;

    public static AddAddress newInstance(String name, String phone, String email, String city, String address) {

        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("phone", phone);
        args.putString("email", email);
        args.putString("city", city);
        args.putString("address", address);
        AddAddress fragment = new AddAddress();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (getArguments() != null) {
            long id = bundle.getLong("id");
            email = getArguments().getString("email");
            name = getArguments().getString("name");
            phone = getArguments().getString("phone");
            city = getArguments().getString("city");
            address = getArguments().getString("address");
            Log.d("getArguments", "result: " + id);
        } else {
            Log.d("getArguments oncreate", "null ");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(vn.ticketgo.taipv.ticketgo.R.layout.add_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        event();
    }

    private void event() {
        if(SharedUtils.getInstance().getStringValue("name_address")==null||SharedUtils.getInstance().getStringValue("name_address").equals("")){
//            cardView.setVisibility(View.VISIBLE);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                MyApplication.toast("click card");
//                AlertDialog.Builder mBuilder=new AlertDialog.Builder(getActivity());
//                View mView=getLayoutInflater().inflate(R.layout.dialog_address_user,null);
//                mBuilder.setView(mView);
//                AlertDialog alertDialog=mBuilder.create();
//                alertDialog.show();.
//                getActivity().startActivityForResult(new Intent(getActivity(), DialogAddress.class));
                    Intent intent = new Intent(getContext(), DialogAddress.class);
                    startActivityForResult(intent, REQUEST_CODE_INPUT);
                }
            });
        }else {
//            cardView.setVisibility(View.GONE);
            String nameShare=SharedUtils.getInstance().getStringValue("name_address");
            String phoneShare=SharedUtils.getInstance().getStringValue("phone_address");
            String emailShare=SharedUtils.getInstance().getStringValue("email_address");
            String cityShare=SharedUtils.getInstance().getStringValue("city_address");
            String addressShare=SharedUtils.getInstance().getStringValue("address_address");
            GetAddress getAddress=new GetAddress(nameShare,phoneShare,emailShare,cityShare,addressShare);
            addressAdapter.addItem(getAddress);

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        MyApplication.log(TAG,"say ok");


            if(requestCode==REQUEST_CODE_INPUT){
                MyApplication.log(TAG,"OK");
                    if(resultCode==RESULTCODE){
                        name=data.getStringExtra("name");
                        phone=data.getStringExtra("phone");
                        email=data.getStringExtra("email");
                        city=data.getStringExtra("city");
                        address=data.getStringExtra("address");
                            GetAddress getAddress=new GetAddress(name,phone,email,city,address);
                            addressAdapter.addItem(getAddress);


                    }
            }else {
                MyApplication.log(TAG,"NOT OK");
            }



    }


    private void init(View view) {
        cardView = view.findViewById(R.id.card_view_address);
        rcvDiaChi = view.findViewById(R.id.rcv_dia_chi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvDiaChi.setHasFixedSize(true);
        rcvDiaChi.setLayoutManager(layoutManager);
        addressAdapter = new AddressAdapter();
        rcvDiaChi.setAdapter(addressAdapter);
    }
}
