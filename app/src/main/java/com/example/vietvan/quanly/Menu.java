package com.example.vietvan.quanly;

/**
 * Created by VietVan on 09/05/2018.
 */

public class Menu {
    int anh;
    public String vanban;

    public Menu(int anh, String vanban) {
        this.anh = anh;
        this.vanban = vanban;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public String getVanban() {
        return vanban;
    }

    public void setVanban(String vanban) {
        this.vanban = vanban;
    }
}
