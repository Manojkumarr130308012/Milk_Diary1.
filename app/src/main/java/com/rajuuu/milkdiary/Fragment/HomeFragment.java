package com.rajuuu.milkdiary.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rajuuu.milkdiary.Activity.AddLineActivity;
import com.rajuuu.milkdiary.Activity.AllLinesActivity;
import com.rajuuu.milkdiary.Activity.AllProductsActivity;
import com.rajuuu.milkdiary.Activity.OrderActivity;
import com.rajuuu.milkdiary.Activity.ViewOrderActivity;
import com.rajuuu.milkdiary.R;


public class HomeFragment extends Fragment {
    CardView cardProduct,cardLine, cardOrder, cardViewOrder;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        cardProduct = rootView.findViewById(R.id.cardProduct);
        cardLine = rootView.findViewById(R.id.cardLine);
        cardOrder = rootView.findViewById(R.id.cardOrder);
        cardViewOrder = rootView.findViewById(R.id.cardViewOrder);

        cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AllProductsActivity.class);
                startActivity(i);
            }
        });

        cardLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent line = new Intent(getActivity(), AllLinesActivity.class);
                startActivity(line);
            }
        });

        cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order = new Intent(getActivity(), OrderActivity.class);
                startActivity(order);
            }
        });

        cardViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order = new Intent(getActivity(), ViewOrderActivity.class);
                startActivity(order);
            }
        });

        return rootView;

    }

}