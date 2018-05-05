package com.example.waifu.calendercoba;

import android.support.v7.view.menu.MenuView;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class classKegiatan {
    String lokasi, namaKegiatan;
    Date date;
    boolean isMute, isCheck;
    User starter;
    ArrayList<User> members;

    public classKegiatan(String lokasi, String namaKegiatan, Date date, boolean isMute, boolean isCheck, User starter, User... members) {
        this.lokasi = lokasi;
        this.namaKegiatan = namaKegiatan;
        this.date = date;
        this.isMute = isMute;
        this.isCheck = isCheck;
        this.starter = starter;
        this.members = new ArrayList<>(Arrays.asList(members));

}
}
