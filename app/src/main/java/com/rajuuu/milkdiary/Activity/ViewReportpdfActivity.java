package com.rajuuu.milkdiary.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rajuuu.milkdiary.Adapter.PdfCreateAdapter;
import com.rajuuu.milkdiary.Adapter.SelectAllReportAdapter;
import com.rajuuu.milkdiary.BuildConfig;
import com.rajuuu.milkdiary.Model.PDFModel;
import com.rajuuu.milkdiary.R;
import com.rajuuu.milkdiary.utils.PDFCreationUtils;
import com.rajuuu.milkdiary.utils.PdfBitmapCache;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.rajuuu.milkdiary.Activity.ViewReportActivity.loadBitmapFromView;

public class ViewReportpdfActivity extends AppCompatActivity {
    String lineId, lineName, fdate, tdate, alldate;
    private Button btn;
    ArrayList<HashMap<String,String>> arrayList;
    RecyclerView lv;
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
    private boolean IS_MANY_PDF_FILE;

    /**
     * This is identify to number of pdf file. If pdf model list size > sector so we have create many file. After that we have merge all pdf file into one pdf file
     */
    private int SECTOR = 100; // Default value for one pdf file.
    private int START;
    private int END = SECTOR;
    private int NO_OF_PDF_FILE = 1;
    private int NO_OF_FILE;
    private int LIST_SIZE;
    private ProgressDialog progressDialog;
    private TextView btnPdfPath;
    private List<PDFModel> pdfModels;
    private Button btnSharePdfFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reportpdf);
        btn = findViewById(R.id.btn_create_pdf);
        llScroll = findViewById(R.id.llScroll);
        ftdate = findViewById(R.id.ftdate);
        totclosebal = findViewById(R.id.totclosebal);
        btnPdfPath = (TextView) findViewById(R.id.btn_pdf_path);
        btnSharePdfFile = (Button) findViewById(R.id.btn_share_pdf);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Log.d("size", " " + llScroll.getWidth() + "  " + llScroll.getWidth());
                bitmap = loadBitmapFromView(llScroll, llScroll.getWidth(), llScroll.getHeight());
*/
                requestPermission();
            }
        });
        arrayList = new ArrayList<>();
        lv = (RecyclerView) findViewById(R.id.rv_show_demo);
        bar = (ProgressBar) findViewById(R.id.progressBar2);

        Intent i = getIntent();
        lineId = i.getStringExtra("lineid");
        lineName = i.getStringExtra("linename");
        fdate = i.getStringExtra("frdt");
        tdate = i.getStringExtra("todt");
        alldate = fdate + " TO " + tdate;

        ftdate.setText(fdate + " to " + tdate);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ViewReportpdfActivity.ReadJSON().execute("https://neophron.in/MilkDiary/report.php?fdate=" + fdate + "&tdate=" +
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
                PDFCreationUtils.filePath.clear();
                PDFCreationUtils.progressCount = 1;
                pdfModels = new ArrayList<>();
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
                    pdfModels.add(new PDFModel(
                            ""+orderdate,""+orderline,""+openbalance,""+mrngrept,""+evngrept,""+total,""+closebalance,""+Mproduct_name,""+Mtl,""+Mml,""+Msl,""+Mrate,""+Mamount,""+Msession,""+Mproduct_name1,""+Mtl1,""+Mml1,""+Msl1,""+Mrate1,""+Mamount1,""+Msession1,""+Eproduct_name,""+Etl,""+Eml,""+Esl,""+Erate,""+Eamount,""+Esession,""+Eproduct_name1,""+Etl1,""+Eml1,""+Esl1,""+Erate1,""+Eamount1,""+Esession1,""+AMTTOTAL
                    ));

                    arrayList.add(hashMap);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RecyclerView rvShowDemo = (RecyclerView) findViewById(R.id.rv_show_demo);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ViewReportpdfActivity.this);
            rvShowDemo.setLayoutManager(mLayoutManager);
            PdfCreateAdapter pdfRootAdapter = new PdfCreateAdapter();
            pdfRootAdapter.setListData(pdfModels);
            rvShowDemo.setAdapter(pdfRootAdapter);


          /*  String[] from={"orderdate","orderline","openbalance","Mproduct_name","Mtl","Mml","Msl","Mrate","Mamount","Mproduct_name1","Mtl1","Mml1","Msl1","Mrate1","Mamount1","Eproduct_name","Etl","Eml","Esl","Erate","Eamount","Eproduct_name1","Etl1","Eml1","Esl1","Erate1","Eamount1","amttotal","mrngrept","evngrept","total","closebalance"};//string array
            int[] to={R.id.date,R.id.line,R.id.obal,R.id.mBn,R.id.mBnTl,R.id.mBnMl,R.id.mBnSl,R.id.mBnRate,R.id.mBnAmo,R.id.mCn,R.id.mCnTl,R.id.mCnMl,R.id.mCnSl,R.id.mCnRate,R.id.mCnAmo,R.id.eBn,R.id.eBnTl,R.id.eBnMl,R.id.eBnSl,R.id.eBnRate,R.id.eBnAmo,R.id.eCn,R.id.eCnTl,R.id.eCnMl,R.id.eCnSl,R.id.eCnRate,R.id.eCnAmo,R.id.amtTot,R.id.mrngReci,R.id.evngReci,R.id.reciTot,R.id.cbal};//int array of views id's
            SimpleAdapter simpleAdapter=new SimpleAdapter(ViewReportActivity.this,arrayList,R.layout.table_layout_list,from,to);//Create object and set the parameters for simpleAdapter
            lv.setAdapter(simpleAdapter);*/

//            bar.setVisibility(View.GONE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            generatePdfReport();
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
        } else {
            generatePdfReport();
        }
    }


    /**
     * This is manage to all model
     */
    private void generatePdfReport() {


        // NO_OF_FILE : This is identify to one file or many file have to created

        LIST_SIZE = pdfModels.size();
        NO_OF_FILE = LIST_SIZE / SECTOR;
        if (LIST_SIZE % SECTOR != 0) {
            NO_OF_FILE++;
        }
        if (LIST_SIZE > SECTOR) {
            IS_MANY_PDF_FILE = true;
        } else {
            END = LIST_SIZE;
        }
        createPDFFile();
    }

    private void createProgressBarForPDFCreation(int maxProgress) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(String.format(getString(R.string.msg_progress_pdf), String.valueOf(maxProgress)));
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(maxProgress);
        progressDialog.show();
    }

    private void createProgressBarForMergePDF() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_progress_merger_pdf));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * This function call with recursion
     * This recursion depend on number of file (NO_OF_PDF_FILE)
     */
    private void createPDFFile() {

        // Find sub list for per pdf file data
        List<PDFModel> pdfDataList = pdfModels.subList(START, END);
        ;;


        PdfBitmapCache.clearMemory();
        PdfBitmapCache.initBitmapCache(getApplicationContext());
        final PDFCreationUtils pdfCreationUtils = new PDFCreationUtils(ViewReportpdfActivity.this, pdfDataList, LIST_SIZE, NO_OF_PDF_FILE);
        if (NO_OF_PDF_FILE == 1) {
            createProgressBarForPDFCreation(PDFCreationUtils.TOTAL_PROGRESS_BAR);
        }
        pdfCreationUtils.createPDF(new PDFCreationUtils.PDFCallback() {

            @Override
            public void onProgress(final int i) {
                progressDialog.setProgress(i);
            }

            @Override
            public void onCreateEveryPdfFile() {
                // Execute may pdf files and this is depend on NO_OF_FILE
                if (IS_MANY_PDF_FILE) {
                    NO_OF_PDF_FILE++;
                    if (NO_OF_FILE == NO_OF_PDF_FILE - 1) {

                        progressDialog.dismiss();
                        createProgressBarForMergePDF();
                        pdfCreationUtils.downloadAndCombinePDFs();
                    } else {

                        // This is identify to manage sub list of current pdf model list data with START and END

                        START = END;
                        if (LIST_SIZE % SECTOR != 0) {
                            if (NO_OF_FILE == NO_OF_PDF_FILE) {
                                END = (START - SECTOR) + LIST_SIZE % SECTOR;
                            }
                        }
                        END = SECTOR + END;
                        createPDFFile();
                    }

                } else {
                    // Merge one pdf file when all file is downloaded
                    progressDialog.dismiss();

                    createProgressBarForMergePDF();
                    pdfCreationUtils.downloadAndCombinePDFs();
                }

            }

            @Override
            public void onComplete(final String filePath) {
                progressDialog.dismiss();

                if (filePath != null) {
                    btnPdfPath.setVisibility(View.VISIBLE);
                    btnPdfPath.setText("PDF path : " + filePath);
                    Toast.makeText(ViewReportpdfActivity.this, "pdf file " + filePath, Toast.LENGTH_LONG).show();
                    btnSharePdfFile.setVisibility(View.VISIBLE);
                    btnSharePdfFile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sharePdf(filePath);
                        }
                    });

                }
            }

            @Override
            public void onError(Exception e) {
//                Toast.makeText(ViewReportpdfActivity.this, "Error  " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void sharePdf(String fileName) {
        final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        emailIntent.setType("text/plain");
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        ArrayList<Uri> uris = new ArrayList<Uri>();
        File fileIn = new File(fileName);
        Uri u = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, fileIn);

        uris.add(u);
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_to)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, getString(R.string.error_file), Toast.LENGTH_SHORT).show();
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
}
