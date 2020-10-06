package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.rajuuu.milkdiary.Fragment.HomeFragment;
import com.rajuuu.milkdiary.MainActivity;
import com.rajuuu.milkdiary.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

public class OrderActivity extends AppCompatActivity {

    TextView customerId;
    SearchableSpinner lineSpinner;
    WebView myWebView;
    TextInputEditText dateText;
    ProgressBar bar;
    private DatePickerDialog mDatePickerDialog;
    Button button_Order;

    //An ArrayList for Spinner Items
    private ArrayList<String> custtName;
    //JSON Array
    private JSONArray result1;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        lineSpinner = (SearchableSpinner)findViewById(R.id.lineSpinner);
        customerId = findViewById(R.id.customerId);
        dateText = findViewById(R.id.dateText);
        button_Order = findViewById(R.id.button_Order);
        bar=(ProgressBar) findViewById(R.id.progressBar2);
        bar.setVisibility(View.GONE);

        button_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.setVisibility(View.VISIBLE);
                if (dateText.getText().toString().equals("")) {
                    dateText.requestFocus();
                    bar.setVisibility(View.GONE);
                    dateText.setError("Date required");
                }
                else {
                    getOrder();
                }

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
        setDateTimeField();
        dateText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDatePickerDialog.show();
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
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);

                dateText.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
    private void getCustomers(){
        //Creating a string request
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        String DATA_URL = "https://neophron.in/MilkDiary/viewLine.php";
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
                            result1 = j.getJSONArray("alllines");

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
                custtName.add(json.getString("linename"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        lineSpinner.setAdapter(new ArrayAdapter<String>(OrderActivity.this, android.R.layout.simple_spinner_dropdown_item, custtName));
    }
    //Method to get customer id of a particular position
    private String getCustomerName(int position){
        String price="";
        try {
            //Getting object of given index
            JSONObject json = result1.getJSONObject(position);

            //Fetching name from that object
            price = json.getString("lineid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return price;
    }
    private void getOrder() {

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        String empId2 = mPreferences.getString(getString(R.string.empid), "");

        int sess = 1;
        String defaultLink;
        defaultLink = "https://neophron.in/MilkDiary/order.php?session";
        String custid = customerId.getText().toString();
        String linename = lineSpinner.getSelectedItem().toString();
        linename = linename.replaceAll(" ", "%20");
        String date = dateText.getText().toString();
//        Uri uri = Uri.parse(defaultLink+"="+date+"&orderline="+linename);
        String JsonURL = defaultLink+"="+sess+"&orderdate="+date+"&orderline="+custid;
//        myWebView.loadUrl(uri.toString());
        final String[] data = {""};
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("orderdetails");
                            String msg = obj.getString("orderid");
                            data[0] += msg;

//                                mEditor.putString(getString(R.string.logusr), data[0]);
//                                mEditor.commit();
                            bar.setVisibility(View.GONE);
                                Intent i = new Intent(getApplicationContext(), AddItemActivity.class);
                                i.putExtra("oid", data[0]);
                                startActivity(i);
                            Toast.makeText(OrderActivity.this, "Success...", Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                            bar.setVisibility(View.GONE);
                            Toast.makeText(OrderActivity.this, "Connection failed...", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(obreq);

//        Log.i("orderweburl", uri.toString());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            //do whatever you want the 'Back' button to do
            //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
            this.startActivity(new Intent(OrderActivity.this, MainActivity.class));
        }
        return true;
    }
}
