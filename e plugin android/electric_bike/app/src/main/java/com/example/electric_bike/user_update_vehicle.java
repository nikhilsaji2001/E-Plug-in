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

import org.json.JSONArray;
import org.json.JSONObject;

public class user_update_vehicle extends AppCompatActivity implements JsonResponse{
    EditText e1,e2,e3,e4,e5;
    Button b1;
    String vehicle_name,type;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_vehicle);

                sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                startService(new Intent(getApplicationContext(),LocationService.class));
                e1=(EditText)findViewById(R.id.etdsfd1);
                e2=(EditText)findViewById(R.id.etsds2);


                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) user_update_vehicle.this;
                String q="/user_view_vehicle_for_update?vehicle_id="+user_manage_vehicle.vehicleid;
                q=q.replace(" ","%20");
                JR.execute(q);

                b1=(Button)findViewById(R.id.btnfdfdd);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vehicle_name=e1.getText().toString();
                        type=e2.getText().toString();

                        JsonReq JR=new JsonReq();
                        JR.json_response=(JsonResponse) user_update_vehicle.this;

                        String q="/user_update_vehicle?vehicle_id="+user_manage_vehicle.vehicleid+"&vehicle_name="+vehicle_name+"&type="+type;
                        q=q.replace(" ","%20");
                        JR.execute(q);
                    }
                });
            }

            @Override
            public void response(JSONObject jo) {

                try {

                    String method = jo.getString("method");
                    if (method.equalsIgnoreCase("user_update_vehicle")) {

                        String status = jo.getString("status");
                        Log.d("pearl", status);


                        if (status.equalsIgnoreCase("success")) {
                            Toast.makeText(getApplicationContext(), "Update SUCCESSFULLY", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), user_home.class));

                        } else {

                            Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                        }
                    } else if (method.equalsIgnoreCase("user_view_vehicle_for_update")) {
                        String status = jo.getString("status");
                        Log.d("pearl", status);


                        if (status.equalsIgnoreCase("success")) {
                            JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
//                    //feedback_id=new String[ja1.length()];
//                    complaints=new String[ja1.length()];
//                    reply=new String[ja1.length()];
//                    date=new String[ja1.length()];
//                    value=new String[ja1.length()];
//
                            for (int i = 0; i < ja1.length(); i++) {
                                e1.setText(ja1.getJSONObject(0).getString("vehicle_name"));
                                e2.setText(ja1.getJSONObject(0).getString("type"));

//                        complaints[i]=ja1.getJSONObject(i).getString("complaint");
//                        reply[i]=ja1.getJSONObject(i).getString("reply");
//                        date[i]=ja1.getJSONObject(i).getString("date");
//                        value[i]="complaints: "+complaints[i]+"\nreply: "+reply[i]+"\ndate: "+date[i];

                            }
//                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,value);
//                    l1.setAdapter(ar);
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


