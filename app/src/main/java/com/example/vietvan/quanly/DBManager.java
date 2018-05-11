package com.example.vietvan.quanly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by VietVan on 09/05/2018.
 */

public class DBManager extends SQLiteOpenHelper{

    public static final String DATABASE_NAME ="quanlyphong";
    private static final String TABLE_NAME ="phong";
    private static final int VERSION = 6;
    private static final String ID ="id";
    private static final String TINHTRANG = "tinhtrang";
    private static final String TEN ="ten";
    private static final String SDT = "sdt";
    private static final String CMND = "cmnd";
    private static final String NS = "ns";
    private static final String THOIGIANDEN = "thoigianden";
    private static final String THOIGIANDI = "thoigiandi";

    public DBManager(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    // Tạo database với các trường dữ liệu
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + "(" +
                ID + " integer primary key autoincrement, " +
                TINHTRANG + " int, " +
                TEN + " text, " +
                SDT + " text, " +
                CMND + " text, " +
                NS + " text, " +
                THOIGIANDEN + " text, " +
                THOIGIANDI + " text" +
                ");"
                ;
        db.execSQL(sql);
    }

    // Cập nhật lại bảng khi có thay đổi VERSION
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Lấy danh sách Phòng
    public List<Phong> getAllPhong() {
        List<Phong> list = new ArrayList<Phong>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Phong(cursor.getInt(0), cursor.getInt(1),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    // Tạo bộ dữ liệu mặc định
    public void text(){
        for(int i=0;i<9;i++){
            themP(new Phong(0, "", "", "", "", "", ""));
        }
    }

    // Hàm cập nhật lại Danh sách Phòng
    public void capnhatDS(List<Phong> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getTinhtrang() == 0){

            }
            else{
                SimpleDateFormat simple = new SimpleDateFormat("MMM dd yyy, HH:mm");
                try {
                    Date date = simple.parse(list.get(i).getThoigiandi());
                    if(System.currentTimeMillis() > date.getTime()){
                        list.get(i).setTinhtrang(0);
                        list.get(i).setThoigianden("");
                        list.get(i).setThoigiandi("");
                        list.get(i).setTen("");
                        list.get(i).setSdt("");
                        suaP(list.get(i));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    // Thêm Phòng mới
    public void themP(Phong phong){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TEN, phong.getTen());
        values.put(TINHTRANG, phong.getTinhtrang());
        values.put(SDT, phong.getSdt());
        values.put(CMND, phong.getCmnd());
        values.put(NS, phong.getNs());
        values.put(THOIGIANDEN, phong.getThoigianden());
        values.put(THOIGIANDI, phong.getThoigiandi());

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    // Sửa thông tin Phòng
    public int suaP(Phong phong){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TEN, phong.getTen());
        values.put(TINHTRANG, phong.getTinhtrang());
        values.put(SDT, phong.getSdt());
        values.put(CMND, phong.getCmnd());
        values.put(NS, phong.getNs());
        values.put(THOIGIANDEN, phong.getThoigianden());
        values.put(THOIGIANDI, phong.getThoigiandi());

        return db.update(TABLE_NAME,values,ID +"=?",new String[] { String.valueOf(phong.getId())});

    }

}
