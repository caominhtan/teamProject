package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class ThemThongtincanhan extends AppCompatActivity {

    EditText  fullname, email,diachi,  gioitinh, mobile,  cmnd;
    Button add, cancel;
    FirebaseAuth auth;
    String test;

    int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thongtincanhan);

        auth = FirebaseAuth.getInstance();
        maching();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sname=fullname.getText().toString();

                String saddress=gioitinh.getText().toString();
                String sgender=diachi.getText().toString();
                String smobile=mobile.getText().toString();

                String scmnd = cmnd.getText().toString();
                createUser(auth.getUid(),scmnd,saddress,sgender,smobile,sname);

                Intent intent = new Intent(ThemThongtincanhan.this,TrangChu.class);
                startActivity(intent);



            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void maching() {

        fullname = (EditText) findViewById(R.id.et_Hovatens);
        gioitinh= (EditText) findViewById(R.id.et_Gioitinhs);
        diachi = (EditText) findViewById(R.id.et_Diachis);
        mobile = (EditText) findViewById(R.id.et_Phones);
        cmnd= (EditText) findViewById(R.id.et_CMNDs);
        add =(Button) findViewById(R.id.btn_Thems);
        cancel =(Button) findViewById(R.id.btn_Xoas);

    }

    private void createUser(String userID,String cmnd,String address, String gender,String mobile,String name){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Thongtincanhan");

        myRef.child(userID).child("gender").setValue(gender);
        myRef.child(userID).child("mobile").setValue(mobile);
        myRef.child(userID).child("name").setValue(name);
        myRef.child(userID).child("address").setValue(address);
        myRef.child(userID).child("cmnd").setValue(cmnd);
    }

}