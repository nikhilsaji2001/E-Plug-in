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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class user_view_product extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener{
    ListView l1;
    EditText e1;
    String s;
    SharedPreferences sh;
    String[] sparename,productname,image,quantity,amount,pid,spid;
    public static String pids,spids,amounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_product);
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
                JR.json_response=(JsonResponse) user_view_product.this;
                String q="/user_view_products?p="+s;
                q=q.replace(" ","%20");
                JR.execute(q);
            }
        });
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) user_view_product.this;
        String q="/user_view_spareparts?";
        q=q.replace(" ","%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String method=jo.getString("method");
            if(method.equalsIgnoreCase("user_order_product")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "SEND SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), user_home.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }

                else if (method.equalsIgnoreCase("user_view_spareparts")) {
                    String status = jo.getString("status");
                    Log.d("pearl", status);

                    if (status.equalsIgnoreCase("success")) {
                        JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                        pid = new String[ja1.length()];
                        spid = new String[ja1.length()];
                        sparename = new String[ja1.length()];
                        productname = new String[ja1.length()];
                        quantity = new String[ja1.length()];
                        amount = new String[ja1.length()];
                        image = new String[ja1.length()];


                        //

                        for (int i = 0; i < ja1.length(); i++) {
                            pid[i] = ja1.getJSONObject(i).getString("product_id");
                            spid[i] = ja1.getJSONObject(i).getString("sparepart_id");
                            sparename[i] = ja1.getJSONObject(i).getString("firstname");
                            productname[i] = ja1.getJSONObject(i).getString("product_name");
                            quantity[i] = ja1.getJSONObject(i).getString("quantity");
                            amount[i] = ja1.getJSONObject(i).getString("amount");
                            image[i] = ja1.getJSONObject(i).getString("image");
                            //

                        }
                        //
                        Custproduct cc = new Custproduct(this, image, sparename, productname, quantity, amount);
                        l1.setAdapter(cc);
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
        pids=pid[position];
        spids=spid[position];
        amounts=amount[position];
//        lati=latitude[position];
//        logi=longitude[position];



        final CharSequence[] items = {"Order","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(user_view_product.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Order")) {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) user_view_product.this;
                    String q = "/user_order_product?lid=" + sh.getString("log_id", "")+ "&pid=" + pids +"&spid="+spids+"&amount="+amounts;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
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