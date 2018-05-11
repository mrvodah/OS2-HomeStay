package com.example.vietvan.quanly;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by VietVan on 09/05/2018.
 */

public class Phong implements Serializable {
    public int id;
    public int tinhtrang;
    public String ten, sdt, cmnd, ns, thoigianden, thoigiandi;

    public Phong(int tinhtrang, String ten, String sdt, String cmnd, String ns, String thoigianden, String thoigiandi) {
        this.tinhtrang = tinhtrang;
        this.ten = ten;
        this.sdt = sdt;
        this.cmnd = cmnd;
        this.ns = ns;
        this.thoigianden = thoigianden;
        this.thoigiandi = thoigiandi;
    }

    public Phong(int id, int tinhtrang, String ten, String sdt, String cmnd, String ns, String thoigianden, String thoigiandi) {
        this.id = id;
        this.tinhtrang = tinhtrang;
        this.ten = ten;
        this.sdt = sdt;
        this.cmnd = cmnd;
        this.ns = ns;
        this.thoigianden = thoigianden;
        this.thoigiandi = thoigiandi;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getThoigianden() {
        return thoigianden;
    }

    public void setThoigianden(String thoigianden) {
        this.thoigianden = thoigianden;
    }

    public String getThoigiandi() {
        return thoigiandi;
    }

    public void setThoigiandi(String thoigiandi) {
        this.thoigiandi = thoigiandi;
    }

    public Date getDateformat(String date){
        SimpleDateFormat format = new SimpleDateFormat("MMM dd yyy, HH:mm");
        try {
            Date d = format.parse(date);
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();
    }

    @Override
    public String toString() {
        return "Phong{" +
                "id=" + id +
                ", tinhtrang=" + tinhtrang +
                ", ten='" + ten + '\'' +
                ", sdt='" + sdt + '\'' +
                ", cmnd='" + cmnd + '\'' +
                ", ns='" + ns + '\'' +
                ", thoigianden='" + thoigianden + '\'' +
                ", thoigiandi='" + thoigiandi + '\'' +
                '}';
    }
}
