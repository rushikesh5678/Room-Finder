package com.example.pguser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pguser.Model.UserData2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class UpdateProfile2 extends AppCompatActivity {
    EditText name,phno,pgname;
    TextView email;
    Button btn;
    FirebaseAuth firebaseAuth;
    CircleImageView profileimgview;

    ProgressDialog progressDialog;

    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile2);
        email= findViewById(R.id.txtaemail);
        name = findViewById(R.id.txtaname);
        phno = findViewById(R.id.txt_amobileno);
        pgname= findViewById(R.id.txt_pgnames);
        profileimgview= findViewById(R.id.profile_imageview);
        btn = findViewById(R.id.submit);

        progressDialog = new ProgressDialog(UpdateProfile2.this);
        progressDialog.setMessage("LOADING ...");

        firebaseAuth= FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Admin").child(firebaseAuth.getCurrentUser().getUid()).child("Profile");

        progressDialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData2 member = dataSnapshot.getValue(UserData2.class);
                name.setText(member.getName());
                email.setText(member.getEmail());
                phno.setText(member.getMno());
                pgname.setText(member.getPgname());
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), "Retrieve Failed !", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference1=firebaseDatabase.getReference().child("Admin").child(firebaseAuth.getCurrentUser().getUid()).child("Profile");
                UserData2 userData = new UserData2();
                userData.setName(name.getText().toString().trim());
                userData.setEmail(email.getText().toString().trim());
                userData.setMno(phno.getText().toString().trim());
                userData.setPgname(pgname.getText().toString().trim());
                databaseReference1.setValue(userData);
                Toast.makeText(UpdateProfile2.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateProfile2.this, MainActivity.class));
            }
        });


    }
}