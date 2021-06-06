package com.sap.dryice.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sap.dryice.R;
import com.sap.dryice.dbAccess.CollectRPiRTData;
import com.sap.dryice.dbAccess.CollectRPiUsers;
import com.sap.dryice.dbAccess.CollectUsers;
import com.sap.dryice.dbEntities.RPiUser;
import com.sap.dryice.dbEntities.RTData;
import com.sap.dryice.dbEntities.User;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, CollectRPiUsers.Comunication, CollectUsers.Comunication {

    //probar android:launchMode="singleTask"

    //Barra de abajo
    private BottomNavigationView bnv;
    //Para el Mapa
    private GoogleMap mMap;
    private final OnMapReadyCallback omrc = this;
    private LocationManager lm;
    private static final int REQUEST_CODE = 101;
    private FirebaseUser u;
    private FloatingActionButton find_location;
    //context
    private Context contexto = this;
    private CollectUsers.Comunication collectUser = this;
    private CollectRPiUsers.Comunication collectRPiUsers = this;
    private User uBueno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Para llamar al metodo onMapReady
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(omrc);

        u = FirebaseAuth.getInstance().getCurrentUser();
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        checkPermissionClient();

        find_location = findViewById(R.id.fab);
        find_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerLocalizacion();
            }
        });

        CollectUsers.takeData(collectUser);

        bnv = findViewById(R.id.bottom_navigation);
        bnv.setSelectedItemId(R.id.bottom_navigation);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.activity_map:
                        return true;
                    case R.id.activity_graficas:
                        startActivity(new Intent(getApplicationContext(), GraphicsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private void checkPermissionClient() {
        //Aqui prguntaremos si quiere dar permiso de ubicacion a la aplicacion en caso de que no tenga ira al metodo onRequestPermissionsResult(),
        //si ya tiene los permiso ira directamente a obtenerLocalizacion()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //comprobamos que le dan permiso a la apliocacion para que coja la ubicacio
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerLocalizacion();
            }
        }
    }

    private void obtenerLocalizacion() {
        //Aqui revisamos si estan los permisos
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //Este toast es para que muestre el mensaje de que busca una ubicacion
        Toast toast = Toast.makeText(contexto, "Buscando Ubicacion.....", Toast.LENGTH_SHORT);
        toast.show();
        //Aqui ponemos un oyesnte a la ubicacion y en caso de que sea nulo le decimos que ponga la ultima encontrada
        //y si no que compruebe si la latitud y longitud no son nulas
        //en caso de que sean busca otra vez y si las encuentra que lo marque en el mapa
        LocationListener oyente_localizaciones = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                findLocation(location);
                lm.removeUpdates(this);
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                Toast.makeText(MapsActivity.this, "Activa el GPS", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
            }
        };
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, oyente_localizaciones);
    }

    private void findLocation(Location location) {
        if (location != null) {
            double latitud = location.getLatitude();
            double longitud = location.getLongitude();
            String username = u.getUid();
            CollectUsers.updateLocation(latitud, longitud, username);
            CollectUsers.takeData(collectUser);
        }
    }

    private void drawLocation(double latitud, double longitud, String nombre) {

        if (latitud == 0.0 && longitud == 0.0) {
            Toast.makeText(getApplicationContext(), "Pulsa el botón para recargar la ubicación", Toast.LENGTH_LONG).show();
        } else {
            //Aqui se pasan las coordenadas
            LatLng ubi = new LatLng(latitud, longitud);
            //Aqui dirigimos la camara a la ubicacion
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubi, 13));
            //Esto es el marcador con el titulo de ubicacion
            mMap.addMarker(new MarkerOptions().position(ubi).title(nombre).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }
    }

    private String changeName() {
        String nombre_user = u.getUid();
        String username;
        String[] nombreCompleto = nombre_user.split(" ");
        if (nombreCompleto.length >= 2) {
            username = nombreCompleto[0] + " " + nombreCompleto[1];
        } else {
            username = nombreCompleto[0];
        }
        return username;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void sendDataUsers(List<User> users) {
        uBueno = new User();
        mMap.clear();
        for (User user : users) {
            if (user.getUserId().equals(LoginActivity.USERUID)) {
                uBueno = user;
                CollectRPiUsers.takeData(collectRPiUsers);
            }
        }

    }

    @Override
    public void sendDataRPiUsers(List<RPiUser> rPiUsersList) {

        for (RPiUser e : rPiUsersList) {
            drawLocationEvents(e.getLatitude(), e.getLongitude(), e.getIdRPi());
        }

        drawLocation(uBueno.getLatitude(), uBueno.getLongitude(), uBueno.getIdRPi());
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                AlertDialog builder = new AlertDialog.Builder(MapsActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(getApplicationContext());
                View view = factory.inflate(R.layout.activity_map_information,
                        null);

                ImageView imgInfo = view.findViewById(R.id.imageViewProfilePhoto);
                TextView titInfo = view.findViewById(R.id.textViewUserNameProfile);
                TextView co2Info = view.findViewById(R.id.text_view_co2);
                TextView tempInfo = view.findViewById(R.id.text_view_temp);
                TextView humInfo = view.findViewById(R.id.text_view_hum);

                String getData = marker.getTitle();

                Glide.with(getApplicationContext())
                        .load(Uri.parse("https://firebasestorage.googleapis.com/v0/b/net4-515ff.appspot.com/o/eventspics%2F" + getData + ".jpg?alt=media&token=26419bcf-488c-4c50-802a-8088e2c092b1"))
                        .placeholder(R.drawable.hombre)
                        .centerCrop()
                        //.transition(DrawableTransitionOptions.withCrossFade(300))
                        //.circleCrop()
                        .into(imgInfo);

                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(LoginActivity.USERUID);
                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User u = snapshot.getValue(User.class);
                        titInfo.setText(u.getIdRPi());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("RTData").child(getData);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        RTData e = snapshot.getValue(RTData.class);
                        co2Info.setText(String.valueOf((int) e.getCO2()));
                        tempInfo.setText(String.valueOf((int) e.getTemperature()));
                        humInfo.setText(String.valueOf((int) e.getRelHumedity()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                builder.setView(view);

                builder.show();
                return true;
            }

        });
    }

    public void toolbarIconToProfileActivity(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

    private void drawLocationEvents(double latitud, double longitud, String name) {
        //Aqui se pasan las coordenadas
        LatLng ubi = new LatLng(latitud, longitud);
        //Aqui dirigimos la camara a la ubicacion
        mMap.animateCamera(CameraUpdateFactory.newLatLng(ubi));
        //Esto es el marcador con el titulo de ubicacion

        mMap.addMarker(new MarkerOptions().position(ubi)
                .title(name)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

    }
}