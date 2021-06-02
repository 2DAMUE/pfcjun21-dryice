package com.sap.dryice;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sap.dryice.fragments_main_screen.PageFragment1;
import com.sap.dryice.fragments_main_screen.PageFragment2;
import com.sap.dryice.fragments_main_screen.PageFragment3;

import java.util.ArrayList;
import java.util.List;

public class ActivityGraphics extends AppCompatActivity {

    BottomNavigationView bnv;

    private MainVerticalViewPager viewPager;
    private MainPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        bnv = findViewById(R.id.bottom_navigation);

        bnv.setSelectedItemId(R.id.activity_graficas);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.activity_map:
                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.activity_profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.activity_graficas:
                        return true;
                }
                return false;
            }
        });

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragment1());
        list.add(new PageFragment2());
        list.add(new PageFragment3());

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(),list);

        viewPager.setAdapter(pagerAdapter);




    }


}