package com.example.pguser.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pguser.Listss.BaramatiActivity2;
import com.example.pguser.Listss.MumbaiActivity;
import com.example.pguser.Listss.NashikActivity;
import com.example.pguser.Listss.PuneActivity;
import com.example.pguser.R;
import com.example.pguser.UploadActivity2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.pguser.R;


public class HomeFragment2 extends Fragment {

    FloatingActionButton fb;
    Button Baramati, Pune, Nashik, Mumbai;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public HomeFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home2, container, false);

        Baramati = root.findViewById(R.id.buttonBaramati);
        Pune = root.findViewById(R.id.buttonPune);
        Nashik = root.findViewById(R.id.buttonNashik);
        Mumbai = root.findViewById(R.id.buttonMumbai);
        fb=root.findViewById(R.id.btnadd);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Baramati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), BaramatiActivity2.class);
                getContext().startActivity(intent);
            }
        });

        Pune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PuneActivity.class);
                getContext().startActivity(intent);
            }
        });

        Nashik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NashikActivity.class);
                getContext().startActivity(intent);
            }
        });

        Mumbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MumbaiActivity.class);
                getContext().startActivity(intent);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), UploadActivity2.class);
                startActivity(intent);
            }
        });

        return root;

    }
}