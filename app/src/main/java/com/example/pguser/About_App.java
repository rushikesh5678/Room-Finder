package com.example.pguser;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class About_App extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        Toolbar toolbar = findViewById(R.id.toolBaraboutapp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}