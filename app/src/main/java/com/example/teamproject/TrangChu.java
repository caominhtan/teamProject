package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.teamproject.model.Produuct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class TrangChu extends AppCompatActivity {
    ImageView btn_profile, btn_whishlist,btn_giohang;
    Button ThemSp;
    HashMap<String, Produuct> hashMap;
    FloatingActionButton giohang;
    ListView listfoods;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        matching();

        ThemSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gothem = new Intent(TrangChu.this, them.class);
                startActivity(gothem);
            }
        });

        listfoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = adapter.getItem(position);
                Intent gocapnhat = new Intent(TrangChu.this, capnhat.class);
                gocapnhat.putExtra("key", data);
                startActivity(gocapnhat);
            }
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listfoods.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("foods");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                for (DataSnapshot data: snapshot.getChildren()) {
                    String data1 = data.getValue().toString();
                    String[] data2 = data1.substring(1, data1.length()-1).split(",");
                    String data3 = "";
                    for (int i = 0; i < data2.length; i++) {
                        data3 += data2[i] + "\n";
                    }
                    adapter.add(data3);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Firebase","loadPost:onCancelled", error.toException());
            }
        });

        ThemSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChu.this,Profile.class);
                startActivity(intent);
            }
        });
        btn_whishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChu.this, WhishList.class);
                startActivity(intent);
            }
        });
        btn_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChu.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        listfoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = adapter.getItem(position);
                String key = data.split("\n")[0];
                Intent intent = new Intent(getApplicationContext(),ThemWhishlist.class);
                intent.putExtra("ID",key);
                startActivity(intent);
            }
        });
        giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void getProduct(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("foods");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void matching() {
        ThemSp = (Button) findViewById(R.id.addProduct);
        giohang = (FloatingActionButton) findViewById(R.id.btn_giohang);
        listfoods = (ListView) findViewById(R.id.lv_listfood);
        btn_profile = (ImageView) findViewById(R.id.home_btn_profile);
        btn_whishlist = (ImageView) findViewById(R.id.btn_like);
        btn_giohang = (ImageView) findViewById(R.id.btn_giohang);
    }
}