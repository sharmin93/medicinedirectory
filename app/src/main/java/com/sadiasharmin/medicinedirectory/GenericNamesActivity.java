package com.sadiasharmin.medicinedirectory;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GenericNamesActivity extends AppCompatActivity {
    private ListView genericListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_names);
        genericListView = findViewById(R.id.lvGenericNames);
        genericListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                String genericNames= textView.getText().toString();
                SharedDataUtil.MEDICINE_TO_LOAD = genericNames;

                Intent  medicineDetails = new Intent(GenericNamesActivity.this, MedicineDetailsActivity.class );
                startActivity(medicineDetails);
            }
        });


        initFireBase();

    }
    public void initFireBase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(SharedDataUtil.CATAGORY_TO_LOAD);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> dataFromFirebase = (ArrayList<String>) dataSnapshot.getValue();
                dataFromFirebase.remove(null);
                loadDataOnListView(dataFromFirebase);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void loadDataOnListView(ArrayList<String> dataToLoad){
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,dataToLoad);

        genericListView.setAdapter(stringArrayAdapter);
    }
}
