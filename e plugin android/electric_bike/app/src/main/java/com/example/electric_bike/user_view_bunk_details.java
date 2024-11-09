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

public class user_view_bunk_details extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_bunk_details);

        tv1=(TextView) findViewById(R.id.textView9);
        tv2=(TextView) findViewById(R.id.textView10);
        tv3=(TextView) findViewById(R.id.textView11);
        tv4=(TextView) findViewById(R.id.textView12);
        b1=(Button) findViewById(R.id.button);
        b2=(Button) findViewById(R.id.button2);
        tv1.setText("Name: "+user_view_bunk.name);
        tv2.setText("Place: "+user_view_bunk.pls);
        tv3.setText("Email: "+user_view_bunk.em);
        tv4.setText("Phone: "+user_view_bunk.ph);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://www.google.com/maps?q=" + lati + "," + logi;
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                   startActivity(in);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),User_send_request.class));
            }
        });
    }
}