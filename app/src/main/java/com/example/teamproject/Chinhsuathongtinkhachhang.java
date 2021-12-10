package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Chinhsuathongtinkhachhang extends AppCompatActivity {
    EditText fullname, email,diachi,  gioitinh, mobile,  cmnd;
    Button add;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinhsuathongtinkhachhang);
        maching();

        loadUser( FirebaseAuth.getInstance().getUid());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname=fullname.getText().toString();

                String saddress=gioitinh.getText().toString();
                String sgender=diachi.getText().toString();
                String smobile=mobile.getText().toString();
                String scmnd = cmnd.getText().toString();
                createUser(auth.getUid(),scmnd,saddress,sgender,smobile,sname);
                Toast.makeText(getApplicationContext(),"Chỉnh sửa thông tin thành công",Toast.LENGTH_LONG).show();
                finish();


            }
        });

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
    private void maching() {

        fullname = (EditText) findViewById(R.id.et_Hovatens);
        gioitinh= (EditText) findViewById(R.id.et_Gioitinhs);
        email= (EditText) findViewById(R.id.et_Emails);
        diachi = (EditText) findViewById(R.id.et_Diachis);
        mobile = (EditText) findViewById(R.id.et_Phones);
        cmnd= (EditText) findViewById(R.id.et_CMNDs);
        add =(Button) findViewById(R.id.btn_Thems);

    }
    private void loadUser(String id) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Thongtincanhan/" + id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                HashMap<String, String> map = (HashMap<String, String>) dataSnapshot.getValue();
                fullname.setText(map.get("name"));
                gioitinh.setText(map.get("gender"));
                email.setText(map.get("email"));
                email.setFocusable(false);
                email.setEnabled(false);
                cmnd.setText(map.get("cmnd"));
                mobile.setText(map.get("mobile"));
                diachi.setText(map.get("address"));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }

}