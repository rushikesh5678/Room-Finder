package com.example.pguser;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TermsConditions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        Toolbar toolbar = findViewById(R.id.toolBartermscondition);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Terms & Conditions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}