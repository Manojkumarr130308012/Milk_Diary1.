package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rajuuu.milkdiary.Adapter.SelectAllReportAdapter;
import com.rajuuu.milkdiary.Adapter.SelectOrderHistoryAdapter;
import com.rajuuu.milkdiary.Model.AllOrders;
import com.rajuuu.milkdiary.Model.AllReports;
import com.rajuuu.milkdiary.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewReportActivity extends AppCompatActivity {

    String lineId, lineName, fdate, tdate, alldate;
    private Button btn;
    ArrayList<HashMap<String,String>> arrayList;
    ListView lv;
    ProgressBar bar;
    SelectAllReportAdapter adapter;
    TextView ftdate, totclosebal;
    String orderdate,orderline,openbalance, mrngrept,evngrept,total,closebalance;
    String Mproduct_name,Mproduct_name1,Mtl,Mtl1,Mml,Mml1,Msl,Msl1,Mrate,Mrate1,Msession,Msession1;
    Double Mamount=0.0;
            Double Mamount1 = 0.0;
    Double Eamount=0.0;
    Double Eamount1=0.0;
    String Eproduct_name,Eproduct_name1,Etl,Etl1,Eml,Eml1,Esl,Esl1,Erate,Erate1,Esession,Esession1;
    private Bitmap bitmap;
    private LinearLayout llScroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        btn = findViewById(R.id.btn);
        llScroll = findViewById(R.id.llScroll);
        ftdate = findViewById(R.id.ftdate);
        totclosebal = findViewById(R.id.totclosebal);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("size"," "+llScroll.getWidth() +"  "+llScroll.getWidth());
                bitmap = loadBitmapFromView(llScroll, llScroll.getWidth(), llScroll.getHeight());
                createPdf();
            }
        });
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.simpleListView);
        bar=(ProgressBar) findViewById(R.id.progressBar2);

        Intent i = getIntent();
        lineId = i.getStringExtra("lineid");
        lineName = i.getStringExtra("linename");
        fdate = i.getStringExtra("frdt");
        tdate = i.getStringExtra("todt");
        alldate = fdate + " TO " + tdate;

        ftdate.setText(fdate +" to "+tdate);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ViewReportActivity.ReadJSON().execute("https://neophron.in/MilkDiary/report.php?fdate=" + fdate + "&tdate=" +
                        tdate + "&lineid=" + lineId);
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
                JSONArray jsonArray =  jsonObject.getJSONArray("allorders");

                for(int i =0;i<jsonArray.length(); i++){
                    HashMap<String,String> hashMap=new HashMap<>();
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    orderdate = productObject.getString("orderdate");
                    orderline = productObject.getString("orderline");
                    openbalance = productObject.getString("openbalance");
                    mrngrept = productObject.getString("mrngrept");
                    evngrept = productObject.getString("evngrept");
                    total = productObject.getString("total");
                    closebalance = productObject.getString("closebalance");

                    totclosebal.setText(closebalance);

                    JSONArray Morprods = productObject.getJSONArray("morning_data");
                    for(int j = 0; j < Morprods.length();j++){
                        JSONObject innerElem = Morprods.getJSONObject(j);
                                if (j == 0){

                                    Mproduct_name=innerElem.getString("product_name");
                                    Mtl=innerElem.getString("tl");
                                    Mml=innerElem.getString("ml");
                                    Msl=innerElem.getString("sl");
                                    Mrate=innerElem.getString("rate");
                                    Mamount=innerElem.getDouble("amount");
                                    Msession=innerElem.getString("session");
                                }else{
                                    Mproduct_name1=innerElem.getString("product_name");
                                    Mtl1=innerElem.getString("tl");
                                    Mml1=innerElem.getString("ml");
                                    Msl1=innerElem.getString("sl");
                                    Mrate1=innerElem.getString("rate");
                                    Mamount1=innerElem.getDouble("amount");
                                    Msession1=innerElem.getString("session");
                                }
                    }
                    JSONArray Evenprods = productObject.getJSONArray("evening_data");
                    for(int k = 0; k < Evenprods.length();k++){
                        JSONObject innerElem = Evenprods.getJSONObject(k);
                        if (k == 0){
                            Eproduct_name=innerElem.getString("product_name");
                            Etl=innerElem.getString("tl");
                            Eml=innerElem.getString("ml");
                            Esl=innerElem.getString("sl");
                            Erate=innerElem.getString("rate");
                            Eamount=innerElem.getDouble("amount");
                            Esession=innerElem.getString("session");
                        }else{
                            Eproduct_name1=innerElem.getString("product_name");
                            Etl1=innerElem.getString("tl");
                            Eml1=innerElem.getString("ml");
                            Esl1=innerElem.getString("sl");
                            Erate1=innerElem.getString("rate");
                            Eamount1=innerElem.getDouble("amount");
                            Esession1=innerElem.getString("session");
                        }
                    }


                    hashMap.put("orderdate",""+orderdate);
                    hashMap.put("orderline",""+orderline);
                    hashMap.put("openbalance",""+openbalance);
                    hashMap.put("mrngrept",""+mrngrept);
                    hashMap.put("evngrept",""+evngrept);
                    hashMap.put("total",""+total);
                    hashMap.put("closebalance",""+closebalance);
                    hashMap.put("Mproduct_name",""+Mproduct_name);
                    hashMap.put("Mtl",""+Mtl);
                    hashMap.put("Mml",""+Mml);
                    hashMap.put("Msl",""+Msl);
                    hashMap.put("Mrate",""+Mrate);
                    hashMap.put("Mamount",""+Mamount);
                    hashMap.put("Msession",""+Msession);
                    hashMap.put("Mproduct_name1",""+Mproduct_name1);
                    hashMap.put("Mtl1",""+Mtl1);
                    hashMap.put("Mml1",""+Mml1);
                    hashMap.put("Msl1",""+Msl1);
                    hashMap.put("Mrate1",""+Mrate1);
                    hashMap.put("Mamount1",""+Mamount1);
                    hashMap.put("Msession1",""+Msession1);
                    hashMap.put("Eproduct_name",""+Eproduct_name);
                    hashMap.put("Etl",""+Etl);
                    hashMap.put("Eml",""+Eml);
                    hashMap.put("Esl",""+Esl);
                    hashMap.put("Erate",""+Erate);
                    hashMap.put("Eamount",""+Eamount);
                    hashMap.put("Esession",""+Esession);
                    hashMap.put("Eproduct_name1",""+Eproduct_name1);
                    hashMap.put("Etl1",""+Etl1);
                    hashMap.put("Eml1",""+Eml1);
                    hashMap.put("Esl1",""+Esl1);
                    hashMap.put("Erate1",""+Erate1);
                    hashMap.put("Eamount1",""+Eamount1);
                    hashMap.put("Esession1",""+Esession1);
                    Double AMTTOTAL=Mamount+Mamount1+Eamount1+Eamount;
                    hashMap.put("amttotal",""+AMTTOTAL);
                    arrayList.add(hashMap);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String[] from={"orderdate","orderline","openbalance","Mproduct_name","Mtl","Mml","Msl","Mrate","Mamount","Mproduct_name1","Mtl1","Mml1","Msl1","Mrate1","Mamount1","Eproduct_name","Etl","Eml","Esl","Erate","Eamount","Eproduct_name1","Etl1","Eml1","Esl1","Erate1","Eamount1","amttotal","mrngrept","evngrept","total","closebalance"};//string array
            int[] to={R.id.date,R.id.line,R.id.obal,R.id.mBn,R.id.mBnTl,R.id.mBnMl,R.id.mBnSl,R.id.mBnRate,R.id.mBnAmo,R.id.mCn,R.id.mCnTl,R.id.mCnMl,R.id.mCnSl,R.id.mCnRate,R.id.mCnAmo,R.id.eBn,R.id.eBnTl,R.id.eBnMl,R.id.eBnSl,R.id.eBnRate,R.id.eBnAmo,R.id.eCn,R.id.eCnTl,R.id.eCnMl,R.id.eCnSl,R.id.eCnRate,R.id.eCnAmo,R.id.amtTot,R.id.mrngReci,R.id.evngReci,R.id.reciTot,R.id.cbal};//int array of views id's
            SimpleAdapter simpleAdapter=new SimpleAdapter(ViewReportActivity.this,arrayList,R.layout.table_layout_list,from,to);//Create object and set the parameters for simpleAdapter
            lv.setAdapter(simpleAdapter);

//            bar.setVisibility(View.GONE);
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


    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }


    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "pdffromScroll.pdf";
        File directory = new File(this.getFilesDir()+File.separator+"Download");

//        String filePath = "/storage/Download/text.pdf";
        File f = new File(directory, "myText.pdf");


        try {
            document.writeTo(new FileOutputStream(f));
//            FileOutputStream fos = new FileOutputStream(new File(getFilesDir(), "tours"));
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF of Scroll is created!!!", Toast.LENGTH_SHORT).show();

        openGeneratedPDF();

    }

    private void openGeneratedPDF(){


        String filePath = "/storage/Download/text.pdf";
        File f = new File(filePath);

//        File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/fileName.pdf");
        if (f.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(f);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(ViewReportActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }

    }














      /*
        String filePath = getApplicationContext().getFilesDir().getPath().toString() + "/fileName.pdf";
        File f = new File(filePath);
        if (f.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);

            Uri uri = Uri.fromFile(f);

            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(ViewReportActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }*/
    }


