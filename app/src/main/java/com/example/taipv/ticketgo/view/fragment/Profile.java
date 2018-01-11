package com.example.taipv.ticketgo.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.example.taipv.MyApplication;
import com.example.taipv.sdk.callbacks.ItemClickListener;
import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.adapter.ProfileAdapter;
import com.example.taipv.ticketgo.model.GetInfoFB;
import com.example.taipv.ticketgo.model.ProfileModel;
import com.example.taipv.ticketgo.presenter.profilepre.ProfilePre;
import com.example.taipv.ticketgo.util.DownloadTask;
import com.example.taipv.ticketgo.util.PrefUtil;
import com.example.taipv.ticketgo.util.SharedUtils;
import com.example.taipv.ticketgo.view.activity.inf.profile.IProfileView;
import com.example.taipv.ticketgo.view.activity.login.LoginActivity;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;


public class Profile extends BasicFragment implements IProfileView {
    RecyclerView recyclerView;
    ProfileModel profileModel;
    ProfilePre profilePre;
    ImageView imgAvatar, imgCover;
    TextView tvUserName, tvEmail;
    List<ProfileModel> listProfile;
    String name;
    String email;
    String image;
    Integer[] icon = {R.drawable.history, R.drawable.adduser, R.drawable.share, R.drawable.mail, R.drawable.star, R.drawable.setup, R.drawable.info, R.drawable.phone, R.drawable.exit};
    String[] title = {"Lịch sử", "Mời bạn bè", "Chia sẻ", "Góp ý", "Đánh giá app TicketGo", "Cài đặt", "Giới thiệu", "Liên hệ ngay", "Đăng xuất"};

    public static Profile newInstance(long id, String email, String name, String image) {

        Bundle args = new Bundle();
        args.putLong("id", id);
        args.putString("email", email);
        args.putString("name", name);
        args.putString("image", image);
        Profile fragment = new Profile();

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
            image = getArguments().getString("image");
            Log.d("getArguments", "result: " + id);
        } else {
            Log.d("getArguments oncreate", "null ");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Bundle bundle = this.getArguments();
//        if (getArguments() != null) {
//            long id = bundle.getLong("id");
//            String email = getArguments().getString("email");
//            String name = getArguments().getString("name");
//            String image = getArguments().getString("image");
//            Log.d("getArguments", "result: " + id);
//        } else {
//            Log.d("getArguments", "null ");
//        }
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setShowHideAnimationEnabled(false);
        profilePre = new ProfilePre(this);
        initRecyclerView(view);
        profilePre.getLogout();
        initProfile(view);


        result();
    }

    private void initProfile(View view) {
        tvUserName = view.findViewById(R.id.tv_username);
        tvEmail = view.findViewById(R.id.tv_email);
        imgAvatar = view.findViewById(R.id.img_avatar);
        if(SharedUtils.getInstance().getStringValue("name")==null||SharedUtils.getInstance().getStringValue("name").equals("")){
            if (name != null && !name.equals("")) {
                tvUserName.setText(name);
                tvEmail.setText(email);
                Glide.with(getFragment()).load(image).into(imgAvatar);
                DownloadTask downloadTask=new DownloadTask(getActivity());
                downloadTask.execute(image);
                SharedUtils.getInstance().putStringValue("name", name);
                SharedUtils.getInstance().putStringValue("email", email);
                SharedUtils.getInstance().putStringValue("image", image);
                Log.d("put",SharedUtils.getInstance().getStringValue("email"));
                if(SharedUtils.getInstance().getStringValue("pathImage")==null){
                    MyApplication.log("pathImage","null");
                }else {
                    String x=SharedUtils.getInstance().getStringValue("pathImage");
                    MyApplication.log("pathImage","OK");
                }
            } else {
                tvUserName.setText("Nuller");
                tvEmail.setText("Nuller");
            }
        }else {
            Log.d("put",""+SharedUtils.getInstance().getStringValue("email"));

        }

    }

    private void result() {

    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.rcv_profile);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getFragment().getContext()));
        listProfile = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            profileModel = new ProfileModel(title[i], icon[i]);
            listProfile.add(profileModel);
        }


        ProfileAdapter profileAdapter = new ProfileAdapter(listProfile, getActivity());
        profileAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(profileAdapter);
        profileAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object object) {
                switch (position) {
                    case 8:

                        LoginManager.getInstance().logOut();
                        new PrefUtil(getActivity()).clearToken();
                        SharedPreferences settings = getActivity().getSharedPreferences("mydata", Context.MODE_PRIVATE);
                        settings.edit().clear().commit();
                        Intent intent = new Intent(Utils.getApp().getApplicationContext(), LoginActivity.class);
                        intent.putExtra("finish", true); // if you are checking for this in your other Activities
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        if (SharedUtils.getInstance().getStringValue("name") == null || SharedUtils.getInstance().getStringValue("name").equals("")) {
//            tvUserName.setText(name);
//            tvEmail.setText(email);
//            Glide.with(getFragment()).load(image).into(imgAvatar);
//            MyApplication.log("share","null");
        }else {
            tvUserName.setText(SharedUtils.getInstance().getStringValue("name"));
            if(SharedUtils.getInstance().getStringValue("email")==null){
                Log.d("email profile","null");
            }
            tvEmail.setText(SharedUtils.getInstance().getStringValue("email"));
            String path=SharedUtils.getInstance().getStringValue("pathImage");
            imgAvatar.setImageDrawable(Drawable.createFromPath(path));

            MyApplication.log("share",SharedUtils.getInstance().getStringValue("name")+SharedUtils.getInstance().getStringValue("email")+SharedUtils.getInstance().getStringValue("pathImage"));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        if (name != null & email != null & image != null) {

            Log.d("profile", "onPause:email "+email);
        }

    }


    @Override
    public void passData(long id, String email, String name, String image) {

    }

    @Override
    public void onGetSuscess(GetInfoFB object) {
        MyApplication.log("image", object.getImage());


    }


    @Override
    public void onLogoutSuccess(boolean isSuccess) {

    }

    @Override
    public void showProgressBar(int type) {
        String message;

        switch (type) {
            case 1:
                message = getString(R.string.doing_load_data);
                break;
            case 3:
                message = getString(R.string.doing_mbr_delete_hr);
                break;
            default:
                message = getString(R.string.doing_do);
                break;
        }
//        showProgressBar(false, false, message);
    }

    @Override
    public Fragment getFragment() {
        return this;
    }
}
