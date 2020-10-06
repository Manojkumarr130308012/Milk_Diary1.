package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.rajuuu.milkdiary.MainActivity;
import com.rajuuu.milkdiary.R;

public class EditProductActivity extends AppCompatActivity {

    EditText prodName, rate;
    Button button_Order;
    WebView myWebView;
    String empId, pid, pname,prate;
    int option = 1;
    ImageView deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        empId = mPreferences.getString(getString(R.string.usrid), "");

//        Toast.makeText(AddProductActivity.this, empId, Toast.LENGTH_SHORT).show();


        prodName = findViewById(R.id.prodName);
        rate = findViewById(R.id.rate);
        button_Order = findViewById(R.id.button_Order);
        deleteBtn = findViewById(R.id.deleteBtn);
        myWebView = (WebView) findViewById(R.id.webView);

        Intent i = getIntent();
        // Get the result of rank
        pname = i.getStringExtra("prodname");
        prate = i.getStringExtra("prodrate");
        pid = i.getStringExtra("prodid");

        prodName.setText(pname);
        rate.setText(prate);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertMessage();
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
        defaultLink = "https://neophron.in/MilkDiary/editProduct.php?prodid";
        String prodname = prodName.getText().toString();
        prodname = prodname.replaceAll(" ", "%20");
        String orrate = rate.getText().toString();

        Uri uri = Uri.parse(defaultLink+"="+pid+"&prodname="+prodname+"&prodprice="+orrate+"&option="+option);
        myWebView.loadUrl(uri.toString());

        Log.i("orderweburl", uri.toString());
        Intent intent = new Intent(EditProductActivity.this, AllProductsActivity.class);

        startActivity(intent);

        Toast.makeText(EditProductActivity.this, "success...", Toast.LENGTH_LONG).show();
    }

    public void alertMessage() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // Yes button clicked
//                        Toast.makeText(EditProductActivity.this, "Yes Clicked",
//                                Toast.LENGTH_LONG).show();
                        int option = 2;
                        String defaultLink;
                        defaultLink = "https://neophron.in/MilkDiary/editProduct.php?prodid";

                        Uri uri = Uri.parse(defaultLink+"="+pid+"&option="+option);
                        myWebView.loadUrl(uri.toString());

                        Log.i("orderweburl", uri.toString());
                        Intent intent = new Intent(EditProductActivity.this, AllProductsActivity.class);

                        startActivity(intent);

                        Toast.makeText(EditProductActivity.this, "Product deleted success...", Toast.LENGTH_SHORT).show();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        // do nothing
                        Toast.makeText(EditProductActivity.this, "Cancelled...",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            //do whatever you want the 'Back' button to do
            //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
            this.startActivity(new Intent(EditProductActivity.this, AllProductsActivity.class));
        }
        return true;
    }

}
