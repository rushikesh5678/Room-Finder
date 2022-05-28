package com.example.pguser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pguser.Model.PGData2;
import com.example.pguser.Model.UserData2;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;

public class UploadActivity2 extends AppCompatActivity {
    ImageView pgImage;
    Uri uri;
    EditText pgname,pgdescription,pgprice,pgaddress;
    TextView adminid,adminphone,adminpg,adminname,pgadslink;
    Spinner cityspin,occupancyspin,pgforspin,foodincluspin,pgtypespin,pgavailablespin;
    CheckBox cwifi,cpb,crclean,cparking,ctv,cparty;
    Button selectimage, uploadPG;
    String imageUrl, city,occupancy,pgfor,foodinclude,pgtypes,pgavailablity;
    String occ2;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    //location
    Button loct;
    String latitude,longitude,address;
    String myloct;
    FusedLocationProviderClient fusedLocationProviderClient;

    PGData2 pgData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload2);
        pgImage = findViewById(R.id.iv_pgImage);

        pgname = findViewById(R.id.txt_pg_name);
        pgdescription = findViewById(R.id.txt_pg_description);
        pgprice = findViewById(R.id.txt_pg_price);
        pgaddress = findViewById(R.id.pgaddress);

        adminid = findViewById(R.id.adminid);
        adminname = findViewById(R.id.adminname);
        adminphone = findViewById(R.id.adminphone);
        adminpg = findViewById(R.id.adminshop);
        pgadslink = findViewById(R.id.adlinks);

        cityspin = findViewById(R.id.pg_city);
        occupancyspin = findViewById(R.id.pg_occupancy);
        pgforspin = findViewById(R.id.pg_for);
        foodincluspin = findViewById(R.id.pg_foodinclude);
        pgtypespin = findViewById(R.id.pg_type);
        pgavailablespin = findViewById(R.id.pg_available);

        cwifi = findViewById(R.id.wifis);
        cparking = findViewById(R.id.parkings);
        ctv = findViewById(R.id.tvs);
        cparty = findViewById(R.id.partys);
        crclean = findViewById(R.id.rooms);
        cpb = findViewById(R.id.powers);

        selectimage = findViewById(R.id.selectImage);
        uploadPG = findViewById(R.id.uploadPGInfo);

        database= FirebaseDatabase.getInstance();
        myRef=database.getReference();

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();

        loct = (Button)findViewById(R.id.btnaddresfetch);

        // Initialize fusedLocationProviderClient

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());

        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Admin").child(firebaseAuth.getCurrentUser().getUid()).child("Profile");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData2 member = dataSnapshot.getValue(UserData2.class);
                adminname.setText(member.getName());
                adminphone.setText(member.getMno());
                adminpg.setText(member.getPgname());
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), "Retrieve Failed !", Toast.LENGTH_SHORT).show();


            }
        });

        occupancyspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                occupancy = value.toString().trim();

                switch (occupancy)
                {
                    case "Single(1)": occ2="1";break;
                    case "Double(2)":occ2="2";break;
                    case "Triple(3)":occ2="3";break;
                    case "Quadruple(4)":occ2="4";break;
                    case "Quintuple(5)":occ2="5";break;
                    case "sextuple(6)":occ2="6";break;
                    case "septuple(7)":occ2="7";break;
                    case "octuple(8)":occ2="8";break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cityspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                city = value.toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pgforspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                pgfor = value.toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        foodincluspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                foodinclude = value.toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pgtypespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                pgtypes = value.toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pgavailablespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                pgavailablity = value.toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                startActivityForResult(photoPicker,1);
            }
        });

        uploadPG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(UploadFood.this, "save btn clicked", Toast.LENGTH_SHORT).show();
                uploadImage();
            }
        });

        // Gmap location fetch

        loct.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                //check permission
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //when permission granted
                    getLocations();
                } else {
                    //when permission denied
                    requestPermissions( new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }


            }

            @SuppressLint("MissingPermission")
            private void getLocations() {

                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        // Initialize Location
                        Location location = task.getResult();
                        if (location != null) {

                            // Initialize Address List
                            try {
                                //Initialize Geocoder
                                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                                // Set  latitude on Textview
                                latitude = String.valueOf(addresses.get(0).getLatitude());
                                Toast.makeText(getApplicationContext(), "Lat:"+latitude, Toast.LENGTH_SHORT).show();

                                // Set  longitude on Textview
                                longitude = String.valueOf(addresses.get(0).getLongitude());
                                Toast.makeText(getApplicationContext(), "Long:"+longitude, Toast.LENGTH_SHORT).show();

                                // Set  address on Textview
                                address = addresses.get(0).getAddressLine(0);
                                pgaddress.setText(address);

                                myloct = "https://www.google.com/maps/search/?api=1&query="+latitude+","+longitude;
                                Toast.makeText(getApplicationContext(), "URL:"+myloct, Toast.LENGTH_SHORT).show();
                                pgadslink.setText(myloct);


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

            uri=data.getData();
            pgImage.setImageURI(uri);

        }
        else Toast.makeText(this, "You have not picked a image", Toast.LENGTH_SHORT).show();

    }

    public void uploadImage(){

        final ProgressDialog progressDialog = new ProgressDialog(UploadActivity2.this);
        progressDialog.setMessage("Uploading PG Info...");
        progressDialog.show();
        StorageReference storageRefrence = FirebaseStorage.getInstance().getReference().child("PGImages").child(uri.getLastPathSegment());
        storageRefrence.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uriImage = uriTask.getResult();
                imageUrl= uriImage.toString();
                Toast.makeText(UploadActivity2.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                uploadPGData();

            }
        });
    }

    public void uploadPGData(){

        final ProgressDialog progressDialog = new ProgressDialog(UploadActivity2.this);
        //progressDialog.setMessage("Uploading Reciepe Info...");
        //progressDialog.show();

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        adminid.setText(userid);

        pgData= new PGData2();

        if (cwifi.isChecked()){
            pgData.setPgwifis("Available");
        }else {
            pgData.setPgwifis("UnAvailable");
        }

        if (cpb.isChecked()){
            pgData.setPgpowers("Available");
        }else {
            pgData.setPgpowers("UnAvailable");
        }

        if (crclean.isChecked()){
            pgData.setPgcleans("Available");
        }else {
            pgData.setPgcleans("UnAvailable");
        }

        if (cparking.isChecked()){
            pgData.setPgparkings("Available");
        }else {
            pgData.setPgparkings("UnAvailable");
        }

        if (ctv.isChecked()){
            pgData.setPgtvs("Available");
        }else {
            pgData.setPgtvs("UnAvailable");
        }

        if (cparty.isChecked()){
            pgData.setPgpartys("Available");
        }else {
            pgData.setPgpartys("UnAvailable");
        }

        pgData.setPgname(pgname.getText().toString().trim());
        pgData.setPgdesc(pgdescription.getText().toString().trim());
        pgData.setPgprice(pgprice.getText().toString().trim());
        pgData.setPgaddress(pgaddress.getText().toString());

        pgData.setAname(adminname.getText().toString());
        pgData.setApg(adminpg.getText().toString());
        pgData.setAphone(adminphone.getText().toString());
        pgData.setAkey(adminid.getText().toString());

        pgData.setPgcity(city);
        pgData.setPgoccupancy(occupancy);
        pgData.setPgfor(pgfor);
        pgData.setPgfoodinclude(foodinclude);
        pgData.setPgavailability(pgavailablity);
        pgData.setRoomtypes(pgtypes);

        pgData.setPgimage(imageUrl);
        pgData.setPgadslink(pgadslink.getText().toString());






        myRef.child("Admin").child(userid).child("City").child(city).child(pgtypes).child(pgname.getText().toString())
                .setValue(pgData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    myRef.child("PGs").child(city).child(pgtypes).child(pgname.getText().toString())
                            .setValue(pgData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(UploadActivity2.this, "Info Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                //finish();
                                Intent intent = new Intent(UploadActivity2.this, MainActivity2.class);
                                startActivity(intent);
                                Toast.makeText(UploadActivity2.this, "Info Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadActivity2.this,"Failed To Upload..", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }

}