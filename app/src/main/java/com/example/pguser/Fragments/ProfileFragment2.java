package com.example.pguser.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pguser.MainActivity2;
import com.example.pguser.Model.UserData2;
import com.example.pguser.R;
import com.example.pguser.UpdateProfile2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment2 extends Fragment {


    View v;
    TextView aemail,aname,amobile,apg;
    FirebaseAuth firebaseAuth;
    CircleImageView profileimgview;
    Button profile;
    ProgressDialog progressDialog;

    FirebaseDatabase firebaseDatabase;
    String imageurl ;

    public ProfileFragment2() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_profile2, container, false);

        aemail=v.findViewById(R.id.txtaemail);
        aname=v.findViewById(R.id.txtaname);
        amobile=v.findViewById(R.id.txt_amobileno);
        apg=v.findViewById(R.id.txt_pgnames);
        profileimgview=v.findViewById(R.id.profile_imageview);
        profile=v.findViewById(R.id.updatepr);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("LOADING ...");

        firebaseAuth= FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Admin").child(firebaseAuth.getCurrentUser().getUid()).child("Profile");

        progressDialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData2 member = dataSnapshot.getValue(UserData2.class);
                aname.setText(member.getName());
                aemail.setText(member.getEmail());
                amobile.setText(member.getMno());
                apg.setText(member.getPgname());
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

                Toast.makeText(getContext(), "Retrieve Failed !", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),UpdateProfile2.class));
            }
        });

        return v;

    }
}