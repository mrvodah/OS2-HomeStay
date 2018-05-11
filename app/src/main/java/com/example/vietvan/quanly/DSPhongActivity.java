package com.example.vietvan.quanly;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DSPhongActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    DBManager dbManager;
    List<Phong> list;
    public int time = 0;

    @Override
    protected void onStart() {
        super.onStart();
        init();
        updatelist();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsphong);
        ButterKnife.bind(this);

        dbManager = new DBManager(this);
        list = new ArrayList<>();
        if(list.size() == 0){
            dbManager.text();
            list = dbManager.getAllPhong();
        }

        Log.d(TAG, "onCreate: " + dbManager.getAllPhong());

        recycleview.setLayoutManager(new GridLayoutManager(this, 2));
        recycleview.setAdapter(new PhongAdapter(this, list));

    }

    // Khởi tọa danh sách Phòng
    public void init(){
        dbManager.capnhatDS(dbManager.getAllPhong());
        list = dbManager.getAllPhong();
        recycleview.setAdapter(new PhongAdapter(this, list));
    }

    // Cập nhật lại danh sách
    public void updatelist(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                init();
                Log.d(TAG, "run: " + time++);
                handler.postDelayed(this, 10000);
            }
        });
    }

}
