package com.sap.dryice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


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
                        startActivity(new Intent(getApplicationContext(), ActivityGraphics.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.activity_profile:
                        return true;
                }
                return false;
            }
        });
    }
}