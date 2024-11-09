package com.example.electric_bike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class user_order_product extends AppCompatActivity {
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order_product);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
}