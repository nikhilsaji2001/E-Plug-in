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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class user_manage_vehicle extends AppCompatActivity implements JsonResponse {
    Spinner s;
    EditText e1;
    Button b1;
    public static String type, vehicle,vehicleid;
    SharedPreferences sh;
    ListView lvv;
    String[] typ,name,details,vehicle_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manage_vehicle);
        e1 = (EditText) findViewById(R.id.editTextTextPersonNameghjkhj);
        s = (Spinner) findViewById(R.id.spinnersss);
        b1 = (Button) findViewById(R.id.buttonuyy);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lvv = (ListView) findViewById(R.id.lvhjgfrtr);
//        final List<String> list =new ArrayList<String>();
//        list.add("Two Wheeler");
//        list.add("Four Wheeler");

lvv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getApplicationContext(),user_update_vehicle.class));
    }
});

        lvv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        vehicleid=vehicle_id[position];

        final CharSequence[] popup = {"Delete","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(user_manage_vehicle.this);
        builder.setTitle("Select Option!");
        builder.setItems(popup, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (popup[which].equals("Delete"))
                {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) user_manage_vehicle.this;
                    String q = "/user_delete_vehicle?vehicle_id=" + vehicleid;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                    startActivity(new Intent(getApplicationContext(),user_manage_vehicle.class));
                }
//                else if (popup[which].equals("Update"))
//                {
//                    startActivity(new Intent(getApplicationContext(),user_update_vehicle.class));
//                }
                else if (popup[which].equals("Cancel"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
            }
            });
        final String[] str = {"Two Wheeler", "Four Wheeler"};
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, str);
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adap);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) user_manage_vehicle.this;
        String q = "/user_view_vehicle?lid=" + sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type = s.getSelectedItem().toString();
                vehicle = e1.getText().toString();

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) user_manage_vehicle.this;
                String q = "/user_manage_vehicle?lid=" + sh.getString("log_id","") + "&type=" + type + "&vehicle=" + vehicle;
                q = q.replace(" ", "%20");
                JR.execute(q);
            }
        });
    }
    @Override
    public void response(JSONObject jo) {
        try {
            String method = jo.getString("method");


            if (method.equalsIgnoreCase("user_view_vehicle")) {
                try {
                    String status = jo.getString("status");
                    Log.d("pearl", status);


                    if (status.equalsIgnoreCase("success")) {

                        JSONArray ja1 = jo.getJSONArray("data");
                        typ=new String[ja1.length()];
                        name=new String[ja1.length()];
                        vehicle_id=new String[ja1.length()];
                        details=new String[ja1.length()];
                        for (int i=0;i< ja1.length();i++)
                        {
                                 typ[i]=ja1.getJSONObject(i).getString("type");
                                 name[i]=ja1.getJSONObject(i).getString("vehicle_name");
                                 vehicle_id[i]=ja1.getJSONObject(i).getString("vehicle_id");
                                 details[i]="Vehicle Type:  "+typ[i]+"\n Vehicle Name"+name[i];
                        }
                        ArrayAdapter<String> ar= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,details);
                        lvv.setAdapter(ar);
                    } else {
//                startActivity(new Intent(getApplicationContext(),user_manage_vehicle.class));

                        Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            } else if (method.equalsIgnoreCase("user_manage_vehicle")) {
                try {
                    String status = jo.getString("status");
                    Log.d("pearl", status);


                    if (status.equalsIgnoreCase("success"))
                    {

//                        Toast.makeText(getApplicationContext(), "Payment Success", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), user_home.class));

                    } else
                    {
//                      startActivity(new Intent(getApplicationContext(),user_manage_vehicle.class));

                        Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}