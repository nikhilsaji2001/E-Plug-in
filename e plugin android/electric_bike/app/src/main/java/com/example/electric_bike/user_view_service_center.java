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

public class user_view_service_center extends AppCompatActivity implements JsonResponse{
    ListView l1;
    String s;
    SharedPreferences sh;
    String[] fname, place,bid,value,latitude,longitude;
    public static String bids,lati,logi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_service_center);

                sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                l1=(ListView)findViewById(R.id.lva);
                l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                            bids=bid[i];
                            lati=latitude[i];
                            logi=longitude[i];



                            final CharSequence[] items = {"View Maps","Cancel"};

                            AlertDialog.Builder builder = new AlertDialog.Builder(user_view_service_center.this);
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
                                    else if (items[item].equals("Request")) {
                                        startActivity(new Intent(getApplicationContext(),User_send_request.class));


                                    }
                                    else if(items[item].equals("Cancel")){
                                        dialog.dismiss();

                                    }

                                }

                            });
                            builder.show();
                        }


                });
//                e1.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable editable) {
//                        s=e1.getText().toString();
//                        JsonReq JR=new JsonReq();
//                        JR.json_response=(JsonResponse) user_view_service_center.this;
//                        String q="/user_view_bunks?p="+s;
//                        q=q.replace(" ","%20");
//                        JR.execute(q);
//                    }
//                });
                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) user_view_service_center.this;
                String q="/user_view_service_center?longi="+LocationService.logi+"&lati="+LocationService.lati;
                q=q.replace(" ","%20");
                JR.execute(q);
            }

            @Override
            public void response(JSONObject jo) {
                try {
                    String method=jo.getString("method");
                    if(method.equalsIgnoreCase("user_view_servicfrre_center")) {

                        String status = jo.getString("status");
                        Log.d("pearl", status);


                        if (status.equalsIgnoreCase("success")) {


                        } else {

                            Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if (method.equalsIgnoreCase("user_view_service_center")) {
                        String status = jo.getString("status");
                        Log.d("pearl", status);


                        if (status.equalsIgnoreCase("success")) {
                            JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                            bid = new String[ja1.length()];
                            fname = new String[ja1.length()];

                            place = new String[ja1.length()];

                            latitude = new String[ja1.length()];
                            longitude = new String[ja1.length()];
                            value = new String[ja1.length()];


                            //

                            for (int i = 0; i < ja1.length(); i++) {
                                bid[i] = ja1.getJSONObject(i).getString("center_id");

                                fname[i] = ja1.getJSONObject(i).getString("firstname");

                                place[i] = ja1.getJSONObject(i).getString("place");

                                latitude[i] = ja1.getJSONObject(i).getString("latitude");
                                longitude[i] = ja1.getJSONObject(i).getString("longitude");
                                value[i] = "Name: " + fname[i] + "\nPlace: " + place[i] +"\nLatitude: " + latitude[i] + "\nLongitude: "+longitude[i];

                                //

                            }
                            //
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


              public void onBackPressed()
            {
                // TODO Auto-generated method stub
                super.onBackPressed();
                Intent b=new Intent(getApplicationContext(),user_home.class);
                startActivity(b);
            }
        }