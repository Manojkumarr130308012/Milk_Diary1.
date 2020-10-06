package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rajuuu.milkdiary.MainActivity;
import com.rajuuu.milkdiary.R;

public class AddLineActivity extends AppCompatActivity {

    EditText lineName, salesmanName,mobileNo,vehicleNo;
    Button Linesubmit;
    WebView myWebView;
    String empId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_line);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        empId = mPreferences.getString(getString(R.string.usrid), "");

//        Toast.makeText(AddLineActivity.this, empId, Toast.LENGTH_SHORT).show();

        lineName = findViewById(R.id.lineName);
        salesmanName = findViewById(R.id.salesmanName);
        mobileNo = findViewById(R.id.mobileNo);
        vehicleNo = findViewById(R.id.vehicleNo);
        Linesubmit = findViewById(R.id.Linesubmit);
        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

        Linesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lineName.getText().toString().equals("")) {
                    lineName.requestFocus();
                    lineName.setError("lineName required");
                }
                else if(salesmanName.getText().toString().equals("")) {
                    salesmanName.requestFocus();
                    salesmanName.setError("salesmanName required");
                }
                else if(mobileNo.getText().toString().equals("")) {
                    mobileNo.requestFocus();
                    mobileNo.setError("mobileNo required");
                }
                else if(vehicleNo.getText().toString().equals("")) {
                    vehicleNo.requestFocus();
                    vehicleNo.setError("vehicleNo required");
                }else {
                    getOrder();
                    lineName.getText().clear();
                    salesmanName.getText().clear();
                    mobileNo.getText().clear();
                    vehicleNo.getText().clear();
                }
            }
        });
    }

    private void getOrder() {

//        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = mPreferences.edit();
//        String empId2 = mPreferences.getString(getString(R.string.empid), "");

        String defaultLink;
        defaultLink = "https://neophron.in/MilkDiary/addLine.php?linename";
        String lname = lineName.getText().toString();
        lname = lname.replaceAll(" ", "%20");

        String snamee = salesmanName.getText().toString();
        snamee = snamee.replaceAll(" ", "%20");

        String mno = mobileNo.getText().toString();

        String vno = vehicleNo.getText().toString();
        vno = vno.replaceAll(" ", "%20");

        Uri uri = Uri.parse(defaultLink+"="+lname+"&salesmanname="+snamee
                +"&mobileno="+mno+"&vehicleno="+vno);
        myWebView.loadUrl(uri.toString());

        Log.i("orderweburl", uri.toString());

        Toast.makeText(AddLineActivity.this, "success...", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            //do whatever you want the 'Back' button to do
            //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
            this.startActivity(new Intent(AddLineActivity.this, AllLinesActivity.class));
        }
        return true;
    }
}
