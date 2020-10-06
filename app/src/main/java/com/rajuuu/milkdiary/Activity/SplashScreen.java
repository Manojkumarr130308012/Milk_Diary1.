package com.rajuuu.milkdiary.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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


public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DELAY = 0;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    /**
     * Fields
     */
    private ImageView mImageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setBackgroundDrawable(null);

        initializeViews();
        animateLogo();
        goToMainPage();

//        getMyApp();
    }

//    private void getMyApp(){
//        String defaultLink;
//        defaultLink = "https://astrtrack.000webhostapp.com/MyProjects/milkdiary.php?eapp";
//        String usr = "on";
//        String JsonURL = defaultLink + "=" + usr ;
//        RequestQueue requestQueue;
//        requestQueue = Volley.newRequestQueue(this);
//        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONObject obj = response.getJSONObject("myappdata");
//                            String msg = obj.getString("app_id");
//                            String logval = obj.getString("error_app");
//                            String usrname = obj.getString("data_app");
//                            String logid = obj.getString("message");
//
//                            goToMainPage();
//
//                        } catch (JSONException e) {
//                            // If an error occurs, this prints the error to the log
//                            e.printStackTrace();
////                            Toast.makeText(SplashScreen.this, "Error..", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    // Handles errors that occur due to Volley
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("Volley", "Error");
//                    }
//                }
//        );
//        // Adds the JSON object request "obreq" to the request queue
//        requestQueue.add(obreq);
//
//    }

    /**
     * This method initializes the views
     */
    private void initializeViews() {
        mImageViewLogo = findViewById(R.id.image_view_logo);
    }

    /**
     * This method takes user to the main page
     */
    private void goToMainPage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPreferences = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
                boolean hasLoggedIn = mPreferences.getBoolean("hasLoggedIn", false);
                if (hasLoggedIn) {
                    SplashScreen.this.startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    SplashScreen.this.finish();
                }
                else {
                    SplashScreen.this.startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    SplashScreen.this.finish();
                }
            }
        }, SPLASH_DELAY);
    }

    /**
     * This method animates the logo
     */
    private void animateLogo() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_without_duration);
        fadeInAnimation.setDuration(SPLASH_DELAY);

        mImageViewLogo.startAnimation(fadeInAnimation);
    }
}

