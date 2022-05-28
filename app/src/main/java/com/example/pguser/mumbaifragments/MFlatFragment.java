package com.example.pguser.mumbaifragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pguser.Model.PGData;
import com.example.pguser.MyAdaptePG;
import com.example.pguser.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MFlatFragment extends Fragment {

    RecyclerView mRecyclerView;
    List<PGData> pgData;

    ProgressDialog progressDialog;

    private ValueEventListener eventListener;

    FirebaseDatabase database;
    DatabaseReference myRef;

    public MFlatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_m_flat, container, false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.pgrecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("LOADING ..");
        progressDialog.setCanceledOnTouchOutside(false);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database= FirebaseDatabase.getInstance();
        myRef=database.getReference().child("PGs").child("Mumbai").child("Flat");

        pgData=new ArrayList<>();

        final MyAdaptePG pgAdapter = new MyAdaptePG(getContext(),pgData);
        mRecyclerView.setAdapter(pgAdapter);

        progressDialog.show();

        eventListener = myRef.orderByChild("pgavailability").equalTo("Available").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                pgData.clear();

                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {

                    PGData pgData1 = itemSnapshot.getValue(PGData.class);
                    pgData.add(pgData1);

                }

                pgAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

        return v;

    }
}