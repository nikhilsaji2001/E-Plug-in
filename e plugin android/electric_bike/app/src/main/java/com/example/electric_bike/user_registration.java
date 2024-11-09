package com.example.electric_bike;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class user_registration extends AppCompatActivity implements JsonResponse{
    EditText e1,e2,e3,e4,e5,e6,e7;
    RadioButton r1,r2;

    String fname,lname,place,phone,email,uname,pass;

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        e1=(EditText)findViewById(R.id.etf);
        e2=(EditText)findViewById(R.id.etl);
        e3=(EditText)findViewById(R.id.etpl);
        e4=(EditText)findViewById(R.id.etph);
        e5=(EditText)findViewById(R.id.etem);
        e6=(EditText)findViewById(R.id.etu);
        e7=(EditText)findViewById(R.id.etpas);
        b1=(Button)findViewById(R.id.btn);
        startService(new Intent(getApplicationContext(),LocationService.class));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=e1.getText().toString();
                lname=e2.getText().toString();
                place=e3.getText().toString();
                phone=e4.getText().toString();
                email=e5.getText().toString();
                uname=e6.getText().toString();
                pass=e7.getText().toString();
                if (fname.equalsIgnoreCase("")){
                    e1.setError("enter your firstname");
                    e1.setFocusable(true);
                }
                else
                {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) user_registration.this;
                    String q = "/user_registration?firstname=" + fname + "&password=" + pass + "&lastname="+lname + "&place="+place +"&phone="+phone+"&email="+email+"&username="+uname + "&latitude="+LocationService.lati +"&longitude="+LocationService.logi;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("user_registration")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), login.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }



        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}