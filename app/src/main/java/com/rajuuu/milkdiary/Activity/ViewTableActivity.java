package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rajuuu.milkdiary.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewTableActivity extends AppCompatActivity {

    String orderdate,orderline,openbalance, mrngrept,evngrept,total,closebalance;
    ArrayList<HashMap<String,String>> remind_list,remind_list2;
    String Mproduct_name,Mproduct_name1,Mtl,Mtl1,Mml,Mml1,Msl,Msl1,Mrate,Mrate1,Msession,Msession1;
    String Eproduct_name,Eproduct_name1,Etl,Etl1,Eml,Eml1,Esl,Esl1,Erate,Erate1,Esession,Esession1;
    ImageView editBtn;

    TextView date,line,obal,mBn,mBnTl,mBnMl,mBnSl,mBnRate,mBnAmo,mCn,mCnTl,mCnMl,mCnSl,mCnRate,mCnAmo,eBn,eBnTl,eBnMl,eBnSl,eBnRate,eBnAmo,
            eCn,eCnTl,eCnMl,eCnSl,eCnRate,eCnAmo,amtTot,mrngReci,evngReci,reciTot,cbal;
    Double antTotal,Mamount,Mamount1,Eamount,Eamount1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_table);

        date = findViewById(R.id.date);
        line = findViewById(R.id.line);
        obal = findViewById(R.id.obal);
        mBn = findViewById(R.id.mBn);
        mBnTl = findViewById(R.id.mBnTl);
        mBnMl = findViewById(R.id.mBnMl);
        mBnSl = findViewById(R.id.mBnSl);
        mBnRate = findViewById(R.id.mBnRate);
        mBnAmo = findViewById(R.id.mBnAmo);
        mCn = findViewById(R.id.mCn);
        mCnTl = findViewById(R.id.mCnTl);
        mCnMl = findViewById(R.id.mCnMl);
        mCnSl = findViewById(R.id.mCnSl);
        mCnRate = findViewById(R.id.mCnRate);
        mCnAmo = findViewById(R.id.mCnAmo);
        eBn = findViewById(R.id.eBn);
        eBnTl = findViewById(R.id.eBnTl);
        eBnMl = findViewById(R.id.eBnMl);
        eBnSl = findViewById(R.id.eBnSl);
        eBnRate = findViewById(R.id.eBnRate);
        eBnAmo = findViewById(R.id.eBnAmo);
        eCn = findViewById(R.id.eCn);
        eCnTl = findViewById(R.id.eCnTl);
        eCnMl = findViewById(R.id.eCnMl);
        eCnSl = findViewById(R.id.eCnSl);
        eCnRate = findViewById(R.id.eCnRate);
        eCnAmo = findViewById(R.id.eCnAmo);
        amtTot = findViewById(R.id.amtTot);
        mrngReci = findViewById(R.id.mrngReci);
        evngReci = findViewById(R.id.evngReci);
        reciTot = findViewById(R.id.reciTot);
        cbal = findViewById(R.id.cbal);
        editBtn = findViewById(R.id.editBtn);

        Mamount = 0.00;
        Mamount1 = 0.00;
        Eamount = 0.00;
        Eamount1 = 0.00;

        remind_list=new ArrayList<>();
        remind_list2=new ArrayList<>();

        Intent i = getIntent();
        orderdate = i.getStringExtra("getOrderline");
        orderline = i.getStringExtra("getOrderdate");
        openbalance = i.getStringExtra("getTotal");
        mrngrept = i.getStringExtra("getOpenbalance");
        evngrept = i.getStringExtra("getClosebalance");
        total = i.getStringExtra("s");
        closebalance = i.getStringExtra("getClosebalance1");
       remind_list = (ArrayList<HashMap<String, String>>) i.getSerializableExtra("getRemind_list");
       remind_list2 = (ArrayList<HashMap<String, String>>) i.getSerializableExtra("getRemind_list2");



       for(int j=0;j<remind_list.size();j++){

           if (j == 0) {
               Mproduct_name=remind_list.get(j).get("product_name");
               Mtl=remind_list.get(j).get("tl");
               Mml=remind_list.get(j).get("ml");
               Msl=remind_list.get(j).get("sl");
               Mrate=remind_list.get(j).get("rate");
               Mamount= Double.valueOf(remind_list.get(j).get("amount"));
               Msession=remind_list.get(j).get("session");
           } else {
               Mproduct_name1=remind_list.get(j).get("product_name");
               Mtl1=remind_list.get(j).get("tl");
               Mml1=remind_list.get(j).get("ml");
              Msl1=remind_list.get(j).get("sl");
               Mrate1=remind_list.get(j).get("rate");
               Mamount1= Double.valueOf(remind_list.get(j).get("amount"));
               Msession1=remind_list.get(j).get("session");
           }

       }

        for(int k=0;k<remind_list2.size();k++){

            if (k == 0) {
                Eproduct_name=remind_list2.get(k).get("product_name");
                Etl=remind_list2.get(k).get("tl");
                Eml=remind_list2.get(k).get("ml");
                Esl=remind_list2.get(k).get("sl");
                Erate=remind_list2.get(k).get("rate");
                Eamount= Double.valueOf(remind_list2.get(k).get("amount"));
                Esession=remind_list2.get(k).get("session");
            } else {
                Eproduct_name1=remind_list2.get(k).get("product_name");
                Etl1=remind_list2.get(k).get("tl");
                Eml1=remind_list2.get(k).get("ml");
                Esl1=remind_list2.get(k).get("sl");
                Erate1=remind_list2.get(k).get("rate");
                Eamount1= Double.valueOf(remind_list2.get(k).get("amount"));
                Esession1=remind_list2.get(k).get("session");
            }

        }

        date.setText(orderline);
        line.setText(orderdate);
        mrngReci.setText(openbalance);
        evngReci.setText(evngrept);
        reciTot.setText(total);
        obal.setText(mrngrept);
        cbal.setText(closebalance);


        mBn.setText(Mproduct_name1);
        mCn.setText(Mproduct_name);
        eBn.setText(Eproduct_name1);
        eCn.setText(Eproduct_name);

        mBnTl.setText(Mtl1);
        mCnTl.setText(Mtl);
        eBnTl.setText(Etl1);
        eCnTl.setText(Etl);

        mBnMl.setText(Mml1);
        mCnMl.setText(Mml);
        eBnMl.setText(Eml1);
        eCnMl.setText(Eml);

        mBnSl.setText(Msl1);
        mCnSl.setText(Msl);
        eBnSl.setText(Esl1);
        eCnSl.setText(Esl);

        mBnRate.setText(Mrate1);
        mCnRate.setText(Mrate);
        eBnRate.setText(Erate1);
        eCnRate.setText(Erate);

        mBnAmo.setText(""+Mamount1);
        mCnAmo.setText(""+Mamount);
        eBnAmo.setText(""+Eamount1);
        eCnAmo.setText(""+Eamount);

        antTotal = (Mamount1 + Mamount + Eamount1 + Eamount);

       amtTot.setText(""+antTotal);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditProductActivity.class);

//                i.putExtra("prodid",product.getProductid() );
//                i.putExtra("prodname",product.getProductname() );
//                i.putExtra("prodrate",product.getAmount() );

                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }
}
