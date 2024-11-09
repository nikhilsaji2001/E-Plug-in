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

public class mechanic_view_customer extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener{
    ListView l1;
    SharedPreferences sh;
    String[] firstname,lastname,place,phone,email,value,uuid,latitude,longitude;
    public static String uuids,lati,logi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_view_customer);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lv);
        l1.setOnItemClickListener(this);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)mechanic_view_customer.this;
        String q="/mechanic_view_customer?uid="+mechanic_view_request.uids;
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
                uuid=new String[ja1.length()];
                firstname = new String[ja1.length()];
                lastname = new String[ja1.length()];
                place=new String[ja1.length()];
                phone=new String[ja1.length()];
                email=new String[ja1.length()];
                latitude=new String[ja1.length()];
                longitude=new String[ja1.length()];
                value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    uuid[i] = ja1.getJSONObject(i).getString("user_id");
                    firstname[i] = ja1.getJSONObject(i).getString("firstname");
                    lastname[i] = ja1.getJSONObject(i).getString("lastname");
                    place[i]=ja1.getJSONObject(i).getString("place");
                    phone[i]=ja1.getJSONObject(i).getString("phone");
                    email[i]=ja1.getJSONObject(i).getString("email");
                    latitude[i]=ja1.getJSONObject(i).getString("latitude");
                    longitude[i]=ja1.getJSONObject(i).getString("longitude");
                    value[i] ="firstname: " + firstname[i]+"lastname: " + lastname[i]+"\nplace: "+place[i]+"\nphone: "+phone[i]+"\nemail: "+email[i]+ "\nlatitude: "+latitude[i] + "\nlongitude: "+longitude[i];

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                l1.setAdapter(ar);
            }
        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        uuids=uuid[position];
        lati=latitude[position];
        logi=longitude[position];



        final CharSequence[] items = {"View Maps", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(mechanic_view_customer.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {


                if (items[item].equals("View Maps"))
                {
                    String url = "http://www.google.com/maps?q=" + lati + "," + logi;
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(in);

                }else if(items[item].equals("Cancel")){
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