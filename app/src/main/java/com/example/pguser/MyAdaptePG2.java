package com.example.pguser;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pguser.Model.PGData2;

import java.util.List;

public class MyAdaptePG2 extends RecyclerView.Adapter<PGViewHolder2> {

    private Context mContext;
    private List<PGData2> myPGList;

    private int lastPosition = -1;

    public MyAdaptePG2(Context mContext, List<PGData2> myPGList) {
        this.mContext = mContext;
        this.myPGList = myPGList;

    }

    @NonNull
    @Override
    public PGViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carddesign2, viewGroup, false);
        return new PGViewHolder2(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final PGViewHolder2 pg, int i) {

        pg.pg_name.setText(myPGList.get(i).getPgname());
        pg.pg_price.setText("Price: " + myPGList.get(i).getPgprice() + "/-Rs");
        pg.pg_desc.setText(myPGList.get(i).getPgdesc());

        pg.pg_city.setText("City: " + myPGList.get(i).getPgcity());
        pg.pg_occupancy.setText("Occupancy: " + myPGList.get(i).getPgoccupancy());
        pg.pg_foodinclude.setText(myPGList.get(i).getPgfoodinclude());
        pg.pg_for.setText("For: " + myPGList.get(i).getPgfor());
        pg.pg_roomtype.setText(myPGList.get(i).getRoomtypes());
        pg.pg_avail.setText(myPGList.get(i).getPgavailability());

        pg.pg_ads.setText(myPGList.get(i).getPgaddress());
        pg.pg_adslink.setText(myPGList.get(i).getPgadslink());

        pg.admin_name.setText(myPGList.get(i).getAname());
        pg.admin_pgname.setText(myPGList.get(i).getApg());
        pg.admin_phone.setText(myPGList.get(i).getAphone());
        pg.admin_key.setText(myPGList.get(i).getAkey());

        pg.pg_wifi.setText(myPGList.get(i).getPgwifis());
        pg.pg_power.setText(myPGList.get(i).getPgpowers());
        pg.pg_parking.setText(myPGList.get(i).getPgparkings());
        pg.pg_party.setText(myPGList.get(i).getPgpartys());
        pg.pg_tv.setText(myPGList.get(i).getPgtvs());
        pg.pg_clean.setText(myPGList.get(i).getPgcleans());

        Glide.with(mContext)
                .load(myPGList.get(i).getPgimage())
                .into(pg.pg_image);

        pg.pg_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(mContext, DetailedActivity2.class);
                intent.putExtra("PGName", myPGList.get(pg.getAdapterPosition()).getPgname());
                intent.putExtra("PGDescription", myPGList.get(pg.getAdapterPosition()).getPgdesc());
                intent.putExtra("PGPrice", myPGList.get(pg.getAdapterPosition()).getPgprice());

                intent.putExtra("PGAdds", myPGList.get(pg.getAdapterPosition()).getPgaddress());
                intent.putExtra("PGAddslink", myPGList.get(pg.getAdapterPosition()).getPgadslink());


                intent.putExtra("PGCity", myPGList.get(pg.getAdapterPosition()).getPgcity());
                intent.putExtra("PGOccupancy", myPGList.get(pg.getAdapterPosition()).getPgoccupancy());
                intent.putExtra("PGFor", myPGList.get(pg.getAdapterPosition()).getPgfor());
                intent.putExtra("PGFoodInclude", myPGList.get(pg.getAdapterPosition()).getPgfoodinclude());
                intent.putExtra("PGRoomtype", myPGList.get(pg.getAdapterPosition()).getRoomtypes());
                intent.putExtra("PGAvailable", myPGList.get(pg.getAdapterPosition()).getPgavailability());

                intent.putExtra("PGAdminName", myPGList.get(pg.getAdapterPosition()).getAname());
                intent.putExtra("PGAdminShopName", myPGList.get(pg.getAdapterPosition()).getApg());
                intent.putExtra("PGAdminPhone", myPGList.get(pg.getAdapterPosition()).getAphone());
                intent.putExtra("PGAdminKey", myPGList.get(pg.getAdapterPosition()).getAkey());

                intent.putExtra("PGImage", myPGList.get(pg.getAdapterPosition()).getPgimage());

                intent.putExtra("PGwifi", myPGList.get(pg.getAdapterPosition()).getPgwifis());
                intent.putExtra("PGpower", myPGList.get(pg.getAdapterPosition()).getPgpowers());
                intent.putExtra("PGclean", myPGList.get(pg.getAdapterPosition()).getPgcleans());
                intent.putExtra("PGtv", myPGList.get(pg.getAdapterPosition()).getPgtvs());
                intent.putExtra("PGparking", myPGList.get(pg.getAdapterPosition()).getPgparkings());
                intent.putExtra("PGparty", myPGList.get(pg.getAdapterPosition()).getPgpartys());

                mContext.startActivity(intent);

            }
        });


        setAnimation(pg.itemView, i);

    }

    private void setAnimation(View viewToAnimate, int position) {

        if (position > lastPosition) {

            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(1000);
            viewToAnimate.setAnimation(animation);
            lastPosition = position;
        }

    }

    @Override
    public int getItemCount() {
        return myPGList.size();
    }
}
