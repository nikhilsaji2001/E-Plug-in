package com.example.electric_bike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONObject;

public class mechanic_registeration extends AppCompatActivity implements JsonResponse{
    EditText e1,e2,e3,e4,e5,e6,e7;
    RadioButton r1,r2;

    String fname,lname,place,phone,email,uname,pass;

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_registeration);

                e1=(EditText)findViewById(R.id.etf1);
                e2=(EditText)findViewById(R.id.etl1);
                e3=(EditText)findViewById(R.id.etpl1);
                e4=(EditText)findViewById(R.id.etph1);
                e5=(EditText)findViewById(R.id.etem1);
                e6=(EditText)findViewById(R.id.etu1);
                e7=(EditText)findViewById(R.id.etpas1);
                b1=(Button)findViewById(R.id.btn11);
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
                            JR.json_response = (JsonResponse) mechanic_registeration.this;
                            String q = "/mechanic_registration?firstname=" + fname + "&password=" + pass + "&lastname="+lname + "&place="+place +"&phone="+phone+"&email="+email+"&username="+uname + "&latitude="+LocationService.lati +"&longitude="+LocationService.logi;
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
                    if(method.equalsIgnoreCase("mechanic_registration")) {

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




