package com.example.taipv.ticketgo.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.taipv.ticketgo.R;
import com.example.taipv.ticketgo.view.fragment.Home;
import com.example.taipv.ticketgo.view.fragment.MyOder;
import com.example.taipv.ticketgo.view.fragment.News;
import com.example.taipv.ticketgo.view.fragment.Profile;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView botnavi;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = getSupportActionBar();

        botnavi= findViewById(R.id.navigation);
        botnavi.setOnNavigationItemSelectedListener(this);

        toolbar.setTitle("Shop");
        loadFragment(new Home());
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
            case R.id.navigation_shop:
                toolbar.setTitle("Home");
                fragment = new Home();
                loadFragment(fragment);
                return true;
            case R.id.navigation_gifts:
                toolbar.setTitle("News");
                fragment = new News();
                loadFragment(fragment);
                return true;
            case R.id.navigation_cart:
                toolbar.setTitle("Oder");
                fragment = new MyOder();
                loadFragment(fragment);
                return true;
            case R.id.navigation_profile:
                toolbar.setTitle("Profile");
                fragment = new Profile();
                loadFragment(fragment);
                return true;
        }
        return false;
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
