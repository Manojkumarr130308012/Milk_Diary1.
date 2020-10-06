package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.rajuuu.milkdiary.Adapter.SelectAllLinesAdapter;
import com.rajuuu.milkdiary.Adapter.SelectAllProductsAdapter;
import com.rajuuu.milkdiary.MainActivity;
import com.rajuuu.milkdiary.Model.AllLinesModel;
import com.rajuuu.milkdiary.Model.AllProductsModel;
import com.rajuuu.milkdiary.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class AllLinesActivity extends AppCompatActivity {

    ArrayList<AllLinesModel> arrayList;
    ListView lv;
    ProgressBar bar;
    Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lines);
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);
        addbtn = findViewById(R.id.addLines);
        bar=(ProgressBar) findViewById(R.id.progressBar2);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AllLinesActivity.this, AddLineActivity.class);
                startActivity(i);
            }
        });

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        String empId = mPreferences.getString(getString(R.string.empid), "");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AllLinesActivity.ReadJSON().execute("https://neophron.in/MilkDiary/viewLine.php");
            }
        });
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =  jsonObject.getJSONArray("alllines");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new AllLinesModel(
                            productObject.getString("lineid"),
                            productObject.getString("linename"),
                            productObject.getString("salesmanname"),
                            productObject.getString("mobilenumber"),
                            productObject.getString("vehiclenumber")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SelectAllLinesAdapter adapter = new SelectAllLinesAdapter(
                    getApplicationContext(), R.layout.allliness_list_layout, arrayList
            );
            lv.setAdapter(adapter);
            bar.setVisibility(View.GONE);
        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            //do whatever you want the 'Back' button to do
            //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
            this.startActivity(new Intent(AllLinesActivity.this, MainActivity.class));
        }
        return true;
    }
}
