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

public class user_view_recharge_request extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener{
    ListView l1;
    SharedPreferences sh;
    String[] bunk_id,name,amount,date,rid,value,stat;
    public static String bids,rids,amounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_recharge_request);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lv);
        l1.setOnItemClickListener(this);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)user_view_recharge_request.this;
        String q="/user_view_recharge_request?lid="+sh.getString("log_id","");
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
                bunk_id=new String[ja1.length()];
                name = new String[ja1.length()];
                amount = new String[ja1.length()];
                date = new String[ja1.length()];
                stat=new String[ja1.length()];
                value = new String[ja1.length()];

//

                for (int i = 0; i < ja1.length(); i++) {
                    rid[i] = ja1.getJSONObject(i).getString("rrequest_id");
                    bunk_id[i] = ja1.getJSONObject(i).getString("bunk_id");
                    stat[i]= ja1.getJSONObject(i).getString("status");
                    name[i] = ja1.getJSONObject(i).getString("name");
                    amount[i] = ja1.getJSONObject(i).getString("amount");
                    date[i] = ja1.getJSONObject(i).getString("date");

                    value[i] ="Name: "+name[i]+"\nAmount: "+amount[i]+"\nDate: "+date[i]+"\nStatus: " + stat[i];

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        bids = bunk_id[position];
        rids = rid[position];
        amounts=amount[position];

        final CharSequence[] items = {"Review","Payment","Compliant","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(user_view_recharge_request.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {


                if (items[item].equals("Review")) {
                    startActivity(new Intent(getApplicationContext(), user_rate_bunk.class));
                }
                else if (items[item].equals("Payment")) {
                    startActivity(new Intent(getApplicationContext(), user_pay_recharge_amount.class));
                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }else if (items[item].equals("Compliant")) {
                    startActivity(new Intent(getApplicationContext(), user_compliant_bunk.class));

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
