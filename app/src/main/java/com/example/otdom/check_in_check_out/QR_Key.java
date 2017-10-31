package com.example.otdom.check_in_check_out;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QR_Key extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr__key);

    // Toolbar
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        toolbarTop.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));

        setSupportActionBar(toolbarTop);
        getSupportActionBar().setTitle("QR Key");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    // Generate qr_code
        image = (ImageView) findViewById(R.id.image);
        getJSON("http://10.10.18.28/MyWebservice/api/getdata.php");

    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//                Log.d("======DATA====", s);
                generateQRData(s);
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    public void generateQRData(String json){
        JSONArray jsonArray = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        String username;
        try {
            jsonArray = new JSONArray(json);
            JSONObject obj = jsonArray.getJSONObject(4); //change to get last string in database
            username = obj.getString("name");
            BitMatrix bitMatrix = multiFormatWriter.encode(username, BarcodeFormat.QR_CODE, 150, 150);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (WriterException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("QR_Key", "Clicked");
        finish();
        return super.onOptionsItemSelected(item);
    }
}
