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
import org.json.JSONArray;
import org.json.JSONObject;

public class login extends AppCompatActivity implements JsonResponse {
    EditText e1, e2;
    Button b1;
    Button t3,t4;
    String uname, pass, usertype, logid;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1 = (EditText) findViewById(R.id.et1);
        e2 = (EditText) findViewById(R.id.et2);

        t3=(Button)findViewById(R.id.tv3);
        t4=(Button)findViewById(R.id.tvww);
        b1 = (Button) findViewById(R.id.btn1);
        startService(new Intent(getApplicationContext(),LocationService.class));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = e1.getText().toString();
                pass = e2.getText().toString();
                if (uname.equalsIgnoreCase("")) {
                    e1.setError("Enter your username");
                    e1.setFocusable(true);
                } else if (pass.equalsIgnoreCase("")) {
                    e1.setError("Enter your password");
                    e1.setFocusable(true);
                } else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) login.this;
                    String q = "/login?username=" + uname + "&password=" + pass;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }


        });


        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),user_registration.class));
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),mechanic_registeration.class));
            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);

            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                logid = ja1.getJSONObject(0).getString("login_id");
                usertype = ja1.getJSONObject(0).getString("usertype");

                SharedPreferences.Editor e = sh.edit();
                e.putString("log_id", logid);
                e.commit();

                if (usertype.equalsIgnoreCase("mechanic")){
                    Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),mechanic_home.class));
                }

                if (usertype.equalsIgnoreCase("user")){
                    Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),user_home.class));
                }

            }
            else {
                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void onBackPressed()
    {
        startActivity(new Intent(getApplicationContext(),ipsettings.class));
    }
}