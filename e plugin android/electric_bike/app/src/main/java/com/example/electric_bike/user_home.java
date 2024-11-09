package com.example.electric_bike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user_home extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        b1=(Button) findViewById(R.id.btn1);
        b2=(Button) findViewById(R.id.btn2);
        b3=(Button) findViewById(R.id.btn3);
        b4=(Button) findViewById(R.id.btn4);
        b5=(Button) findViewById(R.id.btn5);
//        b6=(Button) findViewById(R.id.btn6);
        b7=(Button)findViewById(R.id.logut);
        b8=(Button)findViewById(R.id.comp);
        b9=(Button)findViewById(R.id.bunk);
        b10=(Button)findViewById(R.id.vehicle);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),user_manage_profile.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),user_view_recharge_request.class));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),user_view_mechanic.class));
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),user_view_mechanic_request.class));
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),user_view_service_center.class));
            }
        });
//        b6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),user_view_product.class));
//            }
//        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),user_send_complaint.class));
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),user_view_bunk.class));
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });
       b10.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(),user_manage_vehicle.class));
          }
      });
    }
}