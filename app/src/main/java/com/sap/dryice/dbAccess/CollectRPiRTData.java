package com.sap.dryice.dbAccess;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sap.dryice.dbEntities.RTData;

import java.util.ArrayList;
import java.util.List;

public class CollectRPiRTData {
    private static FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference myDatabaseReference;

    public static void takeData(Comunication comunication){
        myDatabaseReference = myDatabase.getReference("RTData");

        List<RTData> rtDataList = new ArrayList<>();
        myDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> data = snapshot.getChildren();
                for (DataSnapshot d: data) {
                    RTData rtData = d.getValue(RTData.class);
                    rtDataList.add(rtData);
                }
                comunication.sendDataRTData(rtDataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public interface Comunication{
        void sendDataRTData(List<RTData> rtData);
    }
}
