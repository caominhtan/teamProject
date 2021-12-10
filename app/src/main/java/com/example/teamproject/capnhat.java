package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class capnhat extends AppCompatActivity {
    EditText name, price, quanlity;
    Button edit, delete;
    HashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhat);
        matching();

        String data = getIntent().getStringExtra("key");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("foods/" + data);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hashMap = (HashMap<String, String>) snapshot.getValue();

                name.setText(hashMap.get("Name"));
                price.setText(hashMap.get("Price"));
                quanlity.setText(hashMap.get("Quantity"));


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("foods");

                myRef.child("Name").setValue(name.getText().toString());
                myRef.child("Price").setValue(price.getText().toString());
                myRef.child("Quantity").setValue(quanlity.getText().toString());

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("foods");

                myRef.child(data).removeValue();

            }
        });
    }


    private void matching() {
        name = (EditText) findViewById(R.id.Namecapnhat);
        price = (EditText) findViewById(R.id.Pricecapnhat);
        quanlity = (EditText) findViewById(R.id.Quanlitycapnhat);
        edit = (Button) findViewById(R.id.savecapnhat);
        delete = (Button) findViewById(R.id.cancelcapnhat);
    }
}