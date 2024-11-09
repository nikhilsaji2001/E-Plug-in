package com.example.electric_bike;

import static com.example.electric_bike.user_view_bunk.lati;
import static com.example.electric_bike.user_view_bunk.logi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

public class user_view_mechanic_details extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_mechanic_details);
        tv1=(TextView) findViewById(R.id.textView9a);
        tv2=(TextView) findViewById(R.id.textView10a);
        tv3=(TextView) findViewById(R.id.textView11a);
        tv4=(TextView) findViewById(R.id.textView12a);
        b1=(Button) findViewById(R.id.buttona);
        b2=(Button) findViewById(R.id.button2a);
        tv1.setText("Name: "+user_view_mechanic.name);
        tv2.setText("Place: "+user_view_mechanic.pls);
        tv3.setText("Email: "+user_view_mechanic.em);
        tv4.setText("Phone: "+user_view_mechanic.ph);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://www.google.com/maps?q=" + user_view_mechanic.lati + "," + user_view_mechanic.logi;
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(in);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),user_request_mechanic.class));
            }
        });
    }


}