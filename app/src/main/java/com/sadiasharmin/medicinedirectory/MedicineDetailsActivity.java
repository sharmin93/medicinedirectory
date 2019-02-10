package com.sadiasharmin.medicinedirectory;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MedicineDetailsActivity extends AppCompatActivity {
    private TextView medicineTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);
        medicineTextView = findViewById(R.id.tvMedicines);
        initFireBase();

    }
    public void initFireBase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(SharedDataUtil.MEDICINE_TO_LOAD);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = (String) dataSnapshot.getValue();
                medicineTextView.setText(s);
                medicineTextView.setTextSize(20);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



}
