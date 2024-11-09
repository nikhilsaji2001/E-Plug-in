package com.example.electric_bike;

import static com.example.electric_bike.user_view_mechanic.mids;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;

public class user_request_mechanic extends AppCompatActivity implements JsonResponse{
    Button b1;
    SharedPreferences sh;
    DatePicker dp;
    Spinner veh;
    CharSequence[] my_type_id,type_name;
    String[] veh_id,veh_name;
    String vehid,typeid,date;
    public static String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request_mechanic);



            sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            b1=(Button)findViewById(R.id.buttonssss1);



            JsonReq JR=new JsonReq();
            JR.json_response=(JsonResponse) user_request_mechanic.this;
            String q="/user_request_mechanic_select_type?lid="+sh.getString("log_id","");
            q=q.replace(" ","%20");
            JR.execute(q);
            dp=(DatePicker) findViewById(R.id.date_picker1) ;

            veh=(Spinner)findViewById(R.id.spinnerhjhjhjdd1) ;

            veh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    vehid=veh_id[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                int day = dp.getDayOfMonth();
//                int month = dp.getMonth() + 1;
//                int year = dp.getYear();
//
//                date = year + "/" + month + "/" + day;
                    date = String.format("%04d-%02d-%02d", dp.getYear(), dp.getMonth() + 1, dp.getDayOfMonth());

                    JsonReq JR=new JsonReq();
                    JR.json_response=(JsonResponse) user_request_mechanic.this;
                    String q="/user_request_mechanic?lid="+sh.getString("log_id","")+"&mid="+mids+"&date="+date+"&vid="+vehid;
                    q=q.replace(" ","%20");
                    JR.execute(q);
                }
            });
        }
        @Override
        public void response(JSONObject jo) {
            try
            {
                String method = jo.getString("method");
                if (method.equalsIgnoreCase("user_request_mechanic_select_type"))
                {
                    try {
                        String status = jo.getString("status");
                        Log.d("pearl", status);
                        JSONArray ja2 = (JSONArray) jo.getJSONArray("veh");

                        if (status.equalsIgnoreCase("success"))
                        {
                            veh_id=new String[ja2.length()];
                            veh_name=new String[ja2.length()];
                            for (int j=0;j< ja2.length();j++ )
                            {
                                veh_id[j]=ja2.getJSONObject(j).getString("vehicle_id");
                                veh_name[j]=ja2.getJSONObject(j).getString("vehicle_name");
                            }
                            ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,veh_name);
                            veh.setAdapter(adapter1);
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                else if(method.equalsIgnoreCase("user_request_mechanic"))
                {
                    String status = jo.getString("status");
                    if (status.equalsIgnoreCase("success"))
                    {
                        startActivity(new Intent(getApplicationContext(),user_view_mechanic.class));
                    }
                }
            }
            catch (Exception e)
            {
                // TODO: handle exception
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
