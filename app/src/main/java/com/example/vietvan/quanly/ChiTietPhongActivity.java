package com.example.vietvan.quanly;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChiTietPhongActivity extends AppCompatActivity {

    @BindView(R.id.tv_p)
    TextView tvP;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.btn_datphong)
    Button btnDatphong;
    Phong phong;
    Integer[] arr = new Integer[]{
            R.drawable.anh1, R.drawable.anh2, R.drawable.anh3, R.drawable.anh4, R.drawable.anh5,
            R.drawable.anh6, R.drawable.anh7, R.drawable.anh8, R.drawable.anh9, R.drawable.anh10,
            R.drawable.anh11, R.drawable.anh12, R.drawable.anh13, R.drawable.anh14, R.drawable.anh15,
            R.drawable.anh16
    };
    List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phong);
        ButterKnife.bind(this);

        list = new ArrayList<>();
        list.addAll(Arrays.asList(arr));

        Collections.shuffle(list);

        phong = (Phong) getIntent().getSerializableExtra("phong");

        tvP.setText("Phòng " + phong.getId());
        iv1.setImageResource(list.get(0));
        iv2.setImageResource(list.get(1));
        iv3.setImageResource(list.get(2));

        loadfont(btnDatphong);
        loadfont(tvP);

    }

    // Load font cho Button
    public void loadfont(Button tv) {
        Typeface type = Typeface.createFromAsset(getAssets(), "UTM BryantLG.ttf");
        tv.setTypeface(type);
    }

    // Load font cho Textview
    public void loadfont(TextView tv) {
        Typeface type = Typeface.createFromAsset(getAssets(), "UTM Amerika Sans.ttf");
        tv.setTypeface(type);
    }

    @OnClick(R.id.btn_datphong)
    public void onViewClicked() {
        Intent intent = new Intent(getApplicationContext(), DatPhongActivity.class);
        intent.putExtra("phong", phong);
        startActivity(intent);

        finish();
    }
}
