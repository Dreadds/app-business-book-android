package com.edu.upc.businessbook.viewcontrollers.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.viewcontrollers.fragments.HomeFragment;
import com.edu.upc.businessbook.viewcontrollers.fragments.ProfileFragment;
import com.edu.upc.businessbook.viewcontrollers.fragments.ReportsFragment;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            return navigateTo(item);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private Fragment getFragmentFor(MenuItem item){
        switch (item.getItemId()) {
            case R.id.navigation_home: return new HomeFragment();
            case R.id.navigation_reports: return new ReportsFragment();
            case R.id.navigation_profile: return new ProfileFragment();
            default: return  new HomeFragment();
        }
    }

    private boolean navigateTo(MenuItem item){
        return getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, getFragmentFor(item))
                .commit()>0;
    }

}
