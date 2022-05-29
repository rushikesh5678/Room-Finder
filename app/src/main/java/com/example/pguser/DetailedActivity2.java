package com.example.pguser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pguser.Model.PGData2;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DetailedActivity2 extends AppCompatActivity {

    ImageView pgImage;
    TextView pgname,pgdescription,pgprice,pgaddress,pgadslink,pgphone;
    String adminid,adminphone,adminpg,adminname,adslinks;
    TextView wifis,powers,tvs,parkings,partys,cleans;
    TextView city,occupancy,fors,foodavai,pgroom,pgavail;
    Button gmap, delete,enable,disble;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    PGData2 pgData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed2);

        Toolbar toolbar =findViewById(R.id.detail_toolbar);
        toolbar.setTitle("P.G Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        pgImage =findViewById(R.id.pgimages);

        pgname = findViewById(R.id.dpgname);
        pgdescription = findViewById(R.id.dpgdesc);
        pgprice = findViewById(R.id.dpgprice);
        pgphone = findViewById(R.id.dpgphone);
        pgaddress = findViewById(R.id.dpgadds);
        pgadslink = findViewById(R.id.dpgadlink);

        city = findViewById(R.id.dpgcity);
        occupancy = findViewById(R.id.dpgoccu);
        fors = findViewById(R.id.dpgfor);
        foodavai = findViewById(R.id.dpgfoodinc);
        pgroom = findViewById(R.id.dpgroomtype);
        pgavail = findViewById(R.id.dpgavail);

        wifis = findViewById(R.id.dwifi);
        powers = findViewById(R.id.dpower);
        tvs = findViewById(R.id.dtv);
        parkings = findViewById(R.id.dparking);
        partys = findViewById(R.id.dparty);
        cleans = findViewById(R.id.dclean);

        gmap = findViewById(R.id.location);
        delete = findViewById(R.id.delete);
        enable=findViewById(R.id.enable);
        disble=findViewById(R.id.disable);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null){
            pgname.setText(mBundle.getString("PGName"));
            pgdescription.setText("Desc: "+mBundle.getString("PGDescription"));
            pgprice.setText(mBundle.getString("PGPrice")+"/-Rs");
            pgaddress.setText("Address: "+mBundle.getString("PGAdds"));
            pgadslink.setText(mBundle.getString("PGAddslink"));

            city.setText("City: "+mBundle.getString("PGCity"));
            occupancy.setText("Occupancy: "+mBundle.getString("PGOccupancy"));
            fors.setText("For: "+mBundle.getString("PGFor"));
            foodavai.setText("Food Included: "+mBundle.getString("PGFoodInclude"));
            pgroom.setText("P.G Type: "+mBundle.getString("PGRoomtype"));
            pgavail.setText("P.G Availability: "+mBundle.getString("PGAvailable"));

            wifis.setText(mBundle.getString("PGwifi"));
            powers.setText(mBundle.getString("PGpower"));
            tvs.setText(mBundle.getString("PGtv"));
            parkings.setText(mBundle.getString("PGparking"));
            partys.setText(mBundle.getString("PGparty"));
            cleans.setText(mBundle.getString("PGclean"));

            pgphone.setText("Contact No: "+mBundle.getString("PGAdminPhone"));
            adminid = mBundle.getString("PGAdminKey");
            adminpg = mBundle.getString("PGAdminShopName");
            adminname = mBundle.getString("PGAdminName");
            adminphone = (mBundle.getString("PGAdminPhone"));


            Glide.with(DetailedActivity2.this)
                    .load(mBundle.getString("PGImage"))
                    .into(pgImage);

        }

        enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgData= new PGData2();

                if(pgavail.getText().toString().equals("P.G Availability: Booked")) {
                    pgavail.setText("P.G Availability: Available");
//

                    String city1=city.getText().toString().substring(6);

                    HashMap hm=new HashMap();
                    hm.put("pgavailability","Available");

                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    myRef=FirebaseDatabase.getInstance().getReference();
                    myRef.child("Admin").child(userid).child("City")
                            .child(city1).child(pgroom.getText().toString().substring(10)).child(pgname.getText().toString()).updateChildren(hm)
                            .addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {

                                    myRef.child("PGs")
                                            .child(city1).child(pgroom.getText().toString().substring(10)).child(pgname.getText().toString()).updateChildren(hm)
                                            .addOnSuccessListener(new OnSuccessListener() {
                                                @Override
                                                public void onSuccess(Object o) {

                                                    Toast.makeText(DetailedActivity2.this, "Enabled", Toast.LENGTH_SHORT).show();


                                                }
                                            });
                                }
                            });




                }
                else
                {
                    Toast.makeText(DetailedActivity2.this, "PG is already enabled", Toast.LENGTH_SHORT).show();
                }
            }
        });


        disble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgData= new PGData2();

                if(pgavail.getText().toString().equals("P.G Availability: Available")) {
                    pgavail.setText("P.G Availability: Booked");
//

                    String city1=city.getText().toString().substring(6);

                    HashMap hm=new HashMap();
                    hm.put("pgavailability","Booked");

                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();




                    myRef=FirebaseDatabase.getInstance().getReference();
                    myRef.child("Admin").child(userid).child("City")
                            .child(city1).child(pgroom.getText().toString().substring(10)).child(pgname.getText().toString()).updateChildren(hm)
                            .addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {

                                    myRef.child("PGs")
                                            .child(city1).child(pgroom.getText().toString().substring(10)).child(pgname.getText().toString()).updateChildren(hm)
                                            .addOnSuccessListener(new OnSuccessListener() {
                                                @Override
                                                public void onSuccess(Object o) {

                                                    Toast.makeText(DetailedActivity2.this, "Disabled", Toast.LENGTH_SHORT).show();


                                                }
                                            });
                                }
                            });




                }
                else
                {
                    Toast.makeText(DetailedActivity2.this, "PG is already Disabled", Toast.LENGTH_SHORT).show();
                }



            }
        });

        gmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adslinks = pgadslink.getText().toString();
                Uri uri = Uri.parse(adslinks);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Admin").child(adminid).child("City").child(city.getText().toString()).child(pgname.getText().toString());
                //Toast.makeText(NotesDetailActivity.this, "Date:", Toast.LENGTH_SHORT).show();
                reference.removeValue();

                final DatabaseReference refer= FirebaseDatabase.getInstance().getReference().child("PGs").child(city.getText().toString()).child(pgname.getText().toString());
                //Toast.makeText(NotesDetailActivity.this, "Date:", Toast.LENGTH_SHORT).show();
                refer.removeValue();

                Toast.makeText(DetailedActivity2.this, " Deleted Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }
        });

    }
}