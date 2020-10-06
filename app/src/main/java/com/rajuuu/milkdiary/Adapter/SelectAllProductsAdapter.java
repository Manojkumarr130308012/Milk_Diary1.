package com.rajuuu.milkdiary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.rajuuu.milkdiary.Activity.EditProductActivity;
import com.rajuuu.milkdiary.Model.AllProductsModel;
import com.rajuuu.milkdiary.R;

import java.util.ArrayList;

public class SelectAllProductsAdapter extends ArrayAdapter<AllProductsModel> {

    ArrayList<AllProductsModel> data;
    Context context;
    int resource;

    public SelectAllProductsAdapter(Context context, int resource, ArrayList<AllProductsModel> data) {
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
            convertView = layoutInflater.inflate(R.layout.allproducts_list_layout, null, true);

        }
        AllProductsModel product = getItem(position);

        //  ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewProduct);
        //   Picasso.with(context).load(product.getStudent_photo()).into(imageView);

        TextView prodNametxt = (TextView) convertView.findViewById(R.id.prodNametxt);
        prodNametxt.setText(product.getProductname());

        TextView prodratetxt = (TextView) convertView.findViewById(R.id.prodratetxt);
        prodratetxt.setText("₹ "+product.getAmount());

        TextView prodDatetxt = (TextView) convertView.findViewById(R.id.prodDatetxt);
        prodDatetxt.setText(product.getLastupdate());

        ImageView editBtn = (ImageView) convertView.findViewById(R.id.editBtn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllProductsModel product = getItem(position);
                Intent i = new Intent(context.getApplicationContext(), EditProductActivity.class);

                i.putExtra("prodid",product.getProductid() );
                i.putExtra("prodname",product.getProductname() );
                i.putExtra("prodrate",product.getAmount() );

                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


        return convertView;

    }


}

