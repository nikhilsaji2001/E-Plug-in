package com.example.electric_bike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class user_compliant_bunk extends AppCompatActivity implements JsonResponse{
    EditText e1;
    Button b1;
    String comp;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_compliant_bunk);

                sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                e1=(EditText)findViewById(R.id.et1a);
                b1=(Button)findViewById(R.id.btn1a);

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        comp=e1.getText().toString();
                        JsonReq JR = new JsonReq();
                        JR.json_response = (JsonResponse)user_compliant_bunk.this;
                        String q = "/user_send_complaint_bunk?complaint=" + comp + "&lid=" + sh.getString("log_id", "")+"&bunkid="+user_view_recharge_request.bids;
                        q = q.replace(" ", "%20");
                        JR.execute(q);
                    }
                });
            }

            @Override
            public void response(JSONObject jo) {
                try {

                    String method=jo.getString("method");
                    if(method.equalsIgnoreCase("user_send_complaint_bunk")) {

                        String status = jo.getString("status");
                        Log.d("pearl", status);


                        if (status.equalsIgnoreCase("success")) {
                            Toast.makeText(getApplicationContext(), "SEND SUCCESSFULLY", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), user_home.class));

                        } else {

                            Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                        }
                    }



                }

                catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
            public void onBackPressed()
            {
                // TODO Auto-generated method stub
                super.onBackPressed();
                Intent b=new Intent(getApplicationContext(),user_home.class);
                startActivity(b);
            }
        }

