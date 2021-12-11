package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Fooditem extends AppCompatActivity {
    private int thumbnail;
    private String labelFood;
    private String contentFood;
    private String priceFood;

    public Fooditem(int thumbnail, String labelFood, String contentFood, String priceFood) {
        this.thumbnail = thumbnail;
        this.labelFood = labelFood;
        this.contentFood = contentFood;
        this.priceFood = priceFood;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLabelFood() {
        return labelFood;
    }

    public void setLabelFood(String labelFood) {
        this.labelFood = labelFood;
    }

    public String getContentFood() {
        return contentFood;
    }

    public void setContentFood(String contentFood) {
        this.contentFood = contentFood;
    }

    public String getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(String priceFood) {
        this.priceFood = priceFood;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooditem);
    }
}