package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rajuuu.milkdiary.R;

public class EditLineActivity extends AppCompatActivity {

    EditText lineName, salesmanName, mobileNo, vehicleNo;
    Button Linesubmit;
    WebView myWebView;
    String empId, lid, lname,lsalesname,lmob,lvech;
    int option = 1;
    ImageView deleteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_line);

        lineName = findViewById(R.id.lineName);
        salesmanName = findViewById(R.id.salesmanName);
        mobileNo = findViewById(R.id.mobileNo);
        vehicleNo = findViewById(R.id.vehicleNo);
        Linesubmit = findViewById(R.id.Linesubmit);
        deleteBtn = findViewById(R.id.deleteBtn);
        myWebView = (WebView) findViewById(R.id.webView);

        Intent i = getIntent();
        // Get the result of rank
        lname = i.getStringExtra("linename");
        lsalesname = i.getStringExtra("salesmanname");
        lmob = i.getStringExtra("mobile");
        lvech = i.getStringExtra("vehicle");
        lid = i.getStringExtra("lineid");

        lineName.setText(lname);
        salesmanName.setText(lsalesname);
        mobileNo.setText(lvech);
        vehicleNo.setText(lmob);

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

        Linesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(lineName.getText().toString().equals("")) {
                    lineName.requestFocus();
                    lineName.setError("Line name required");
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
                }
            }
        });

    }

    private void getOrder() {

        String defaultLink;
        defaultLink = "https://neophron.in/MilkDiary/editLine.php?lineid";
        String linename = lineName.getText().toString();
        linename = linename.replaceAll(" ", "%20");
        String salesname = salesmanName.getText().toString();
        salesname = salesname.replaceAll(" ", "%20");
        String mob = mobileNo.getText().toString();
        String vech = vehicleNo.getText().toString();
        vech = vech.replaceAll(" ", "%20");

        Uri uri = Uri.parse(defaultLink+"="+lid+"&linename="+linename+"&salesmanname="+salesname+"&mobileno="+mob+"&vehino="+vech+"&option="+option);
        myWebView.loadUrl(uri.toString());

        Log.i("orderweburl", uri.toString());
        Intent intent = new Intent(EditLineActivity.this, AllLinesActivity.class);

        startActivity(intent);

        Toast.makeText(EditLineActivity.this, "success...", Toast.LENGTH_LONG).show();
    }

    public void alertMessage() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // Yes button clicked
//                        Toast.makeText(EditProductActivity.this, "Yes Clicked",
//                                Toast.LENGTH_LONG).show();
                        int deloption = 2;
                        String defaultLink;
                        defaultLink = "https://neophron.in/MilkDiary/editLine.php?lineid";

                        Uri uri = Uri.parse(defaultLink+"="+lid+"&option="+deloption);
                        myWebView.loadUrl(uri.toString());

                        Log.i("orderweburl", uri.toString());
                        Intent intent = new Intent(EditLineActivity.this, AllLinesActivity.class);

                        startActivity(intent);

                        Toast.makeText(EditLineActivity.this, "Line deleted success...", Toast.LENGTH_SHORT).show();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        // do nothing
                        Toast.makeText(EditLineActivity.this, "Cancelled...",
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
            this.startActivity(new Intent(EditLineActivity.this, AllLinesActivity.class));
        }
        return true;
    }

}
