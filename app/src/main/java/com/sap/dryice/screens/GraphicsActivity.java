package com.sap.dryice.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sap.dryice.R;
import com.sap.dryice.screens.graphicsActivityFragments.PageFragment1;
import com.sap.dryice.screens.graphicsActivityFragments.PageFragment2;
import com.sap.dryice.screens.graphicsActivityFragments.PageFragment3;

import java.util.ArrayList;
import java.util.List;

public class GraphicsActivity extends AppCompatActivity {

    BottomNavigationView bnv;

    private MainVerticalViewPager viewPager;
    private MainPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        System.out.println(LoginActivity.USERUID);

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

    public void toolbarIconToProfileActivity(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }


}