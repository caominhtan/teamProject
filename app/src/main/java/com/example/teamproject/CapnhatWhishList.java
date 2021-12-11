package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class CapnhatWhishList extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    EditText nameud,priceud,likeud,quantityud,detailud;
    Button xacnhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhat_whish_list);
        maching();

        loadUser( FirebaseAuth.getInstance().getUid());
        xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNameud=nameud.getText().toString();

                String sPriceud=priceud.getText().toString();
                String sLikeud=likeud.getText().toString();
                String sQuantityud=quantityud.getText().toString();
                String sDetailud = detailud.getText().toString();
                createUser(auth.getUid(),sPriceud,sLikeud,sQuantityud,sDetailud,sNameud);
                Toast.makeText(getApplicationContext(),"Chỉnh sửa thông tin thành công",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
    private void createUser(String userID,String nameud,String priceud, String likeud,String quantityud,String detailud) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("whishlist");

        myRef.child(userID).child("Name").setValue(nameud);
        myRef.child(userID).child("Price").setValue(priceud);
        myRef.child(userID).child("Like").setValue(likeud);
        myRef.child(userID).child("Quantity").setValue(quantityud);
        myRef.child(userID).child("Detail").setValue(detailud);
    }
    private void maching() {
        xacnhan = (Button) findViewById(R.id.btn_xacnhan);
        nameud = (EditText) findViewById(R.id.et_nameud);
        priceud = (EditText) findViewById(R.id.et_priceud);
        likeud = (EditText) findViewById(R.id.et_likeud);
        quantityud = (EditText) findViewById(R.id.et_quantityud);
        detailud = (EditText) findViewById(R.id.et_detailud);
    }
    private void loadUser(String id) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("whishlist/" + id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                HashMap<String, String> map = (HashMap<String, String>) dataSnapshot.getValue();
                nameud.setText(map.get("Name"));
                priceud.setText(map.get("Price"));
                likeud.setText(map.get("Like"));
                quantityud.setText(map.get("Quantity"));
                detailud.setText(map.get("Detail"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
}