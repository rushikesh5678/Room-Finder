package com.example.pguser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailedActivity extends AppCompatActivity {

    ImageView pgImage;
    TextView pgname,pgdescription,pgprice,pgaddress,pgadslink,pgphone;
    String adminid,adminphone,adminpg,adminname,adslinks;
    TextView wifis,powers,tvs,parkings,partys,cleans;
    TextView city,occupancy,fors,foodavai,pgroom,pgavail;
    Button call, whatsapp, locations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

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

        call = findViewById(R.id.call);
        whatsapp = findViewById(R.id.whatsapp);
        locations = findViewById(R.id.location);

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
            adminphone = mBundle.getString("PGAdminPhone");


            Glide.with(DetailedActivity.this)
                    .load(mBundle.getString("PGImage"))
                    .into(pgImage);

        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent call_intent = new Intent(Intent.ACTION_DIAL);
                    call_intent.setData(Uri.parse("tel:"+adminphone));
                    startActivity(call_intent);
                }catch(Exception e){
                }


            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailedActivity.this, "ph:"+adminphone, Toast.LENGTH_SHORT).show();
                String wpurl="https://wa.me/"+"+91"+adminphone+"?text=Hi,";
                Intent intent = new Intent( Intent.ACTION_VIEW);
                intent.setData(Uri.parse(wpurl));
                startActivity(intent);

            }
        });

        locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adslinks = pgadslink.getText().toString();
                Uri uri = Uri.parse(adslinks);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));

            }
        });

    }
}