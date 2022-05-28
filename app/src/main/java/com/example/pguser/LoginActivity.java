package com.example.pguser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView tvForget,btRegister;
    private EditText login_email,login_password;
    private Button loginbtn;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btRegister  = findViewById(R.id.btRegister);
        tvForget = findViewById(R.id.tvForgot);

        firebaseAuth = FirebaseAuth.getInstance();

        login_email=(EditText)findViewById(R.id.txtloginemail);
        login_password=(EditText)findViewById(R.id.txtloginpassword);
        loginbtn=(Button)findViewById(R.id.loginbtn);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOGGING IN ...");

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i   = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);

            }
        });

        // Reset Password

        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ResetPassword.class));
            }
        });


        //Applying setOnClick method on button loginbtn

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String email=login_email.getText().toString().trim();
                String password = login_password.getText().toString().trim();


                if(TextUtils.isEmpty(email)){

                    Toast.makeText(LoginActivity.this, "Email Should not be Empty", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(TextUtils.isEmpty(password)){

                    Toast.makeText(LoginActivity.this, "Password Should not be Empty", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(password.length()<6){

                    Toast.makeText(LoginActivity.this, "Short Password,length must be more than 6", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    progressDialog.dismiss();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(LoginActivity.this, "Login Failed or User Not Found", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }

                                // ...
                            }
                        });



            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
          FirebaseUser currentUser = firebaseAuth.getCurrentUser();
          updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            // User is signed in
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out

        }

    }

}