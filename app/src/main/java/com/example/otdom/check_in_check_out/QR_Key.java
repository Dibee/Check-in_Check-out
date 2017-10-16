package com.example.otdom.check_in_check_out;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class QR_Key extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr__key);


        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        toolbarTop.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));


        setSupportActionBar(toolbarTop);
        getSupportActionBar().setTitle("QR Key");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("QR_Key", "Clicked");
        finish();
        return super.onOptionsItemSelected(item);
    }
}
