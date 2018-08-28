package com.example.aleksadencic.bebysitterapplication.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.aleksadencic.bebysitterapplication.R;
import com.example.aleksadencic.bebysitterapplication.activities.fragments.HomeFragment;
import com.example.aleksadencic.bebysitterapplication.activities.fragments.ProfileFragment;
import com.example.aleksadencic.bebysitterapplication.activities.fragments.SearchFragment;
import com.example.aleksadencic.bebysitterapplication.domain.User;

public class AccountActivity extends AppCompatActivity {

    private FrameLayout mainFrame;
    private BottomNavigationView navBarBottom;
    private SearchFragment searchFragment;
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    User loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mainFrame = (FrameLayout) findViewById(R.id.mainFrame);
        navBarBottom = (BottomNavigationView) findViewById(R.id.navBarBottom);

        loggedUser = (User) getIntent().getParcelableExtra("user");
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", loggedUser);

        homeFragment = new HomeFragment(false);
        homeFragment.setArguments(bundle);

        searchFragment = new SearchFragment();
        searchFragment.setArguments(bundle);

        profileFragment = new ProfileFragment();
        profileFragment.setArguments(bundle);

        setFragment(searchFragment);

        navBarBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navItemSearch:
                        navBarBottom.setItemBackgroundResource(R.color.colorBlue);
                        setFragment(searchFragment);
                        return true;
                    case R.id.navItemHome:
                        navBarBottom.setItemBackgroundResource(R.color.colorGreen);
                        setFragment(homeFragment);
                        return true;
                    case R.id.navItemProfile:
                        navBarBottom.setItemBackgroundResource(R.color.colorRed);
                        setFragment(profileFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }



    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();
    }
}
