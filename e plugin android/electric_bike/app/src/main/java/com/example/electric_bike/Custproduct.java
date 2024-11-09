package com.example.electric_bike;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Custproduct extends ArrayAdapter<String> {

    private Activity context;       //for to get current activity context
    SharedPreferences sh;
    private String[] image;
    private String[] sparename;
    private String[] productname;
    private String[] quantity;
    private String[] amount;


    public Custproduct(Activity context, String[] image, String[] sparename, String[] productname, String[] quantity,String[] amount) {
        //constructor of this class to get the values from main_activity_class

        super(context, R.layout.cust_product, image);
        this.context = context;
        this.image = image;
        this.sparename = sparename;
        this.productname = productname;
        this.quantity=quantity;
        this.amount=amount;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.cust_product, null, true);
        //cust_list_view is xml file of layout created in step no.2

        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView1);
        TextView t1 = (TextView) listViewItem.findViewById(R.id.textView2);

        TextView t2 = (TextView) listViewItem.findViewById(R.id.textView3);
        TextView t3 = (TextView) listViewItem.findViewById(R.id.textView4);
        TextView t4 = (TextView) listViewItem.findViewById(R.id.textView5);

        t1.setText(sparename[position]);
        t2.setText(productname[position]);
        t3.setText(quantity[position]);
        t4.setText(amount[position]);


        sh = PreferenceManager.getDefaultSharedPreferences(getContext());

        String pth = "http://" + sh.getString("ip", "") + "/" + image[position];
        pth = pth.replace("~", "");
	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

        Log.d("-------------", pth);
        Picasso.with(context)
                .load(pth)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(im);

        return listViewItem;
    }

    private TextView setText(String string) {
        // TODO Auto-generated method stub
        return null;
    }
}