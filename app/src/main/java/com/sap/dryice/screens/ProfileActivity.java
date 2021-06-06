package com.sap.dryice.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sap.dryice.R;
import com.sap.dryice.dbEntities.User;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    private TextView rpiName;
    private TextView name;
    private TextView title;
    private TextView location;
    private TextView email;

    private FirebaseAuth mAuth;

    private double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        rpiName = findViewById(R.id.textIdRpi);
        name = findViewById(R.id.textNombre);
        title = findViewById(R.id.textViewUserNameProfile);
        location = findViewById(R.id.txt_location);
        email = findViewById(R.id.textEmail);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users").child(LoginActivity.USERUID);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u = snapshot.getValue(User.class);
                rpiName.setText(u.getIdRPi());
                name.setText(u.getName());
                title.setText(u.getName());
                email.setText(u.getEmail());
                lat = u.getLatitude();
                lng = u.getLongitude();

                if (lat == 0.0 && lng == 0.0){
                    location.setText("Actualiza tu ubicación");
                } else {
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocation(lat, lng, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String cityName = addresses.get(0).getAddressLine(0);
                    location.setText(cityName);
                }

                // ImageView in your Activity
                ImageView imageView = findViewById(R.id.imageViewProfilePhoto);

                // Download directly from StorageReference using Glide
                // (See MyAppGlideModule for Loader registration)
                Glide.with(getApplicationContext())
                        .load(Uri.parse("https://firebasestorage.googleapis.com/v0/b/net4-515ff.appspot.com/o/profilepics%2F" + LoginActivity.USERUID + ".jpg?alt=media&token=dcb65d07-cace-45b4-8fb7-e38880be36ce"))
                        .placeholder(R.drawable.hombre)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade(300))
                        .circleCrop()
                        .into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void toolbarIconToGraphicsActivity(View view) {
        mAuth.signOut();
        LoginActivity.USERUID = null;
        LoginActivity.RPI_USERUID = null;
        startActivity(new Intent(this, GraphicsActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
    public void toolbarIconToLoginActivity(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}