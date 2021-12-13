package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class them extends AppCompatActivity {
    EditText name, price, quanlity;
    Button save, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
         matching();


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("foods");

                myRef.child("Name").setValue(name.getText().toString());
                myRef.child("Price").setValue(price.getText().toString());
                myRef.child("Quantity").setValue(quanlity.getText().toString());

            }
        });
    }

    private void matching() {
        name = (EditText) findViewById(R.id.Name);
        price = (EditText) findViewById(R.id.Price);
        quanlity = (EditText) findViewById(R.id.Quanlity);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
    }

}