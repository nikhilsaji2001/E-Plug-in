package com.example.electric_bike;

import static com.example.electric_bike.user_view_bunk.bids;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User_send_request extends AppCompatActivity implements JsonResponse{
    //onItemselected
    Button b1;
    SharedPreferences sh;
    DatePicker dp;
    TextView dates;
    Spinner veh,typ;
    CharSequence[] my_type_id,type_name;
    String[] veh_id,veh_name;
    String vehid,typeid,date;
    public static String selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_send_request);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1=(Button)findViewById(R.id.buttonssss);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) User_send_request.this;
        String q="/user_request_bunk_select_type?lid="+sh.getString("log_id","")+"&bid=" + bids;
        q=q.replace(" ","%20");
        JR.execute(q);
        dp=(DatePicker) findViewById(R.id.date_picker) ;

        veh=(Spinner)findViewById(R.id.spinnerhjhjhjdd) ;
        typ=(Spinner)findViewById(R.id.spinnerdd) ;
        typ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {

            typeid=my_type_id[i].toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        veh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vehid=veh_id[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int day = dp.getDayOfMonth();
//                int month = dp.getMonth() + 1;
//                int year = dp.getYear();
//
//                date = year + "/" + month + "/" + day;
                 date = String.format("%04d-%02d-%02d", dp.getYear(), dp.getMonth() + 1, dp.getDayOfMonth());

                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) User_send_request.this;
                String q="/user_request_bunk?lid="+sh.getString("log_id","")+"&type="+typeid+"&vehi="+vehid+"&date="+date;
                q=q.replace(" ","%20");
                JR.execute(q);
            }
        });
    }
    @Override
    public void response(JSONObject jo) {
try
{
        String method = jo.getString("method");
    if (method.equalsIgnoreCase("user_request_bunk_select_type"))
    {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);
            JSONArray ja1 = (JSONArray) jo.getJSONArray("typ");
             my_type_id=new CharSequence[ja1.length()];
             type_name=new CharSequence[ja1.length()];
            if (status.equalsIgnoreCase("success"))
            {
                for(int i=0;i<ja1.length();i++)
                {

                    my_type_id[i]=ja1.getJSONObject(i).getString("my_type_id");
                    type_name[i]=ja1.getJSONObject(i).getString("type_name");
                }
            ArrayAdapter<CharSequence> adap=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item,type_name);
            typ.setAdapter(adap);
//
                JSONArray ja2 = (JSONArray) jo.getJSONArray("veh");
                veh_id=new String[ja2.length()];
                veh_name=new String[ja2.length()];
                for (int j=0;j< ja2.length();j++ )
                {
                    veh_id[j]=ja2.getJSONObject(j).getString("vehicle_id");
                    veh_name[j]=ja2.getJSONObject(j).getString("vehicle_name");
                }
                ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,veh_name);
                veh.setAdapter(adapter1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
else if(method.equalsIgnoreCase("user_request_bunk"))
    {

    }
}
catch (Exception e)
{
        // TODO: handle exception
        e.printStackTrace();
        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
}
    }
}