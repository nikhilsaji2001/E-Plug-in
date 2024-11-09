package com.example.electric_bike;

import static com.example.electric_bike.user_view_mechanic_request.amnt;

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

public class user_pay_mechanic extends AppCompatActivity implements JsonResponse{
    EditText e1,e2,e3,e4;
    Button b1;
    String holder,cvv,expdate,amount;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pay_mechanic);

                sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                e1=(EditText)findViewById(R.id.et11);
                e2=(EditText)findViewById(R.id.et21);
                e3=(EditText)findViewById(R.id.et31);
                e4=(EditText)findViewById(R.id.et41);
                e4.setText(user_view_mechanic_request.amnt);
                e4.setEnabled(false);
                b1=(Button)findViewById(R.id.btn11);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder=e1.getText().toString();
                        cvv=e2.getText().toString();
                        expdate=e3.getText().toString();
                        amount=e4.getText().toString();
//
                        JsonReq JR=new JsonReq();
                        JR.json_response=(JsonResponse) user_pay_mechanic.this;
                        String q="/user_pay_mechanic?lid="+sh.getString("log_id","")+"&amount="+amnt+"&rid="+user_view_mechanic_request.rids;
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

                        Toast.makeText(getApplicationContext(), "Payment Success", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),user_view_mechanic_request.class));

                    }
                    else if(status.equalsIgnoreCase("duplicate")){


                        startActivity(new Intent(getApplicationContext(), user_home.class));
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();

                    }

                    else
                    {
                        startActivity(new Intent(getApplicationContext(),user_home.class));

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
                Intent b=new Intent(getApplicationContext(),user_home.class);
                startActivity(b);
            }
        }



