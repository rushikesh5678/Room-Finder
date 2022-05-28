package com.example.pguser.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.pguser.Model.UserData;
import com.example.pguser.R;
import com.example.pguser.UpdateProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {


    View v;
    TextView aemail,aname,amobile;
    FirebaseAuth firebaseAuth;
    CircleImageView profileimgview;
    Button prupdate;

    ProgressDialog progressDialog;

    FirebaseDatabase firebaseDatabase;
    String imageurl ;

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_profile, container, false);

        aemail=v.findViewById(R.id.txtaemail);
        aname=v.findViewById(R.id.txtaname);
        amobile=v.findViewById(R.id.txt_amobileno);
        prupdate = v.findViewById(R.id.prupdate);
        profileimgview=v.findViewById(R.id.profile_imageview);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("LOADING ...");

        firebaseAuth= FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference databaseReference=firebaseDatabase.getReference().child("User").child(firebaseAuth.getCurrentUser().getUid()).child("Profile");

        progressDialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData member = dataSnapshot.getValue(UserData.class);
                aname.setText(member.getName());
                aemail.setText(member.getEmail());
                amobile.setText(member.getMno());
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

                Toast.makeText(getContext(), "Retrieve Failed !", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

        prupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UpdateProfile.class));
            }
        });


        return v;

    }
}