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
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rajuuu.milkdiary.MainActivity;
import com.rajuuu.milkdiary.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddProductActivity extends AppCompatActivity {
    EditText prodName, rate;
    Button button_Order;
    WebView myWebView;
    String empId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        empId = mPreferences.getString(getString(R.string.usrid), "");

//        Toast.makeText(AddProductActivity.this, empId, Toast.LENGTH_SHORT).show();


        prodName = findViewById(R.id.prodName);
        rate = findViewById(R.id.rate);
        button_Order = findViewById(R.id.button_Order);
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

                if(prodName.getText().toString().equals("")) {
                    prodName.requestFocus();
                    prodName.setError("Product required");
                }
                else if(rate.getText().toString().equals("")) {
                    rate.requestFocus();
                    rate.setError("Amount required");
                }else {
                    getOrder();
                    prodName.getText().clear();
                    rate.getText().clear();
                }
            }
        });

    }
    private void getOrder() {

//        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = mPreferences.edit();
//        String empId2 = mPreferences.getString(getString(R.string.empid), "");

        String defaultLink;
        defaultLink = "https://neophron.in/MilkDiary/addProduct.php?prodname";
        String prodname = prodName.getText().toString();
        prodname = prodname.replaceAll(" ", "%20");
        String orrate = rate.getText().toString();
        Uri uri = Uri.parse(defaultLink+"="+prodname+"&prodprice="+orrate);
        myWebView.loadUrl(uri.toString());

        Log.i("orderweburl", uri.toString());

        Toast.makeText(AddProductActivity.this, "success...", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            //do whatever you want the 'Back' button to do
            //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
            this.startActivity(new Intent(AddProductActivity.this, AllProductsActivity.class));
        }
        return true;
    }
}
