package com.sap.dryice.dbAccess;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sap.dryice.dbEntities.RPiUser;
import com.sap.dryice.dbEntities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectRPiUsers {
    private static FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference myDatabaseReference = myDatabase.getReference("RPiUsers");

    public static void updateLocation(double latitud, double longitud, String username) {
        Map<String,Object> updateLatLong = new HashMap<>();
        updateLatLong.put("latitud",latitud);
        updateLatLong.put("longitud",longitud);
        myDatabaseReference.child(username).updateChildren(updateLatLong);
    }

    public static void takeData(Comunication comunication){
        List<RPiUser> idsRPis = new ArrayList<>();
        myDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> data = snapshot.getChildren();
                for (DataSnapshot d: data) {
                    RPiUser idRPi = d.getValue(RPiUser.class);
                    idsRPis.add(idRPi);
                }
                comunication.sendDataRPiUsers(idsRPis);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public static void saveFirebase(RPiUser rPiUser) {
        myDatabaseReference.child(rPiUser.getIdRPi()).setValue(rPiUser);
    }

    public interface Comunication{
        void sendDataRPiUsers(List<RPiUser> idsRPis);
    }
}
