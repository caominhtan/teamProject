package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThemWhishlist extends AppCompatActivity {
    Button yes, no;
    EditText name,price,like,quantity,detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_whishlist);
        matching();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("whishlist");
                String sName = name.getText().toString().trim();
                String sPrice = price.getText().toString().trim();
                String sLike = like.getText().toString().trim();
                String sQuantity = quantity.getText().toString().trim();
                String sDetail = detail.getText().toString().trim();


                myRef.child("Name").setValue(sName);
                myRef.child("Price").setValue(sPrice);
                myRef.child("Quantity").setValue(sQuantity);
                myRef.child("Like").setValue(sLike);
                myRef.child("Detail").setValue(sDetail);
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void matching() {
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        name = (EditText) findViewById(R.id.et_name);
        price = (EditText) findViewById(R.id.et_price);
        like = (EditText) findViewById(R.id.et_like);
        quantity = (EditText) findViewById(R.id.et_quantity);
        detail = (EditText) findViewById(R.id.et_detail);
    }
}