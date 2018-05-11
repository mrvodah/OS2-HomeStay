package com.example.vietvan.quanly;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by VietVan on 09/05/2018.
 */

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.PhongViewHolder>{

    List<Phong> list;
    Context context;

    public PhongAdapter(Context context, List<Phong> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public PhongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phong, null);

        return new PhongViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongViewHolder holder, final int position) {
        holder.setData(list.get(position));

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getTinhtrang() == 0){

                    Intent intent = new Intent(context, DatPhongActivity.class);
                    intent.putExtra("phong", list.get(position));
                    context.startActivity(intent);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PhongViewHolder extends RecyclerView.ViewHolder{

        TextView ten, thoigianden, thoigiandi,stt;
        CardView cv;
        View v;

        public PhongViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            stt = v.findViewById(R.id.tv_stt);
            ten = v.findViewById(R.id.tv_tenkhach);
            thoigianden = v.findViewById(R.id.tv_thoigianden);
            thoigiandi = v.findViewById(R.id.tv_thoigiandi);
            cv = v.findViewById(R.id.cardview);
        }

        // Set giá trị cho trường
        public void setData(Phong phong){
            stt.setText(phong.getId() + "");
            ten.setText(phong.getTen());
            thoigianden.setText(phong.getThoigianden());
            thoigiandi.setText(phong.getThoigiandi());

            if(phong.getTinhtrang() == 1) {
                cv.setCardBackgroundColor(Color.parseColor("#C01734"));
            }
            else{
                ten.setText("<Trống>");
                cv.setCardBackgroundColor(Color.parseColor("#58B526"));
            }

        }

    }

}
