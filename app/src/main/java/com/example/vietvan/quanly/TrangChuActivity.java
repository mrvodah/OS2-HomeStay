package com.example.vietvan.quanly;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class TrangChuActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    List<Menu> list;
    @BindView(R.id.textView2)
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        ButterKnife.bind(this);

        // Khởi tọa dữ liệu Menu
        list = new ArrayList<>();
        list.add(new Menu(R.drawable.anh1, "Giới thiệu"));
        list.add(new Menu(R.drawable.anh2, "Đặt phòng"));
        list.add(new Menu(R.drawable.anh4, "Liên hệ"));
        list.add(new Menu(R.drawable.anh3, "Thoát"));

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MenuAdapter(list));

        loadfont(textView2);

    }

    // Tạo adapter
    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

        List<Menu> list;

        public MenuAdapter(List<Menu> list) {
            this.list = list;
        }

        @Override
        public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trangchu, null);

            return new MenuViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MenuViewHolder holder, final int position) {
            holder.setData(list.get(position), position);

            holder.v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Kiểm tra đối tượng được click
                    if(position == 0){
                        startActivity(new Intent(TrangChuActivity.this, GioiThieuActivity.class));
                    }
                    else if(position == 1){
                        startActivity(new Intent(TrangChuActivity.this, DSPhongActivity.class));
                    }
                    else if(position == 3){
                        AlertDialog dialog = new AlertDialog.Builder(TrangChuActivity.this)
                                .setMessage("Bạn có chắc chắn muốn thoát?")
                                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MenuViewHolder extends RecyclerView.ViewHolder {

            ImageView iv;
            TextView tv;
            RelativeLayout cs;
            View v;

            public MenuViewHolder(View itemView) {
                super(itemView);
                v = itemView;
                iv = v.findViewById(R.id.iv);
                tv = v.findViewById(R.id.tv);
                cs = v.findViewById(R.id.constraint);

                loadfont(tv);

            }

            // Set dữ liệu cho list
            public void setData(Menu data, int pos) {
                if (pos % 2 == 0) {
                    tv.setTextColor(Color.parseColor("#ffffff"));
                    cs.setBackgroundColor(Color.parseColor("#ff0099cc"));
                } else {
                    cs.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                Picasso.get()
                        .load(data.getAnh())
                        .transform(new CropCircleTransformation())
                        .into(iv);
                tv.setText(data.getVanban());
            }
        }

    }

    // Load font cho Textview
    public void loadfont(TextView tv) {
        Typeface type = Typeface.createFromAsset(getAssets(), "UTM Amerika Sans.ttf");
        tv.setTypeface(type);
    }

}
