package com.example.electric_bike;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class user_rate_sparepart extends AppCompatActivity implements JsonResponse{
    RatingBar ratingBar;
    Button getRating;
    SharedPreferences sh;
    Float rated;

    String rat,review,rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rate_sparepart);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getRating = (Button) findViewById(R.id.getRating);
        ratingBar = (RatingBar) findViewById(R.id.rating);



        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)user_rate_sparepart.this;
        String q = "/user_view_review_sparepart?lid="+sh.getString("log_id","");
        q=q.replace(" ", "%20");
        JR.execute(q);
        getRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating =  ratingBar.getRating();


                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse)user_rate_sparepart.this;
                String q = "/user_rate_sparepart?rating="+rating+"&lid="+sh.getString("log_id","")+ "&rid=" + user_view_oredereditem.oids;
                JR.execute(q);
                Log.d("pearl",q);
            }
        });



    }

    @Override
    public void response(JSONObject jo) {
        try {
            String method=jo.getString("method");
            if(method.equalsIgnoreCase("user_view_review_sparepart"))
            {
                try{
                    Toast.makeText(getApplicationContext(),"helloooooooo", Toast.LENGTH_SHORT).show();
                    String status=jo.getString("status");
                    Log.d("result", status);

                    if(status.equalsIgnoreCase("success")){

                        rat=jo.getString("data");
                        rated=Float.parseFloat(rat);

                        Toast.makeText(getApplicationContext(),rated+"", Toast.LENGTH_SHORT).show();
                        ratingBar.setRating(rated);




                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
                    }

                }catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }else if(method.equalsIgnoreCase("user_rate_sparepart"))
            {
                try {
                    String status=jo.getString("status");
                    if(status.equalsIgnoreCase("success"))
                    {
                        Toast.makeText(getApplicationContext()," Added success", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),user_rate_sparepart.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Rating failed",Toast.LENGTH_LONG ).show();
                        startActivity(new Intent(getApplicationContext(),user_rate_sparepart.class));

                    }
                } catch (Exception e){
                    // TODO: handle exception
                }
            }

        }

        catch(Exception e)
        {
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