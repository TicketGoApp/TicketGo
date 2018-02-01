package vn.ticketgo.taipv.ticketgo.view.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import vn.ticketgo.taipv.ticketgo.model.GetInfoFB;
import vn.ticketgo.taipv.ticketgo.presenter.profilepre.ProfilePre;
import vn.ticketgo.taipv.ticketgo.view.activity.inf.profile.IProfileView;
import vn.ticketgo.taipv.ticketgo.view.fragment.homefragment.HomeMain;
import vn.ticketgo.taipv.ticketgo.view.fragment.MyOder;
import vn.ticketgo.taipv.ticketgo.view.fragment.News;
import vn.ticketgo.taipv.ticketgo.view.fragment.Profile;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,IProfileView {
    BottomNavigationView botnavi;
    TextView mTitle;
    Toolbar toolbar;
//    private ActionBar toolbar;
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    private final static String TAG_FRAGMENT2 = "TAG_FRAGMENT2";
    protected OnBackPressedListener onBackPressedListener;
    ProfilePre profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vn.ticketgo.taipv.ticketgo.R.layout.activity_home);
         toolbar =  findViewById(vn.ticketgo.taipv.ticketgo.R.id.toolbar);
         mTitle =  toolbar.findViewById(vn.ticketgo.taipv.ticketgo.R.id.toolbar_title);
         toolbar.setTitle("");
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        setSupportActionBar(toolbar);
//        Toolbar toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        final ActionBar ab = getSupportActionBar();
//        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
//        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
//        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
//        ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
//        toolbar = getSupportActionBar();
        profile=new ProfilePre(this);
        botnavi = findViewById(vn.ticketgo.taipv.ticketgo.R.id.navigation);
        botnavi.setOnNavigationItemSelectedListener(this);

//        toolbar.setTitle("Home");
        loadFragment(new HomeMain(), TAG_FRAGMENT);

    }

    private void getProfile() {
        Intent intent=getIntent();
        long id =intent.getLongExtra("id",-1);
        String name=intent.getStringExtra("name");
        String email=intent.getStringExtra("email");
        String image=intent.getStringExtra("image");
        profile.getToken(id, email, name, image);

    }

/*    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        }
    };*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case vn.ticketgo.taipv.ticketgo.R.id.navigation_shop:
                mTitle.setText("Home");
                fragment = new HomeMain();
                loadFragment(fragment, TAG_FRAGMENT);
                return true;
            case vn.ticketgo.taipv.ticketgo.R.id.navigation_gifts:
                mTitle.setText("News");
                fragment = new News();
                loadFragment(fragment, TAG_FRAGMENT2);
                return true;
            case vn.ticketgo.taipv.ticketgo.R.id.navigation_cart:
                mTitle.setText("Oder");
                fragment = new MyOder();
                loadFragment(fragment, TAG_FRAGMENT2);
                return true;
            case vn.ticketgo.taipv.ticketgo.R.id.navigation_profile:
//                toolbar.setTitle("IProfileView");
                fragment = new Profile();
                loadFragment(fragment, TAG_FRAGMENT2);
                getProfile();
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment, String TAG) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(vn.ticketgo.taipv.ticketgo.R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStack();
//        } else {
//            if (doubleBackToExitPressedOnce) {
//                super.onBackPressed();
//                return;
//            }
//
//            this.doubleBackToExitPressedOnce = true;
//            Toast.makeText(this, "Nhấn back lần nữa để thoát", Toast.LENGTH_SHORT).show();
//
//            new Handler().postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    doubleBackToExitPressedOnce=false;
//
//                }
//            }, 2000);
//        }



        if (onBackPressedListener != null) {
            onBackPressedListener.doBack();

        } else {
            super.onBackPressed();
        }
//        if(getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT)!=null){
//
//        }
//        final Home fragment = (Home) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);

//        if (fragment.allowBackPressed()) {
//            MyApplication.toast("xxx");
//            // and then you define a method allowBackPressed with the logic to allow back pressed or not
//        }else {
//            super.onBackPressed();
//
//        }
    }
    @Override
    public void onError(int code) {

    }

    @Override
    public void showLongToast(String message) {

    }

    @Override
    public void onEndSession() {

    }

    @Override
    public void passData(long id, String email, String name, String image) {

    }

    @Override
    public void onGetSuscess(GetInfoFB object) {
        Fragment fragment=Profile.newInstance(object.getFacebook_id(),object.getEmail(),object.getName(),object.getImage());
        getSupportFragmentManager().beginTransaction().replace(vn.ticketgo.taipv.ticketgo.R.id.frame_container,fragment).commit();
    }

    @Override
    protected void showConfirmExitApp() {
        super.showConfirmExitApp();
    }

    @Override
    public void onLogoutSuccess(boolean isSuccess) {

    }

    @Override
    public void showProgressBar(int type) {

    }

    @Override
    public Fragment getFragment() {
        return null;
    }

    public interface OnBackPressedListener {
        void doBack();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    protected void onDestroy() {
        onBackPressedListener = null;
        super.onDestroy();
    }
}
