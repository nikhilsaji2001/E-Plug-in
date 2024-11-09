package com.example.electric_bike;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class user_view_mechanic_request extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener{
    ListView l1;
    SharedPreferences sh;
    String[] m_id,m_name,s_amount,date,rid,statuss,latitude,longitude,value;
    public static String mids,rids,lati,logi,amnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_mechanic_request);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lv);
        l1.setOnItemClickListener(this);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)user_view_mechanic_request.this;
        String q="/user_view_mechanic_request?lid="+sh.getString("log_id","");
        q=q.replace(" ","%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method = jo.getString("method");
            if (method.equalsIgnoreCase("user_cancel_mrequest")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "UPDATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), user_view_mechanic_request.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            } else if (method.equalsIgnoreCase("user_view_mechanic_request"))
            {

                String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                rid = new String[ja1.length()];
                m_id = new String[ja1.length()];
                m_name = new String[ja1.length()];
                s_amount = new String[ja1.length()];
                date = new String[ja1.length()];
                statuss = new String[ja1.length()];
                latitude = new String[ja1.length()];
                longitude = new String[ja1.length()];
                value = new String[ja1.length()];

//

                for (int i = 0; i < ja1.length(); i++) {
                    rid[i] = ja1.getJSONObject(i).getString("mrequest_id");
                    m_id[i] = ja1.getJSONObject(i).getString("mechanic_id");

                    m_name[i] = ja1.getJSONObject(i).getString("firstname");
                    s_amount[i] = ja1.getJSONObject(i).getString("serviceamount");
                    date[i] = ja1.getJSONObject(i).getString("date");
                    statuss[i] = ja1.getJSONObject(i).getString("status");
                    latitude[i] = ja1.getJSONObject(i).getString("latitude");
                    longitude[i] = ja1.getJSONObject(i).getString("longitude");
                    value[i] = "rid: " + rid[i] + "\nname: " + m_name[i] + "\ns_amount: " + s_amount[i] + "\ndate: " + date[i] + "\nstatuss: " + statuss[i] + "\nlatitude: " + latitude[i] + "\nlongitude: "+longitude[i];

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                l1.setAdapter(ar);

            }
        }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        rids = rid[position];
        mids = m_id[position];
        lati=latitude[position];
        logi=longitude[position];
        amnt=s_amount[position];
        final CharSequence[] items = {"Map","Cancel Request","Review","Payment","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(user_view_mechanic_request.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Map"))
                {
                    String url = "http://www.google.com/maps?q=" + lati + "," + logi;
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(in);

                }
                else if (items[item].equals("Cancel Request")) {
                    JsonReq JR=new JsonReq();
                    JR.json_response=(JsonResponse)user_view_mechanic_request.this;
                    String q="/user_cancel_mrequest?rid=" + user_view_mechanic_request.rids;
                    q=q.replace(" ","%20");
                    JR.execute(q);
                }
                else if (items[item].equals("Review")) {
                    startActivity(new Intent(getApplicationContext(), user_rate_mechanic.class));
                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }else if (items[item].equals("Payment")) {
                    startActivity(new Intent(getApplicationContext(), user_pay_mechanic.class));
                }


            }

        });
        builder.show();
    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),user_home.class);
        startActivity(b);
    }
}