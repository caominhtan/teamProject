package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class WhishList extends AppCompatActivity {

    ListView listlike;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whish_list);
        matching();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listlike.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("whishlist");

        listlike.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = adapter.getItem(position);
                String key = data.split("\n")[0];
                Intent intent = new Intent(getApplicationContext(),CapnhatWhishList.class);
                intent.putExtra("ID",key);
                startActivity(intent);
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                for (DataSnapshot data: snapshot.getChildren()
                ) {
                    String key = data.getKey();
                    String value = data.getValue().toString();
                    adapter.add(key +"\n"+value);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Firebase","loadPost:onCancelled", error.toException());
            }
        });
    }
    private void matching() {
        listlike = (ListView) findViewById(R.id.lv_likes);
    }
}