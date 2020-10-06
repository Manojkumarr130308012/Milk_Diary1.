package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
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

public class ReportActivity extends AppCompatActivity {

    TextInputEditText fromdateText, todateText;
    SearchableSpinner custSpinner;
    WebView myWebView;
    TextView customerId;
    private DatePickerDialog mDatePickerDialog, mDatePickerDialog2;
    Button button_Order;

    //An ArrayList for Spinner Items
    private ArrayList<String> custtName;
    //JSON Array
    private JSONArray result1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        custSpinner = (SearchableSpinner)findViewById(R.id.custSpinner);
        fromdateText = findViewById(R.id.fromdateText);
        todateText = findViewById(R.id.todateText);
        customerId = findViewById(R.id.customerId);
        button_Order = findViewById(R.id.button_Order);

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

        button_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fromdateText.getText().toString().equals("")) {
                    fromdateText.requestFocus();
                    fromdateText.setError("From date required");
                } else if(todateText.getText().toString().equals("")) {
                    todateText.requestFocus();
                    todateText.setError("To date required");
                } else {
                    getOrder();
                }
            }
        });

        setDateTimeField();
        fromdateText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDatePickerDialog.show();

                return false;
            }
        });

        todateText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDatePickerDialog2.show();
                return false;
            }
        });

        getCustomers();
        custSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
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

                fromdateText.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        Calendar newCalendar2 = Calendar.getInstance();
        mDatePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                final Date startDate = newDate.getTime();
                String tdate = sd.format(startDate);

                todateText.setText(tdate);
            }
        }, newCalendar2.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
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
        custSpinner.setAdapter(new ArrayAdapter<String>(ReportActivity.this, android.R.layout.simple_spinner_dropdown_item, custtName));
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

        String emptid = customerId.getText().toString();
        String frdate = fromdateText.getText().toString();
        String tdate = todateText.getText().toString();
        String empname = custSpinner.getSelectedItem().toString();

        Intent i = new Intent(getApplicationContext(), ViewReportpdfActivity.class);
        i.putExtra("lineid",emptid );
        i.putExtra("linename",empname);
        i.putExtra("frdt",frdate );
        i.putExtra("todt",tdate);

        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }
}
