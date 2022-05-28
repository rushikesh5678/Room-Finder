package com.example.pguser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pguser.Model.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private RelativeLayout rlayout;
    private Animation animation;
    EditText name,email,pass,repass,emno;      //Declaration
    Button save;
    UserData userData;

    //-------------- Firebase ---------------
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    // ------------- Progress Dialog ----------

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);

        firebaseAuth = FirebaseAuth.getInstance();

        storedata();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void storedata() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("REGISTERING ...");

        //Profile Details

        name =(EditText)findViewById(R.id.ename);   //Defination
        email=(EditText)findViewById(R.id.eemail);
        pass=(EditText)findViewById(R.id.epassword);
        repass=(EditText)findViewById(R.id.erepassword);
        emno=(EditText)findViewById(R.id.emn);
        save=(Button)findViewById(R.id.submit);

        // Class Object
        userData =new UserData();

        // Firebase Database Reference

        database= FirebaseDatabase.getInstance();
        myRef=database.getReference();

        // Button Save Functionality

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();

                //Local Variable to store Password

                String password=pass.getText().toString().trim();
                String repassword = repass.getText().toString().trim();
                String gender="";

                // Profile Data Store
                userData.setName(name.getText().toString().trim());
                userData.setEmail(email.getText().toString().trim());
                userData.setMno(emno.getText().toString().trim());


                // Validate Data

                // Full name

                if(TextUtils.isEmpty(userData.name)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Full Name ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }


                // Email
                if(TextUtils.isEmpty(userData.email)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Email ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }


                // password

                if(TextUtils.isEmpty(password)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                // repassword

                if(TextUtils.isEmpty(repassword)){

                    Toast.makeText(RegisterActivity.this, "Please Enter RePassword", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(password.length()<6){

                    Toast.makeText(RegisterActivity.this, "Password Must be more than 6 digit & less than 1 digit", Toast.LENGTH_SHORT).show();
                }


                // Mobile Number

                if(TextUtils.isEmpty(userData.mno)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Mobile Number ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(userData.mno.length()<10){

                    Toast.makeText(RegisterActivity.this, "Mobile no. must be more 10 digit number! Enter Valid number. ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                if(userData.mno.length()>10){

                    Toast.makeText(RegisterActivity.this, "Mobile no. must be less than 10 digit number! Enter Valid number. ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


                // Validation Done !!


                if(password.equals(repassword)){

                    firebaseAuth.createUserWithEmailAndPassword(userData.email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete( Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        String key = firebaseAuth.getCurrentUser().getUid();
                                        userData.setUserKey(key);
                                        myRef.child("User").child(firebaseAuth.getCurrentUser().getUid()).child("Profile").setValue(userData);
                                        progressDialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Registration Done", Toast.LENGTH_SHORT).show();
                                        //startActivity(new Intent(getApplicationContext(), Login.class));
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        finish();


                                    } else {
                                        String msg= task.getException().toString();
                                        Toast.makeText(RegisterActivity.this, "Error:"+msg, Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }

                                    // ...
                                }
                            });

                }

            }
        });

    }

}