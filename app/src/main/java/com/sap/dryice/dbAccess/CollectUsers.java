package com.sap.dryice.dbAccess;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sap.dryice.dbEntities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectUsers {
    private static FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference myDatabaseReference = myDatabase.getReference("Users");

    public static void updateLocation(double latitud, double longitud, String usurname) {
        Map<String,Object> updateLatLong = new HashMap<>();
        updateLatLong.put("latitud",latitud);
        updateLatLong.put("longitud",longitud);
        myDatabaseReference.child(usurname).updateChildren(updateLatLong);
    }

    public static void takeData(Comunication comunication){
        List<User> users = new ArrayList<>();
        myDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> datos = snapshot.getChildren();
                for (DataSnapshot d: datos) {
                    User u = d.getValue(User.class);
                    users.add(u);
                }
                comunication.sendDataUsers(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private static String changeName(String nombre_user) {
        String nombre;
        String[] nombreCompleto = nombre_user.split(" ");
        if (nombreCompleto.length >= 2) {
            return nombre = nombreCompleto[0] + " " + nombreCompleto[1];
        } else {
            return nombre = nombreCompleto[0];
        }
    }

    private static String changeUsername(String email) {
        String[] array = email.split("@");
        return array[0];
    }

    public interface Comunication{
        void sendDataUsers(List<User> users);
    }
}
