package com.rajuuu.milkdiary.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rajuuu.milkdiary.MainActivity;
import com.rajuuu.milkdiary.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    EditText eText,party;
    Button signin;
    ProgressBar bar;
    TextView signup, results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        results = findViewById(R.id.result);

        bar=(ProgressBar) findViewById(R.id.progressBar2);
        bar.setVisibility(View.GONE);

        party=(EditText) findViewById(R.id.etPassword);

        eText=(EditText) findViewById(R.id.etUsername);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //mPreferences = getSharedPreferences("tabian.com.sharedpreferencestest", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.setVisibility(View.VISIBLE);
                signin.setOnClickListener(this);
                if (eText.getText().toString().equals("")) {
                    eText.requestFocus();
                    bar.setVisibility(View.GONE);
                    eText.setError("Username required");
                } else if(party.getText().toString().equals("")) {
                    party.requestFocus();
                    bar.setVisibility(View.GONE);
                    party.setError("Password required");
                }
                else {
                    getData2();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }
    private void getData2(){
        String defaultLink;
        defaultLink = "https://neophron.in/MilkDiary/login.php?username";
        String usr = eText.getText().toString();
        String pas = party.getText().toString();
        String JsonURL = defaultLink + "=" + usr + "&password=" + pas ;
        final String[] data = {""};
        final String[] data2 = {""};
        final String[] data3 = {""};
        final String[] data4 = {""};
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("milkuser");
                            String msg = obj.getString("message");
                            String logval = obj.getString("emp_name");
                            String usrname = obj.getString("emp_mobno");
                            String logid = obj.getString("login_id");
                            data[0] += msg;
                            data2[0] += logval;
                            data3[0] += usrname;
                            data4[0] += logid;

                            // Adds the data string to the TextView "results"
                            results.setText(data[0]);

                            if (results.getText().toString().equalsIgnoreCase("Welcome !!")){
                                String name = results.getText().toString();

                                mEditor.putString(getString(R.string.name), name);
                                mEditor.putString(getString(R.string.logusr), data2[0]);
                                mEditor.putString(getString(R.string.usrname), data3[0]);
                                mEditor.putString(getString(R.string.usrid), data4[0]);
                                mEditor.putBoolean("hasLoggedIn", true);
                                mEditor.commit();
                                bar.setVisibility(View.GONE);

                                Toast.makeText(LoginActivity.this, results.getText(), Toast.LENGTH_SHORT).show();
                                results.setTextColor(Color.parseColor("#1b5e20"));
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("empid", data4[0]);
                                i.putExtra("message_key", data2[0]);
                                i.putExtra("message_key", data3[0]);
                                startActivity(i);

                            }
                            else {
                                Toast.makeText(LoginActivity.this, results.getText(), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                            bar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Invalid username and password...", Toast.LENGTH_LONG).show();
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

    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }
}
