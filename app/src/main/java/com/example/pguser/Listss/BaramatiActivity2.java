package com.example.pguser.Listss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pguser.About_App;
import com.example.pguser.About_Us;
import com.example.pguser.Fragments.HomeFragment;
import com.example.pguser.Fragments.ProfileFragment;
import com.example.pguser.LoginActivity;
import com.example.pguser.MainActivity;
import com.example.pguser.Model.PGData;
import com.example.pguser.MyAdaptePG;
import com.example.pguser.R;
import com.example.pguser.Rating_App;
import com.example.pguser.TermsConditions;
import com.example.pguser.baramatifragments.BFlatFragment2;
import com.example.pguser.baramatifragments.BRoomFragment2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BaramatiActivity2 extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bnav;
    public static Context contextOfApplication;

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    BRoomFragment2 room = new BRoomFragment2();
    BFlatFragment2 flat = new BFlatFragment2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baramati2);
        Toolbar toolbar = findViewById(R.id.toolBarhome);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        bnav = findViewById(R.id.bottomNavigation2);
        contextOfApplication = getApplicationContext();

        bnav.setOnNavigationItemSelectedListener(this);
        bnav.setSelectedItemId(R.id.nav_home);

        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, room).commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_room:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.frag_container, room).commit();
                return true;

            case R.id.nav_flat:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.frag_container, flat).commit();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu,menu);
        return true;
    }

}