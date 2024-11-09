package com.example.electric_bike;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class user_view_bunk extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener{
    ListView l1;
    EditText e1;
    String s;
    SharedPreferences sh;
    String[] fname, place,bid,value,latitude,longitude,phone,email;
    public static String bids,lati,logi,ph,em,name,pls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_bunk);
        startService(new Intent(getApplicationContext(),LocationService.class));
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText)findViewById(R.id.et1);
        l1=(ListView)findViewById(R.id.lv);
        l1.setOnItemClickListener(this);
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                s=e1.getText().toString();
                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) user_view_bunk.this;
                String q="/user_view_bunks?p="+s;
                q=q.replace(" ","%20");
                JR.execute(q);
            }
        });
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) user_view_bunk.this;
        String q="/user_view_bunk?longi="+LocationService.logi+"&lati="+LocationService.lati;
        q=q.replace(" ","%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String method=jo.getString("method");
            if(method.equalsIgnoreCase("user_request_bunk")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "SEND SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), user_home.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if (method.equalsIgnoreCase("user_view_bunk")) {
                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    bid = new String[ja1.length()];
                    fname = new String[ja1.length()];

                    place = new String[ja1.length()];

                    latitude = new String[ja1.length()];
                    longitude = new String[ja1.length()];
                    email = new String[ja1.length()];
                    phone = new String[ja1.length()];
                    value = new String[ja1.length()];


                    //

                    for (int i = 0; i < ja1.length(); i++) {
                        bid[i] = ja1.getJSONObject(i).getString("bunk_id");

                        fname[i] = ja1.getJSONObject(i).getString("name");

                        place[i] = ja1.getJSONObject(i).getString("place");
                        phone[i] = ja1.getJSONObject(i).getString("phone");
                        email[i] = ja1.getJSONObject(i).getString("email");

                        latitude[i] = ja1.getJSONObject(i).getString("latitude");
                        longitude[i] = ja1.getJSONObject(i).getString("longitude");
                        value[i] = "Name: " + fname[i] + "\nPlace: " + place[i] ;

                        //

                    }
                    //
                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
//                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.customtext, value);
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
        bids=bid[position];
        lati=latitude[position];
        logi=longitude[position];
        ph=phone[position];
        pls=place[position];
        name=fname[position];
        em=email[position];

        startActivity(new Intent(getApplicationContext(),user_view_bunk_details.class));

//        final CharSequence[] items = {"View Maps","Request" ,"Cancel"};
////
////        AlertDialog.Builder builder = new AlertDialog.Builder(user_view_bunk.this);
////        // builder.setTitle("Add Photo!");
////        builder.setItems(items, new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int item) {
////
////                if (items[item].equals("View Maps"))
////                {
////                    String url = "http://www.google.com/maps?q=" + lati + "," + logi;
////                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
////                    startActivity(in);
////
////                }
////                else if (items[item].equals("Request")) {
////                    startActivity(new Intent(getApplicationContext(),User_send_request.class));
////
////
////                }
////                else if(items[item].equals("Cancel")){
////                    dialog.dismiss();
////
////                }
////
////            }
////
////        });
////        builder.show();
    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),user_home.class);
        startActivity(b);
    }
}