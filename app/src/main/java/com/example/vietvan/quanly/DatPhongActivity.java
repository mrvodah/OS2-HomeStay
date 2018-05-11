package com.example.vietvan.quanly;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatPhongActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    @BindView(R.id.ed_ten)
    EditText edTen;
    @BindView(R.id.ed_sdt)
    EditText edSdt;
    @BindView(R.id.btn_ngayden)
    Button btnNgayden;
    @BindView(R.id.sw)
    Switch sw;
    @BindView(R.id.btn_datphong)
    Button btnDatphong;
    @BindView(R.id.ed_cmnd)
    EditText edCmnd;
    @BindView(R.id.ed_ns)
    EditText edNs;
    @BindView(R.id.btn_ngaydi)
    Button btnNgaydi;
    @BindView(R.id.btn_huy)
    Button btnHuy;

    Phong phong;
    Date dateden, datedi, date;
    public boolean is = false;
    DBManager dbManager;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_phong);
        ButterKnife.bind(this);

        dbManager = new DBManager(this);
        phong = (Phong) getIntent().getSerializableExtra("phong");
        dateden = new Date();
        datedi = new Date();
        date = new Date();
        // Kiểm tra xem phong có dữ liệu không?
        if (phong.getTinhtrang() == 0) {
            //btnNgay.setText(getFormatTime(date));
        } else {
            dateden = phong.getDateformat(phong.getThoigianden());
            datedi = phong.getDateformat(phong.getThoigiandi());
            edTen.setText(phong.getTen());
            edSdt.setText(phong.getSdt());
            btnNgayden.setText(getFormatTime(dateden));
            btnNgaydi.setText(getFormatTime(datedi));
        }
        Log.d(TAG, "onCreate: " + phong);

        // Sự kiện click của switch
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    sw.setText("Chọn Giờ");
                else
                    sw.setText("Chọn Ngày");

                is = isChecked;
            }
        });

        // Sự kiện click của edittext NgaySinh
        edNs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DatPhongActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setYear(i - 1900);
                        date.setMonth(i1);
                        date.setDate(i2);
                        edNs.setText(getFormatTime(date));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        // Load font
        loadfont(btnNgayden);
        loadfont(btnNgaydi);
        loadfont(btnHuy);
        loadfont(btnDatphong);
        loadfonttv(textView);
    }

    // Load font cho Button
    public void loadfont(Button tv) {
        Typeface type = Typeface.createFromAsset(getAssets(), "UTM BryantLG.ttf");
        tv.setTypeface(type);
    }

    // Load font cho Textview
    public void loadfonttv(TextView tv) {
        Typeface type = Typeface.createFromAsset(getAssets(), "UTM Amerika Sans.ttf");
        tv.setTypeface(type);
    }

    // Hàm lấy định dạng ngày: MMM dd yyy, HH:mm
    public String getFormatTime(Date date) {
        SimpleDateFormat simple = new SimpleDateFormat("MMM dd yyy, HH:mm");
        return simple.format(date);
    }

    @OnClick({R.id.btn_ngayden, R.id.btn_datphong, R.id.btn_huy, R.id.btn_ngaydi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // Bat sự kiện click của button NgayDen
            case R.id.btn_ngayden:
                if (!is) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateden);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            dateden.setYear(i - 1900);
                            dateden.setMonth(i1);
                            dateden.setDate(i2);
                            btnNgayden.setText(getFormatTime(dateden));
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.show();
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateden);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            dateden.setHours(i);
                            dateden.setMinutes(i1);
                            btnNgayden.setText(getFormatTime(dateden));
                        }
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    timePickerDialog.show();
                }
                break;
            // Bắt sự kiện click của button NgayDi
            case R.id.btn_ngaydi:
                if (!is) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(datedi);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            datedi.setYear(i - 1900);
                            datedi.setMonth(i1);
                            datedi.setDate(i2);
                            btnNgaydi.setText(getFormatTime(datedi));
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.show();
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(datedi);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            datedi.setHours(i);
                            datedi.setMinutes(i1);
                            btnNgaydi.setText(getFormatTime(datedi));
                        }
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    timePickerDialog.show();
                }
                break;
            // Bắt sự kiện click của button DatPhong
            case R.id.btn_datphong:
                phong.setTinhtrang(1);
                phong.setSdt(edSdt.getText().toString());
                phong.setTen(edTen.getText().toString());
                phong.setCmnd(edCmnd.getText().toString());
                phong.setNs(edNs.getText().toString());
                phong.setThoigianden(btnNgayden.getText().toString());
                phong.setThoigiandi(btnNgaydi.getText().toString());
                dbManager.suaP(phong);
                onBackPressed();
                break;
            // Bắt sự kiện click của button Huy
            case R.id.btn_huy:
                onBackPressed();
                break;
        }
    }
}
