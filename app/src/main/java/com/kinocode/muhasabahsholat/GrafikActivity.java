package com.kinocode.muhasabahsholat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class GrafikActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_grafik);
        toolbar.setTitle("GRAFIK EVALUSAI");
        setSupportActionBar(toolbar);
    }
}
