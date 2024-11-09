package com.example.electric_bike;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class mechanic_view_request extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener{
    ListView l1;
    SharedPreferences sh;
    String[] date,rid,uid,latitude,longitude,statuss,username,value;
    public static String rids,lati,logi,uids;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_view_request);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lv);
        l1.setOnItemClickListener(this);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)mechanic_view_request.this;
        String q="/mechanic_view_request?lid="+sh.getString("log_id","");
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
                rid=new String[ja1.length()];
                uid = new String[ja1.length()];
                username = new String[ja1.length()];
                date = new String[ja1.length()];
                statuss = new String[ja1.length()];
                value = new String[ja1.length()];

//

                for (int i = 0; i < ja1.length(); i++) {
                    rid[i] = ja1.getJSONObject(i).getString("mrequest_id");

                    uid[i] = ja1.getJSONObject(i).getString("user_id");
                    username[i] = ja1.getJSONObject(i).getString("firstname");
                    date[i] = ja1.getJSONObject(i).getString("date");
                    statuss[i] = ja1.getJSONObject(i).getString("status");
                    value[i] ="username: "+username[i]+"\ndate: "+date[i]+"\nstatus: "+statuss[i];

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                l1.setAdapter(ar);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        rids = rid[position];
        uids = uid[position];


        final CharSequence[] items = {"Accept","Reject","view customer","upload service charge","View Payment","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(mechanic_view_request.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {


                if (items[item].equals("Accept")) {
                    JsonReq JR=new JsonReq();
                    JR.json_response=(JsonResponse)mechanic_view_request.this;
                    String q="/mechanic_accept_request?lid="+sh.getString("log_id","")+"&rid=" + rids;
                    q=q.replace(" ","%20");
                    JR.execute(q);
                    startActivity(new Intent(getApplicationContext(),mechanic_view_request.class));

                }
                else if (items[item].equals("Reject")) {
                    JsonReq JR=new JsonReq();
                    JR.json_response=(JsonResponse)mechanic_view_request.this;
                    String q="/mechanic_reject_request?lid="+sh.getString("log_id","")+"&rid=" + rids;
                    q=q.replace(" ","%20");
                    JR.execute(q);
                    startActivity(new Intent(getApplicationContext(),mechanic_view_request.class));

                }
               else if (items[item].equals("view customer")) {
                    startActivity(new Intent(getApplicationContext(), mechanic_view_customer.class));

                }
                else if (items[item].equals("upload service charge")) {
                    startActivity(new Intent(getApplicationContext(), mechanic_upload_servicecharge.class));
                }
                else if (items[item].equals("View Payment")) {
                    startActivity(new Intent(getApplicationContext(), mechanic_view_payment.class));
                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }

            }

        });
        builder.show();
    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),mechanic_home.class);
        startActivity(b);
    }
}