package com.sap.dryice.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sap.dryice.R;
import com.sap.dryice.dbAccess.CollectRPiUsers;
import com.sap.dryice.dbEntities.RPiUser;
import com.sap.dryice.dbEntities.RTData;
import com.sap.dryice.dbEntities.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements CollectRPiUsers.Comunication {

    public static String USERUID = null;
    public static String RPI_USERUID = null;
    private TextInputLayout email, passwd;
    private TextView signUp;
    private Button btnLogin;
    private static FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.loginBtn);
        email = findViewById(R.id.textInputEmailLogin);
        passwd = findViewById(R.id.textInputPassLogin);

        mAuth = FirebaseAuth.getInstance();

        loginButtonClick();
    }

    /**
     * In this method we have put the setOnClickListener of the login button.
     */
    private void loginButtonClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    public void loginUser() {
        String correo = email.getEditText().getText().toString().trim();
        String pwd = passwd.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(correo)) {
            email.setError("Enter your email");
            return;
        } else if (TextUtils.isEmpty(pwd)) {
            passwd.setError("Enter your password");
            return;
        } else if (pwd.length() < 6) {
            passwd.setError("Minimum length of password should be 6");
            return;
        } else if (!isValidEmail(correo)) {
            email.setError("This is not a valid email");
            return;
        } else {
            mAuth.signInWithEmailAndPassword(correo, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        USERUID = mAuth.getCurrentUser().getUid();
                        CollectRPiUsers.takeData(LoginActivity.this::sendDataRPiUsers);
                    } else {
                        Toast.makeText(getApplicationContext(), "Signed in failed", Toast.LENGTH_SHORT).show();
                        Log.d("ERRORLOGIN", task.getException().toString());
                    }
                }
            });
        }
    }

    private boolean isValidEmail(String correo) {
        return (!TextUtils.isEmpty(correo) && Patterns.EMAIL_ADDRESS.matcher(correo).matches());
    }

    public void loginToRegisterSidebarClick(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

    public void loginToGraphicActivity(View view) {
        startActivity(new Intent(this, GraphicsActivity.class));
        overridePendingTransition(R.anim.slide_in_top, R.anim.stay);
    }

    @Override
    public void sendDataRPiUsers(List<RPiUser> idsRPis) {
        for (RPiUser rpi : idsRPis) {
            if (rpi.getUserId().equals(USERUID)) {
                RPI_USERUID = rpi.getIdRPi();
                Intent accessIntent = new Intent(getApplicationContext(), GraphicsActivity.class);
                accessIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                accessIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(accessIntent);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null){
            USERUID = firebaseUser.getUid();
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users").child(USERUID);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User e = snapshot.getValue(User.class);
                    RPI_USERUID = e.getIdRPi();

                    Intent accessIntent = new Intent(getApplicationContext(), GraphicsActivity.class);
                    accessIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    accessIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(accessIntent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}