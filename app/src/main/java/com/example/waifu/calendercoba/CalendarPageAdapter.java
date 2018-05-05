package com.example.waifu.calendercoba;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CalendarPageAdapter extends RecyclerView.Adapter<CalendarPageAdapter.ViewHolder> {
    public ArrayList<classKegiatan> kegiatans;

    public CalendarPageAdapter(ArrayList<classKegiatan> kegiatans){
        this.kegiatans = kegiatans;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView userPic;
        public TextView jam, namaKegiatan, starter, lokasi, group;
        public CheckBox isCheck;
        public Switch isMute;

        public ViewHolder(View v) {
            super(v);
            userPic = (ImageView) itemView.findViewById(R.id.userPic);
            jam = (TextView) itemView.findViewById(R.id.jam);
            namaKegiatan = (TextView) itemView.findViewById(R.id.namaKegiatan);
            starter = (TextView) itemView.findViewById(R.id.namaStarter);
            lokasi = (TextView) itemView.findViewById(R.id.lokasi);
            group = (TextView) itemView.findViewById(R.id.group);
            isCheck = (CheckBox) itemView.findViewById(R.id.isCheck);
            isMute = (Switch) itemView.findViewById(R.id.isMute);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kegiatan, parent, false);
        ViewHolder vh = new ViewHolder(mView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder a, int position) {
        classKegiatan k = kegiatans.get(position);
        a.userPic.setImageResource(R.drawable.ic_userpic);
        a.isMute.setChecked(k.isMute);
        a.isMute.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int pos = a.getAdapterPosition();
                classKegiatan k = kegiatans.get(pos);
                k.isMute = isChecked;
                kegiatans.set(pos, k);
            }
        });
        a.isCheck.setChecked(k.isCheck);
        a.isCheck.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int pos = a.getAdapterPosition();
                classKegiatan k = kegiatans.get(pos);
                k.isCheck = isChecked;
                kegiatans.set(pos, k);
            }
        });
        a.namaKegiatan.setText(k.namaKegiatan);
        a.starter.setText(k.starter.nama);
        a.lokasi.setText(k.lokasi);
        String gr = "Pribadi";
        if (k.members.size() > 1){
            if (k.members.contains(Calendar.currentUser)){
                gr = "Saya dan " + (k.members.size() - 1) + " orang lainnya";
            }
            else{
                gr = k.members.size() + " orang";
            }
        }
        a.group.setText(gr);
        java.util.Calendar cl = GregorianCalendar.getInstance();
        cl.setTime(k.date);
        a.jam.setText(new SimpleDateFormat("hh:mm").format(cl.getTime()));
    }

    @Override
    public int getItemCount() {
        return kegiatans.size();
    }
}
