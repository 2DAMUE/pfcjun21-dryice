package com.sap.dryice.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sap.dryice.R;
import com.sap.dryice.dbAccess.CollectRPiUsers;
import com.sap.dryice.dbAccess.CollectUsers;
import com.sap.dryice.dbEntities.RPiUser;
import com.sap.dryice.dbEntities.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class RaspberryRegisterActivity extends AppCompatActivity implements CollectRPiUsers.Comunication {

    private TextInputLayout idRPi;
    private TextInputLayout pwdRPi;
    private Button btnRegister;

    private LocationManager lm;
    private static final int REQUEST_CODE = 101;

    private double latitude;
    private double longitude;

    private FirebaseAuth firebaseAuth;

    private Uri imageUri;

    private String name, email, pwd, profileImageUrl;
    private String strIdRPi;
    private String strPwdRPi;
    private String pwdRPiHasheada;

    List<RPiUser> rPiUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raspberry_register);

        idRPi = findViewById(R.id.textInputIdRPiRegister);
        pwdRPi = findViewById(R.id.textInputPwdRPiRegister);
        btnRegister = findViewById(R.id.btnRegisterEnd);

        firebaseAuth = FirebaseAuth.getInstance();
        getIntentData();
        continueEndRegistration();

    }

    private void getIntentData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("Username");
        email = intent.getStringExtra("Email");
        pwd = intent.getStringExtra("Password");
        imageUri = intent.getParcelableExtra("ImgUri");
    }

    private void continueEndRegistration() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeRegister();
            }
        });
    }

    private void completeRegister() {
        strIdRPi = idRPi.getEditText().getText().toString();
        strPwdRPi = pwdRPi.getEditText().getText().toString();

        if (TextUtils.isEmpty(strIdRPi)) {
            idRPi.setError("Enter your email");
            return;
        } else if (TextUtils.isEmpty(strPwdRPi)) {
            pwdRPi.setError("Enter your password");
            return;
        } else {
            CollectRPiUsers.takeData(this::sendDataRPiUsers);
        }
    }

    @Override
    public void sendDataRPiUsers(List<RPiUser> idsRPis) {
        rPiUsers = idsRPis;

        boolean correct = false;
        pwdRPiHasheada = hashPwd();

        for (RPiUser rPiUser : rPiUsers) {
            if (rPiUser.getIdRPi().equals(strIdRPi) && rPiUser.getPwd().equals(pwdRPiHasheada)) {
                correct = true;
                lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                checkPermissionClient();
                obtenerLocalizacion();
            }
        }

        if (!correct) {
            Toast.makeText(RaspberryRegisterActivity.this, "El id y la contraseña no son correctos", Toast.LENGTH_LONG).show();
        }
    }

    private String hashPwd() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hash = md.digest(strPwdRPi.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0"
                            + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
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

    private void obtenerLocalizacion() {
        //Aqui revisamos si estan los permisos
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //Este toast es para que muestre el mensaje de que busca una ubicacion
        Toast toast = Toast.makeText(RaspberryRegisterActivity.this, "Buscando Ubicacion...", Toast.LENGTH_SHORT);
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
                Toast.makeText(RaspberryRegisterActivity.this, "Activa el GPS", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
            }
        };
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, oyente_localizaciones);
    }

    private void findLocation(Location location) {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();


            firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        saveUserInformation();

                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String userUid = firebaseUser.getUid();
                        LoginActivity.USERUID = userUid;
                        uploadImageToFirebaseStorage();
                        User user = new User(userUid, name, email, strIdRPi, latitude, longitude);
                        saveDataInDatabase(user);

                        Toast.makeText(getApplicationContext(), "Registrado correctamente. Inicie sesión!", Toast.LENGTH_LONG).show();
                        Intent accessIntent = new Intent(getApplicationContext(), GraphicsActivity.class);
                        accessIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        accessIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(accessIntent);
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getApplicationContext(), "Este usuario ya está en uso", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Registro fallido", Toast.LENGTH_LONG).show();
                            Log.d("ERROREG", task.getException().toString());
                        }
                    }
                }
            });
        }
    }

    private void saveDataInDatabase(User user) {
        CollectUsers.saveFirebase(user);
        RPiUser rPiUser = new RPiUser(strIdRPi, pwdRPiHasheada, user.getUserId(), latitude, longitude);
        CollectRPiUsers.saveFirebase(rPiUser);
    }

    private void saveUserInformation() {
        String displayName = idRPi.getEditText().getText().toString();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .setPhotoUri(imageUri)
                    .build();

            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Imagen del perfil actualizada", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    private void uploadImageToFirebaseStorage() {
        StorageReference profileImageReference = FirebaseStorage.getInstance().getReference("profilepics/" + LoginActivity.USERUID + ".jpg");

        if (imageUri != null) {
            profileImageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    profileImageUrl = taskSnapshot.getUploadSessionUri().toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("PUTAIMG", e.getLocalizedMessage());
                }
            });
        }
    }

    public void raspberryToRegisterClick(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
}