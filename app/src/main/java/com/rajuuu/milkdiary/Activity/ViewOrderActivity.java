package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.rajuuu.milkdiary.Adapter.SelectOrderHistoryAdapter;
import com.rajuuu.milkdiary.Model.AllLinesModel;
import com.rajuuu.milkdiary.Model.AllOrders;
import com.rajuuu.milkdiary.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewOrderActivity extends AppCompatActivity {

    ArrayList<AllOrders> arrayList;
    ListView lv;
    ProgressBar bar;
   String orderdate,orderline,openbalance, mrngrept,evngrept,total1,closebalance;
    ArrayList<HashMap<String,String>> remind_list,remind_list2;
    Double slaeszero=0.00;Double amtzero=0.00;
    Double amttoatl=0.00;
    Double amttoat2=0.00;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);
        bar=(ProgressBar) findViewById(R.id.progressBar2);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ViewOrderActivity.ReadJSON().execute("https://neophron.in/MilkDiary/viewOrder.php");
            }
        });
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {



            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =  jsonObject.getJSONArray("allorders");

                for(int i =0;i<jsonArray.length(); i++){
                   remind_list=new ArrayList<>();
                    remind_list2=new ArrayList<>();
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    orderdate = productObject.getString("orderdate");
                    orderline = productObject.getString("orderline");
                    openbalance = productObject.getString("openbalance");
                    mrngrept = productObject.getString("mrngrept");
                    evngrept = productObject.getString("evngrept");
                    total1 = productObject.getString("total");
                   closebalance = productObject.getString("closebalance");

                    JSONArray Morprods = productObject.getJSONArray("morning_data");
                   for(int j = 0; j < Morprods.length();j++){
                        JSONObject innerElem = Morprods.getJSONObject(j);
                       HashMap<String,String> remindlist=new HashMap<>();
                        remindlist.put("product_name",innerElem.getString("product_name"));
                       remindlist.put("tl",innerElem.getString("tl"));
                      remindlist.put("ml",innerElem.getString("ml"));
                      Double sales=innerElem.getDouble("sl");
                      Double amt=innerElem.getDouble("amount");

                       if (sales <= 0.00) {
                           remindlist.put("sl",""+slaeszero);
                       }else{
                           remindlist.put("sl",innerElem.getString("sl"));

                       }

                       if (amt <= 0.00) {
                           remindlist.put("amount",""+amtzero);
                           amttoatl=amttoatl+amt;
                       }else{
                           remindlist.put("amount",innerElem.getString("amount"));
                           amttoatl=amttoatl+amt;
                       }

         remindlist.put("rate",innerElem.getString("rate"));
                       remindlist.put("session",innerElem.getString("session"));
          remind_list.add(remindlist);
      }

      JSONArray Evenprods = productObject.getJSONArray("evening_data");
     for(int k = 0; k < Evenprods.length();k++){
            JSONObject innerElem = Evenprods.getJSONObject(k);
          HashMap<String,String> remindlist=new HashMap<>();
                     remindlist.put("product_name",innerElem.getString("product_name"));
                       remindlist.put("tl",innerElem.getString("tl"));
                    remindlist.put("ml",innerElem.getString("ml"));
            Double sales=innerElem.getDouble("sl");
         Double amt=innerElem.getDouble("amount");
            if (sales <= 0.00) {
                remindlist.put("sl",""+slaeszero);
            }else{
                remindlist.put("sl",innerElem.getString("sl"));
            }
         if (amt <= 0.00) {
             remindlist.put("amount",""+amtzero);
             amttoat2=amttoat2+amt;

         }else{
             remindlist.put("amount",innerElem.getString("amount"));
             amttoat2=amttoat2+amt;
         }
                     remindlist.put("rate",innerElem.getString("rate"));

                       remindlist.put("session",innerElem.getString("session"));

                       remind_list2.add(remindlist);
                    }
if(total1.equals("0.00")){

}else{
//    String total= String.valueOf(amttoat2+amttoatl);
    arrayList.add(new AllOrders(
            orderdate,orderline,openbalance,mrngrept,evngrept,total1,closebalance,remind_list,remind_list2
    ));
}


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SelectOrderHistoryAdapter adapter = new SelectOrderHistoryAdapter(
                    getApplicationContext(), R.layout.orderhistory_list_layout, arrayList
            );
            lv.setAdapter(adapter);
            bar.setVisibility(View.GONE);
        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}

