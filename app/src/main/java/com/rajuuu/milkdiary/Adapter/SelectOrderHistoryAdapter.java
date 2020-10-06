package com.rajuuu.milkdiary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rajuuu.milkdiary.Activity.ViewTableActivity;
import com.rajuuu.milkdiary.Model.AllOrders;
import com.rajuuu.milkdiary.R;

import java.util.ArrayList;

public class SelectOrderHistoryAdapter extends ArrayAdapter<AllOrders> {
    ArrayList<AllOrders> data;
    Context context;
    int resource;

    public SelectOrderHistoryAdapter(Context context, int resource, ArrayList<AllOrders> data) {
        super(context, resource, data);
        this.data = data;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // HashMap<String, String> resultp = new HashMap<String, String>();

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.orderhistory_list_layout, null, true);

        }
        AllOrders product = getItem(position);

        //  ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewProduct);
        //   Picasso.with(context).load(product.getStudent_photo()).into(imageView);

        TextView lName = (TextView) convertView.findViewById(R.id.lName);
        lName.setText(product.getOrderline());

        TextView tol = (TextView) convertView.findViewById(R.id.tol);
        tol.setText(product.getS());

        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText(product.getOrderdate());

        TextView obal = (TextView) convertView.findViewById(R.id.obal);
        obal.setText(product.getOpenbalance());

        TextView cbal = (TextView) convertView.findViewById(R.id.cbal);
        cbal.setText(product.getClosebalance1());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllOrders product = getItem(position);
                Intent i = new Intent(context.getApplicationContext(), ViewTableActivity.class);
                i.putExtra("getOrderline",product.getOrderline() );
                i.putExtra("getOrderdate",product.getOrderdate() );
                i.putExtra("getTotal",product.getTotal() );
                i.putExtra("getOpenbalance",product.getOpenbalance() );
                i.putExtra("getClosebalance",product.getClosebalance() );

                i.putExtra("s",product.getS() );
                i.putExtra("getClosebalance1",product.getClosebalance1() );
                i.putExtra("getRemind_list",product.getRemind_list() );
                i.putExtra("getRemind_list2",product.getRemind_list2() );
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


        return convertView;

    }


}

