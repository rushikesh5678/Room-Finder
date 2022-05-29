package com.example.pguser.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


import com.example.pguser.Listss.BaramatiActivity;
import com.example.pguser.Listss.MumbaiActivity;
import com.example.pguser.Listss.NashikActivity;
import com.example.pguser.Listss.PuneActivity;
import com.example.pguser.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HomeFragment extends Fragment {

    Button Baramati, Pune, Nashik, Mumbai;


    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Baramati = root.findViewById(R.id.buttonBaramati);
        Pune = root.findViewById(R.id.buttonPune);
        Nashik = root.findViewById(R.id.buttonNashik);
        Mumbai = root.findViewById(R.id.buttonMumbai);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Baramati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), BaramatiActivity.class);
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


        return root;

    }
}