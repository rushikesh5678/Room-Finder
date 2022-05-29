package com.example.pguser.baramatifragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pguser.Listss.PuneActivity;
import com.example.pguser.Model.PGData;
import com.example.pguser.Model.PGData2;
import com.example.pguser.MyAdaptePG;
import com.example.pguser.MyAdaptePG2;
import com.example.pguser.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class BRoomFragment2 extends Fragment {

    RecyclerView mRecyclerView;
    List<PGData2> pgData;

    ProgressDialog progressDialog;

    private ValueEventListener eventListener;

    FirebaseDatabase database;
    DatabaseReference myRef;


    public BRoomFragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_b_room2, container, false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.pgrecyclerView2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("LOADING ..");
        progressDialog.setCanceledOnTouchOutside(false);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database= FirebaseDatabase.getInstance();
        myRef=database.getReference().child("Admin").child(userid).child("City").child("Baramati").child("Room");

        pgData=new ArrayList<>();

        final MyAdaptePG2 pgAdapter = new MyAdaptePG2(getContext(),pgData);
        mRecyclerView.setAdapter(pgAdapter);

        progressDialog.show();

        eventListener = myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                pgData.clear();

                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {

                    PGData2 pgData1 = itemSnapshot.getValue(PGData2.class);
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