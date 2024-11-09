package com.example.electric_bike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class mechanic_upload_servicecharge extends AppCompatActivity implements JsonResponse{
    EditText e1;
    Button b1;
    String amount;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_upload_servicecharge);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText)findViewById(R.id.et1);
        b1=(Button)findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount=e1.getText().toString();
                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse)mechanic_upload_servicecharge.this;
                String q="/mechanic_upload_servicecharge?lid="+sh.getString("log_id","")+"&uid="+mechanic_view_request.uids+"&amount="+amount;
                q=q.replace(" ","%20");
                JR.execute(q);
            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status=jo.getString("status");
            Log.d("pearl",status);


            if(status.equalsIgnoreCase("success")){

                Toast.makeText(getApplicationContext(), "add SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),mechanic_view_request.class));

            }
            else if(status.equalsIgnoreCase("duplicate")){


                startActivity(new Intent(getApplicationContext(),mechanic_upload_servicecharge.class));
                Toast.makeText(getApplicationContext(), "Username already Exist...", Toast.LENGTH_LONG).show();

            }

            else
            {
                startActivity(new Intent(getApplicationContext(),mechanic_upload_servicecharge.class));

                Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
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
        Intent b=new Intent(getApplicationContext(),mechanic_home.class);
        startActivity(b);
    }
}