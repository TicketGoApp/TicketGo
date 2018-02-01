package vn.ticketgo.taipv.ticketgo.view.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.sdk.callbacks.ItemClickListener;
import vn.ticketgo.taipv.sdk.commons.Constants;
import vn.ticketgo.taipv.ticketgo.adapter.ProfileAdapter;
import vn.ticketgo.taipv.ticketgo.model.GetInfoFB;
import vn.ticketgo.taipv.ticketgo.model.ProfileModel;
import vn.ticketgo.taipv.ticketgo.presenter.profilepre.ProfilePre;
import vn.ticketgo.taipv.ticketgo.util.DownloadTask;
import vn.ticketgo.taipv.ticketgo.util.PrefUtil;
import vn.ticketgo.taipv.ticketgo.util.SharedUtils;
import vn.ticketgo.taipv.ticketgo.view.activity.inf.profile.IProfileView;
import vn.ticketgo.taipv.ticketgo.view.activity.login.LoginActivity;
import vn.ticketgo.taipv.ticketgo.view.activity.profile.AboutUs;
import vn.ticketgo.taipv.ticketgo.view.activity.profile.ContactUs;
import vn.ticketgo.taipv.ticketgo.view.activity.profile.EventSaved;

import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.List;


public class Profile extends BasicFragment implements IProfileView {
    private static final String TAG = Utils.getApp().getClass().getSimpleName();
    RecyclerView recyclerView;
    ProfileModel profileModel;
    ProfilePre profilePre;
    ImageView imgAvatar, imgCover;
    TextView tvUserName, tvEmail;
    List<ProfileModel> listProfile;
    String name;
    String email;
    String image;
    Integer[] icon = {vn.ticketgo.taipv.ticketgo.R.drawable.history, vn.ticketgo.taipv.ticketgo.R.drawable.download, vn.ticketgo.taipv.ticketgo.R.drawable.share, vn.ticketgo.taipv.ticketgo.R.drawable.mail, vn.ticketgo.taipv.ticketgo.R.drawable.star, vn.ticketgo.taipv.ticketgo.R.drawable.setup, vn.ticketgo.taipv.ticketgo.R.drawable.info, vn.ticketgo.taipv.ticketgo.R.drawable.phone, vn.ticketgo.taipv.ticketgo.R.drawable.exit};
    String[] title = {"Lịch sử", "Sự kiện đã lưu", "Chia sẻ", "Góp ý", "Đánh giá app TicketGo", "Cài đặt", "Giới thiệu", "Liên hệ ngay", "Đăng xuất"};
    ShareDialog shareDialog;
    ShareLinkContent shareLinkContent;


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
        View view = inflater.inflate(vn.ticketgo.taipv.ticketgo.R.layout.fragment_profile, container, false);
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
//        profilePre.getContactUs();
        initProfile(view);


        result();
    }

    private void initProfile(View view) {
        tvUserName = view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.tv_username);
        tvEmail = view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.tv_email);
        imgAvatar = view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.img_avatar);
        if (SharedUtils.getInstance().getStringValue("name") == null || SharedUtils.getInstance().getStringValue("name").equals("")) {
            if (name != null && !name.equals("")) {
                tvUserName.setText(name);
                tvEmail.setText(email);
                Log.d("email", "initProfile: " + email);
                Glide.with(getFragment()).load(image).into(imgAvatar);
                DownloadTask downloadTask = new DownloadTask(getActivity());
                downloadTask.execute(image);
                SharedUtils.getInstance().putStringValue("name", name);
                SharedUtils.getInstance().putStringValue("emailFB", email);
                SharedUtils.getInstance().putStringValue("image", image);
//                Log.d("put",SharedUtils.getInstance().getStringValue("email"));
                if (SharedUtils.getInstance().getStringValue("pathImage") == null) {
                    MyApplication.log("pathImage", "null");
                } else {
                    String x = SharedUtils.getInstance().getStringValue("pathImage");
                    MyApplication.log("pathImage", "OK");
                }
            } else {
                tvUserName.setText("Nuller");
                tvEmail.setText("Nuller");
            }
        } else {
            Log.d("put", "" + SharedUtils.getInstance().getStringValue("email"));

        }
        shareDialog = new ShareDialog(getActivity());

    }

    private void result() {

    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.rcv_profile);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getFragment().getContext()));
        listProfile = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            profileModel = new ProfileModel(title[i], icon[i]);
            listProfile.add(profileModel);
            if(listProfile.size()>0){
                MyApplication.log(TAG,listProfile.size()+"");

            }
        }


        ProfileAdapter profileAdapter = new ProfileAdapter(listProfile, getActivity());
        profileAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(profileAdapter);
        profileAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object object) {
                switch (position) {
                    case 1:
                        startActivity(new Intent(getActivity(),EventSaved.class));
                        break;
                    case 2:
                        if (shareDialog.canShow(ShareLinkContent.class)) {
                            shareLinkContent = new ShareLinkContent.Builder()
                                    .setContentUrl(Uri.parse(Constants.LinkAPP))
                                    .build();
                        }
                        shareDialog.show(shareLinkContent);
                        break;
                    case 3:
//                        Intent i = new Intent(Intent.ACTION_SEND);
//                        i.setType("message/rfc822");
//                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{Constants.Email});
//                        i.putExtra(Intent.EXTRA_SUBJECT, "Test App");
//                        i.putExtra(Intent.EXTRA_TEXT   , "Test App");
//                        try {
//                            startActivity(Intent.createChooser(i, "Send mail..."));
//                        } catch (android.content.ActivityNotFoundException ex) {
//                            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//                        }.0
                        Intent i = new Intent(Intent.ACTION_SEND);
                        String[] recipients = {Constants.Email};
                        i.putExtra(Intent.EXTRA_EMAIL, recipients);
                        i.putExtra(Intent.EXTRA_SUBJECT, "Phản hồi TicketGo App");
                        i.putExtra(Intent.EXTRA_TEXT, "Nội dung");
                        i.putExtra(Intent.EXTRA_CC, "mailcc@gmail.com");
                        i.setType("text/html");
                        i.setPackage("com.google.android.gm");
                        startActivity(Intent.createChooser(i, "Send mail"));
                        break;
                    case 4:
                        Uri uri = Uri.parse("market://details?id=" + Constants.PlayStore);
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + Constants.PlayStore)));
                        }
                        break;
                    case 6:
                        Intent intentabout = new Intent(getActivity(), AboutUs.class);
                        getActivity().startActivity(intentabout);
                        break;
                    case 7:
                        Intent intent1 = new Intent(Utils.getApp().getApplicationContext(), ContactUs.class);
                        getActivity().startActivity(intent1);
                        break;

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
        } else {
            tvUserName.setText(SharedUtils.getInstance().getStringValue("name"));
            if (SharedUtils.getInstance().getStringValue("emailFB") == null) {
                Log.d("email profile", "null");
            }
            tvEmail.setText(SharedUtils.getInstance().getStringValue("emailFB"));
            String path = SharedUtils.getInstance().getStringValue("pathImage");
            imgAvatar.setImageDrawable(Drawable.createFromPath(path));

            MyApplication.log("share", SharedUtils.getInstance().getStringValue("name") + SharedUtils.getInstance().getStringValue("email") + SharedUtils.getInstance().getStringValue("pathImage"));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        if (name != null & email != null & image != null) {

            Log.d("profile", "onPause:email " + email);
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
                message = getString(vn.ticketgo.taipv.ticketgo.R.string.doing_load_data);
                break;
            case 3:
                message = getString(vn.ticketgo.taipv.ticketgo.R.string.doing_mbr_delete_hr);
                break;
            default:
                message = getString(vn.ticketgo.taipv.ticketgo.R.string.doing_do);
                break;
        }
//        showProgressBar(false, false, message);
    }

    @Override
    public Fragment getFragment() {
        return this;
    }
}
