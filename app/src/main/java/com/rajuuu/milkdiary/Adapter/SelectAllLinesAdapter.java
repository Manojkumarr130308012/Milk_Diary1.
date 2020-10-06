package com.rajuuu.milkdiary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rajuuu.milkdiary.Activity.EditLineActivity;
import com.rajuuu.milkdiary.Activity.EditProductActivity;
import com.rajuuu.milkdiary.Model.AllLinesModel;
import com.rajuuu.milkdiary.Model.AllProductsModel;
import com.rajuuu.milkdiary.R;

import java.util.ArrayList;

public class SelectAllLinesAdapter extends ArrayAdapter<AllLinesModel> {

    ArrayList<AllLinesModel> data;
    Context context;
    int resource;

    public SelectAllLinesAdapter(Context context, int resource, ArrayList<AllLinesModel> data) {
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
            convertView = layoutInflater.inflate(R.layout.allliness_list_layout, null, true);

        }
        AllLinesModel product = getItem(position);

        //  ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewProduct);
        //   Picasso.with(context).load(product.getStudent_photo()).into(imageView);

        TextView lineNametxt = (TextView) convertView.findViewById(R.id.lineNametxt);
        lineNametxt.setText(product.getLinename());

        TextView salesmantxt = (TextView) convertView.findViewById(R.id.salesmantxt);
        salesmantxt.setText(product.getSalesmanname());

        TextView mobiletxt = (TextView) convertView.findViewById(R.id.mobiletxt);
        mobiletxt.setText(product.getMobilenumber());

        TextView vehictxt = (TextView) convertView.findViewById(R.id.vehictxt);
        vehictxt.setText(product.getVehiclenumber());

        ImageView editBtn = (ImageView) convertView.findViewById(R.id.editBtn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllLinesModel product = getItem(position);
                Intent i = new Intent(context.getApplicationContext(), EditLineActivity.class);

                i.putExtra("linename",product.getLinename() );
                i.putExtra("salesmanname",product.getSalesmanname() );
                i.putExtra("mobile",product.getVehiclenumber() );
                i.putExtra("vehicle",product.getMobilenumber() );
                i.putExtra("lineid",product.getLineid() );

                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });





        return convertView;

    }


}


