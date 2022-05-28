package com.example.pguser;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pguser.Model.PGData2;


import java.util.List;

class PGViewHolder2 extends RecyclerView.ViewHolder{

    CardView pg_card;
    TextView pg_name, pg_price, pg_desc, pg_city, pg_occupancy,pg_for,pg_foodinclude,pg_ads,pg_adslink,pg_power,pg_wifi,pg_party,pg_parking,pg_tv,pg_clean,admin_name, admin_pgname, admin_phone, admin_key, pg_avail,pg_roomtype;

    ImageView pg_image;

    public PGViewHolder2 (View itemView){

        super(itemView);

        // Card View

        pg_card = itemView.findViewById(R.id.pgcardview2);
        pg_image = itemView.findViewById(R.id.imvpg);
        pg_name = itemView.findViewById(R.id.cpgname);
        pg_price = itemView.findViewById(R.id.cpgprice);
        pg_desc = itemView.findViewById(R.id.cpgdesc);

        pg_city = itemView.findViewById(R.id.cpgcity);
        pg_occupancy = itemView.findViewById(R.id.cpgoccu);
        pg_foodinclude = itemView.findViewById(R.id.cpgfoodinclude);
        pg_for = itemView.findViewById(R.id.cpgfor);
        pg_avail = itemView.findViewById(R.id.cpgavail);
        pg_roomtype = itemView.findViewById(R.id.cpgroomtypes);

        admin_name = itemView.findViewById(R.id.caname);
        admin_pgname = itemView.findViewById(R.id.capg);
        admin_phone = itemView.findViewById(R.id.caphone);
        admin_key = itemView.findViewById(R.id.cakey);

        pg_ads = itemView.findViewById(R.id.cpgads);
        pg_adslink = itemView.findViewById(R.id.cpgadlink);

        pg_wifi = itemView.findViewById(R.id.cpgwifi);
        pg_power = itemView.findViewById(R.id.cpgpower);
        pg_parking = itemView.findViewById(R.id.cpgparking);
        pg_party = itemView.findViewById(R.id.cpgparty);
        pg_tv = itemView.findViewById(R.id.cpgtvs);
        pg_clean = itemView.findViewById(R.id.cpgclean);




    }

}

