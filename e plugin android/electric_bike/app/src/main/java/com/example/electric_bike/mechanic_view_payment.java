package com.example.electric_bike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class mechanic_view_payment extends AppCompatActivity implements JsonResponse{
    ListView l1;
    SharedPreferences sh;
    String[] requestedfor,amount,date,value;
    public static String rids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_view_payment);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lv);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)mechanic_view_payment.this;
        String q="/mechanic_view_payment?rid="+mechanic_view_request.rids;
        q=q.replace(" ","%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");

                requestedfor = new String[ja1.length()];
                amount = new String[ja1.length()];
                date = new String[ja1.length()];
                value = new String[ja1.length()];

//

                for (int i = 0; i < ja1.length(); i++) {

                    requestedfor[i] = ja1.getJSONObject(i).getString("requestedfor");

                    amount[i] = ja1.getJSONObject(i).getString("amount");
                    date[i] = ja1.getJSONObject(i).getString("date");
                    value[i] ="requestedfor: " + requestedfor[i]+"\namount: "+amount[i]+"\ndate: "+date[i];

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                l1.setAdapter(ar);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),mechanic_home.class);
        startActivity(b);
    }
}