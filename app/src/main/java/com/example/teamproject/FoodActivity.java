package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        recycleView = findViewById(R.id.Rv_recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recycleView.addItemDecoration(itemDecoration);
    }
    public List<Fooditem> getmFoodData(){
        mFoodData = new ArrayList<>();
        mFoodData.add(new Fooditem(R.drawable.garan, "Gà Rán", "Rất ngon", "150000"));
        mFoodData.add(new Fooditem(R.drawable.pizza, "Gà Rán", "Siêu ngon", "100000"));
        mFoodData.add(new Fooditem(R.drawable.miden, "Gà Rán", "Ngon ngon", "80000"));
        mFoodData.add(new Fooditem(R.drawable.miden, "Gà Rán", "Ngon ngon", "80000"));
        mFoodData.add(new Fooditem(R.drawable.miden, "Gà Rán", "Ngon ngon", "80000"));
        mFoodData.add(new Fooditem(R.drawable.miden, "Gà Rán", "Ngon ngon", "80000"));
        mFoodData.add(new Fooditem(R.drawable.miden, "Gà Rán", "Ngon ngon", "80000"));
        mFoodData.add(new Fooditem(R.drawable.miden, "Gà Rán", "Ngon ngon", "80000"));
        mFoodData.add(new Fooditem(R.drawable.miden, "Gà Rán", "Ngon ngon", "80000"));
        return mFoodData;

    }

}