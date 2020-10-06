package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.rajuuu.milkdiary.R;

public class RegisterActivity extends AppCompatActivity {

    EditText userName,pass,fullName,mobileNo,address;
    Button btn_signUp;
    WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.userName);
        pass = findViewById(R.id.pass);
        fullName = findViewById(R.id.fullName);
        mobileNo = findViewById(R.id.mobileNo);
        address = findViewById(R.id.address);
        btn_signUp = findViewById(R.id.btn_signUp);

        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userName.getText().toString().equals("")) {
                    userName.requestFocus();
                    userName.setError("Username required");
                } else if(pass.getText().toString().equals("")) {
                    pass.requestFocus();
                    pass.setError("Password required");
                }
                else if(fullName.getText().toString().equals("")) {
                    fullName.requestFocus();
                    fullName.setError("FullName required");
                }
                else if(mobileNo.getText().toString().equals("")) {
                    mobileNo.requestFocus();
                    mobileNo.setError("MobileNo required");
                }
                else if(address.getText().toString().equals("")) {
                    address.requestFocus();
                    address.setError("Address required");
                }
                else if(userName.getText().toString().equals("") && pass.getText().toString().equals("") && fullName.getText().toString().equals("")
                        && mobileNo.getText().toString().equals("") && address.getText().toString().equals("")) {

                } else {
//                    getdata();
                }

            }
        });
    }
}
