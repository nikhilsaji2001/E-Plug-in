package com.example.electric_bike;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class user_manage_profile extends AppCompatActivity implements JsonResponse{
    EditText e1,e2,e3,e4,e5;
    Button b1;
    String firstname,lastname,place,phone,email,latitude,longitude;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_manage_profile);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        startService(new Intent(getApplicationContext(),LocationService.class));
        e1=(EditText)findViewById(R.id.et1);
        e2=(EditText)findViewById(R.id.et2);
        e3=(EditText)findViewById(R.id.et3);
        e4=(EditText)findViewById(R.id.et4);
        e5=(EditText)findViewById(R.id.et5);


        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) user_manage_profile.this;
        String q="/user_view_profile?lid="+sh.getString("log_id","");
        q=q.replace(" ","%20");
        JR.execute(q);
        b1=(Button)findViewById(R.id.btn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstname=e1.getText().toString();
                lastname=e2.getText().toString();
                place=e3.getText().toString();
                phone=e4.getText().toString();
                email=e5.getText().toString();
                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) user_manage_profile.this;
                String q="/user_update_profile?lid="+sh.getString("log_id","")+"&firstname="+firstname+"&lastname="+lastname+"&place="+place+"&phone="+phone+"&email="+email+"&latitude="+LocationService.lati+"&longitude="+LocationService.logi;
                q=q.replace(" ","%20");
                JR.execute(q);
            }
        });
    }

    @Override
    public void response(JSONObject jo) {

        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("user_update_profile")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "Update SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), user_home.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("user_view_profile"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
//                    //feedback_id=new String[ja1.length()];
//                    complaints=new String[ja1.length()];
//                    reply=new String[ja1.length()];
//                    date=new String[ja1.length()];
//                    value=new String[ja1.length()];
//
//                    for(int i = 0;i<ja1.length();i++)
//                    {
                    e1.setText(ja1.getJSONObject(0).getString("firstname"));
                    e2.setText(ja1.getJSONObject(0).getString("lastname"));
                    e3.setText(ja1.getJSONObject(0).getString("place"));
                    e4.setText(ja1.getJSONObject(0).getString("phone"));
                    e5.setText(ja1.getJSONObject(0).getString("email"));
//                        complaints[i]=ja1.getJSONObject(i).getString("complaint");
//                        reply[i]=ja1.getJSONObject(i).getString("reply");
//                        date[i]=ja1.getJSONObject(i).getString("date");
//                        value[i]="complaints: "+complaints[i]+"\nreply: "+reply[i]+"\ndate: "+date[i];

                    }
//                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,value);
//                    l1.setAdapter(ar);
                }


        }

        catch (Exception e) {
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