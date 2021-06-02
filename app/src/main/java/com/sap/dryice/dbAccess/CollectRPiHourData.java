package com.sap.dryice.dbAccess;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sap.dryice.dbEntities.RTData;
import com.sap.dryice.dbEntities.RTExtendedData;

import java.util.ArrayList;
import java.util.List;

public class CollectRPiHourData {
    private static FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference myDatabaseReference;

    public static void takeData(Comunication comunication, String idRPi){
        myDatabaseReference = myDatabase.getReference("RPiHourData").child(idRPi);

        List<RTExtendedData> rtExtendedDataList = new ArrayList<>();
        myDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> data = snapshot.getChildren();
                for (DataSnapshot d: data) {
                    RTExtendedData rted = d.getValue(RTExtendedData.class);
                    rtExtendedDataList.add(rted);
                }
                comunication.sendRTExtendedData(rtExtendedDataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public interface Comunication{
        void sendRTExtendedData(List<RTExtendedData> rtData);
    }
}
