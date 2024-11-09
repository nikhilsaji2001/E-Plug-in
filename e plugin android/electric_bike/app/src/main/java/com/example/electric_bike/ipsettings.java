package com.example.electric_bike;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ipsettings extends AppCompatActivity {
    EditText e1;
    Button b1;
    public static String text;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipsettings);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=(EditText)findViewById(R.id.et);
        e1.setText(sh.getString("ip:","192.168"));
        b1=(Button)findViewById(R.id.btn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                text=e1.getText().toString();
                Toast.makeText(getApplicationContext(),"ip" +text,Toast.LENGTH_LONG).show();
                if(text.equals(""))
                {
                    e1.setText("Enter ip address");
                    e1.setFocusable(true);
                }
                else{
                    SharedPreferences.Editor e=sh.edit();
                    e.putString("ip",text);
                    e.commit();
                    startActivity(new Intent(getApplicationContext(),login.class));

                }
            }
        });


    }

}