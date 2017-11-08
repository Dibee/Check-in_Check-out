package com.example.otdom.check_in_check_out;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    Button gen_btn;
    ImageView image;
    EditText text;
    String number;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    // int val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.text);
        gen_btn = (Button) findViewById(R.id.gen_btn);
        image = (ImageView) findViewById(R.id.image);
        gen_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                number= text.getText().toString().trim();
                Log.v("=============--------", number);
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                editor = sharedPreferences.edit();
                editor.putString("number",number);
                editor.apply();
                Intent intent = new Intent(MainActivity.this, QR_Key.class);
                startActivity(intent);
            }
        });


    }

    public void gen_btn(View view) {
    }
}