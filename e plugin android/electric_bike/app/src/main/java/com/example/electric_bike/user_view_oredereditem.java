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

public class user_view_oredereditem extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener{
    ListView l1;
    String s;
    SharedPreferences sh;
    String[] sparename,productname,amount,date,oid,value,latitude,longitude,statuss;
    public static String oids,lati,logi,amounts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_oredereditem);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        l1 = (ListView) findViewById(R.id.lv);
        l1.setOnItemClickListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) user_view_oredereditem.this;
        String q = "/user_view_oredereditem?lid=" + sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                oid=new String[ja1.length()];
                sparename = new String[ja1.length()];
                productname = new String[ja1.length()];
                amount = new String[ja1.length()];
                date = new String[ja1.length()];
                latitude=new String[ja1.length()];
                longitude=new String[ja1.length()];
                statuss = new String[ja1.length()];
                value = new String[ja1.length()];


//

                for (int i = 0; i < ja1.length(); i++) {
                    oid[i] = ja1.getJSONObject(i).getString("sparepart_id");

                    sparename[i] = ja1.getJSONObject(i).getString("firstname");
                    productname[i] = ja1.getJSONObject(i).getString("product_name");
                    amount[i] = ja1.getJSONObject(i).getString("amount");
                    date[i] = ja1.getJSONObject(i).getString("phone");
                    latitude[i]=ja1.getJSONObject(i).getString("latitude");
                    longitude[i]=ja1.getJSONObject(i).getString("longitude");
                    statuss[i] = ja1.getJSONObject(i).getString("status");
                    value[i] ="\nsparename: " + sparename[i]+"\nproductname: "+productname[i]+"\namount: "+amount[i]+"\ndate: "+date[i]+ "\nlatitude: "+latitude[i] + "\nlongitude: "+longitude[i]+"\nstatuss:"+statuss[i];

//

                }
//
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                l1.setAdapter(ar);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        oids = oid[position];
        lati = latitude[position];
        logi = longitude[position];
        amounts = amount[position];
        s = statuss[position];


            final CharSequence[] items = {"View Maps", "Payment","Review Spare","Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(user_view_oredereditem.this);
            // builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {

                    if (items[item].equals("View Maps")) {
                        String url = "http://www.google.com/maps?q=" + lati + "," + logi;
                        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(in);

                    } else if (items[item].equals("Payment")) {
                        startActivity(new Intent(getApplicationContext(), user_pay_amount.class));
                    } else if (items[item].equals("Review Spare")) {
                        startActivity(new Intent(getApplicationContext(), user_rate_sparepart.class));

                    } else if (items[item].equals("Cancel")) {
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
        Intent b=new Intent(getApplicationContext(),user_home.class);
        startActivity(b);
    }
}