package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rajuuu.milkdiary.MainActivity;
import com.rajuuu.milkdiary.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

public class AddItemActivity extends AppCompatActivity {

    SearchableSpinner lineSpinner, lineSpinner2;
    TextView mrngbtn, evngbtn;
    EditText total, total2, machineliter, machineliter2, mrngrept, evngrept;
    WebView myWebView;
    LinearLayout viewEvenning, viewMorning, viewEvenning2, viewMorning2;
    Button submit, submit2,reciptsubmit,reciptsubmit2, finalbtn;
    String orderid;
    TextView customerId;
    int mrng = 1;
    int evng = 2;

    //An ArrayList for Spinner Items
    private ArrayList<String> custtName;
    //JSON Array
    private JSONArray result1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        lineSpinner = (SearchableSpinner)findViewById(R.id.lineSpinner);
        lineSpinner2 = (SearchableSpinner)findViewById(R.id.lineSpinner2);
        viewEvenning = findViewById(R.id.viewEvenning);
        viewMorning = findViewById(R.id.viewMorning);
        viewEvenning2 = findViewById(R.id.viewEvenning2);
        viewMorning2 = findViewById(R.id.viewMorning2);
        customerId = findViewById(R.id.customerId);
        mrngbtn = findViewById(R.id.mrngbtn);
        evngbtn = findViewById(R.id.evngbtn);
        total = findViewById(R.id.total);
        total2 = findViewById(R.id.total2);
        mrngrept = findViewById(R.id.mrngrept);
        evngrept = findViewById(R.id.evngrept);
        machineliter = findViewById(R.id.machineliter);
        machineliter2 = findViewById(R.id.machineliter2);
        submit = findViewById(R.id.submit);
        submit2 = findViewById(R.id.submit2);
        finalbtn = findViewById(R.id.finalbtn);
        reciptsubmit = findViewById(R.id.reciptsubmit);
        reciptsubmit2 = findViewById(R.id.reciptsubmit2);
        mrngbtn.setTextColor(getResources().getColor(R.color.md_blue_50));
        viewEvenning.setVisibility(View.GONE);
        viewMorning.setVisibility(View.VISIBLE);
        viewEvenning2.setVisibility(View.GONE);
        viewMorning2.setVisibility(View.VISIBLE);

        Intent i = getIntent();
        // Get the result of rank
        orderid = i.getStringExtra("oid");
//        Toast.makeText(AddItemActivity.this, orderid, Toast.LENGTH_SHORT).show();

        mrngbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewMorning.setVisibility(View.VISIBLE);
                viewEvenning.setVisibility(View.GONE);
                viewMorning2.setVisibility(View.VISIBLE);
                viewEvenning2.setVisibility(View.GONE);
                mrngbtn.setTextColor(getResources().getColor(R.color.md_blue_50));
                evngbtn.setTextColor(getResources().getColor(R.color.grey_10));

            }
        });

        evngbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewMorning.setVisibility(View.GONE);
                viewEvenning.setVisibility(View.VISIBLE);
                viewMorning2.setVisibility(View.GONE);
                viewEvenning2.setVisibility(View.VISIBLE);
                evngbtn.setTextColor(getResources().getColor(R.color.md_blue_50));
                mrngbtn.setTextColor(getResources().getColor(R.color.grey_10));;

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (total.getText().toString().equals("")) {
                    total.requestFocus();
                    total.setError("Total required");
                } else if(machineliter.getText().toString().equals("")) {
                    machineliter.requestFocus();
                    machineliter.setError("MachineLiter required");
                } else {
                    getOrder();
                    total.getText().clear();
                    total2.getText().clear();
                    machineliter.getText().clear();
                    machineliter2.getText().clear();
                }

            }
        });

        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (total2.getText().toString().equals("")) {
                    total2.requestFocus();
                    total2.setError("Total required");
                } else if(machineliter2.getText().toString().equals("")) {
                    machineliter2.requestFocus();
                    machineliter2.setError("MachineLiter required");
                } else {
                    getOrder2();
                    total.getText().clear();
                    total2.getText().clear();
                    machineliter.getText().clear();
                    machineliter2.getText().clear();
                }
            }
        });

        reciptsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mrngRecipt();
                mrngrept.getText().clear();
            }
        });

        reciptsubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evngRecipt();
                evngrept.getText().clear();
            }
        });

        finalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalTotal();
            }
        });


        //Initializing the ArrayList
        custtName = new ArrayList<String>();

        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        getCustomers();
        lineSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                customerId.setText(getCustomerName(position));

            }

            @Override
            public void onNothingSelected() {

            }
        });
        lineSpinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                customerId.setText(getCustomerName(position));

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
    private void getCustomers(){
        //Creating a string request
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        String DATA_URL = "https://neophron.in/MilkDiary/viewProducts.php";
//        Toast.makeText(OrderActivity.this, DATA_URL, Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result1 = j.getJSONArray("allproducts");

                            //Calling method getcusomers to get the customer from the JSON Array
                            getCustomerName(result1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getCustomerName(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                custtName.add(json.getString("productname"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        lineSpinner.setAdapter(new ArrayAdapter<String>(AddItemActivity.this, android.R.layout.simple_spinner_dropdown_item, custtName));
        lineSpinner2.setAdapter(new ArrayAdapter<String>(AddItemActivity.this, android.R.layout.simple_spinner_dropdown_item, custtName));

    }
    //Method to get customer id of a particular position
    private String getCustomerName(int position){
        String price="";
        try {
            //Getting object of given index
            JSONObject json = result1.getJSONObject(position);

            //Fetching name from that object
            price = json.getString("productid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return price;
    }
    private void getOrder() {
        String defaultLink;
        defaultLink = "https://neophron.in/MilkDiary/addItem.php?session";
        String prodid = customerId.getText().toString();
        String tliter = total.getText().toString();
        String mliter = machineliter.getText().toString();
        String pname = lineSpinner.getSelectedItem().toString();
        Uri uri = Uri.parse(defaultLink+"="+mrng+"&orderid="+orderid+"&productname="+prodid+"&tliter="+tliter+"&mliter="+mliter);
        myWebView.loadUrl(uri.toString());

        Log.i("orderweburl", uri.toString());

        Toast.makeText(AddItemActivity.this, "success...", Toast.LENGTH_LONG).show();

    }

    private void getOrder2() {

        String defaultLink;
        defaultLink = "https://neophron.in/MilkDiary/addItem.php?session";
        String prodid2 = customerId.getText().toString();
        String tliter2 = total2.getText().toString();
        String mliter2 = machineliter2.getText().toString();
        String pname2 = lineSpinner2.getSelectedItem().toString();
        Uri uri = Uri.parse(defaultLink+"="+evng+"&orderid="+orderid+"&productname="+prodid2+"&tliter="+tliter2+"&mliter="+mliter2);
        myWebView.loadUrl(uri.toString());

        Log.i("orderweburl", uri.toString());

        Toast.makeText(AddItemActivity.this, "success...", Toast.LENGTH_LONG).show();
    }

    private void mrngRecipt() {
        String defaultLink;
        int sessi = 2;
        defaultLink = "https://neophron.in/MilkDiary/order.php?session";
        String mrn = mrngrept.getText().toString();
        Uri uri = Uri.parse(defaultLink+"="+sessi+"&oid="+orderid+"&mrngreceipt="+mrn);
        myWebView.loadUrl(uri.toString());

        Toast.makeText(AddItemActivity.this, "success...", Toast.LENGTH_LONG).show();
    }

    private void evngRecipt() {
        String defaultLink;
        int sess = 3;
        defaultLink = "https://neophron.in/MilkDiary/order.php?session";
        String evn = evngrept.getText().toString();
        Uri uri = Uri.parse(defaultLink+"="+sess+"&oid="+orderid+"&evngreceipt="+evn);
        myWebView.loadUrl(uri.toString());

        Toast.makeText(AddItemActivity.this, "success...", Toast.LENGTH_LONG).show();
    }

    private void finalTotal() {
        String defaultLink;
        defaultLink = "https://neophron.in/MilkDiary/save_order.php?order_id";
        Uri uri = Uri.parse(defaultLink+"="+orderid);
        myWebView.loadUrl(uri.toString());
        Intent i = new Intent(getApplicationContext(), OrderActivity.class);
        startActivity(i);
        Toast.makeText(AddItemActivity.this, "success...", Toast.LENGTH_LONG).show();
    }
}
