package com.example.electric_bike;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class user_view_spareparts extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener{
    ListView l1;


    SharedPreferences sh;
    String[] fname, lname, place,phone,email,sid,value,latitude,longitude;
    public static String sids,lati,logi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_spareparts);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        l1 = (ListView) findViewById(R.id.lv);
        l1.setOnItemClickListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) user_view_spareparts.this;
        String q = "/user_view_spareparts?lid=" + sh.getString("log_id", "");
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
                sid=new String[ja1.length()];
                fname = new String[ja1.length()];
                lname = new String[ja1.length()];
                place = new String[ja1.length()];
                phone = new String[ja1.length()];
                email = new String[ja1.length()];
                latitude=new String[ja1.length()];
                longitude=new String[ja1.length()];
                value = new String[ja1.length()];


//

                for (int i = 0; i < ja1.length(); i++) {
                    sid[i] = ja1.getJSONObject(i).getString("sparepart_id");

                    fname[i] = ja1.getJSONObject(i).getString("fname");
                    lname[i] = ja1.getJSONObject(i).getString("lname");
                    place[i] = ja1.getJSONObject(i).getString("place");
                    phone[i] = ja1.getJSONObject(i).getString("phone");
                    email[i] = ja1.getJSONObject(i).getString("email");
                    latitude[i]=ja1.getJSONObject(i).getString("latitude");
                    longitude[i]=ja1.getJSONObject(i).getString("longitude");
                    value[i] ="sid: " + sid[i]+"\nfname: " + fname[i]+"\nplace: "+place[i]+"\nphone: "+phone[i]+"\nemail: "+email[i]+ "\nlatitude: "+latitude[i] + "\nlongitude: ";
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
        sids=sid[position];
        lati=latitude[position];
        logi=longitude[position];

        final CharSequence[] items = {"View Maps","Products" ,"Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(user_view_spareparts.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("View Maps"))
                {
                    String url = "http://www.google.com/maps?q=" + lati + "," + logi;
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(in);

                }
                else if (items[item].equals("Products")) {
                    startActivity(new Intent(getApplicationContext(), user_view_product.class));

                }
                else if(items[item].equals("Cancel")){
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