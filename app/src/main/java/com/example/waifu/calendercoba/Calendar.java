package com.example.waifu.calendercoba;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class Calendar extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ArrayList<classKegiatan> kegiatansDB;
    private ArrayList<classKegiatan> kegiatans;

    public static com.prolificinteractive.materialcalendarview.MaterialCalendarView cv;
    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mRecyclerView = (RecyclerView) findViewById(R.id.listKegiatan);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // TEST ////////////////////////////////////////////////////////////////
        User curUser= new User();
        curUser.id = "sebutSajaBunga";
        curUser.nama = "Bunga";

        User anUserA= new User();
        curUser.id = "sebutSajaMawar";
        curUser.nama = "Mawar";

        User anUserB= new User();
        curUser.id = "sebutSajaMelati";
        curUser.nama = "Melati";

        currentUser = curUser;

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        kegiatans = new ArrayList<classKegiatan>(){};
        
        kegiatansDB = new ArrayList<classKegiatan>(){};
        kegiatansDB.add(new classKegiatan("MDC", "Pesta Pora",
                new Date(1525478662*1000L), true, false, curUser, curUser, anUserB));
        kegiatansDB.add(new classKegiatan("MDC", "Ngabuburit",
                new Date(1525478662*1000L), false, false, curUser, curUser, anUserA, anUserB));
        kegiatansDB.add(new classKegiatan("MDC", "Dinner",
                new Date(1525564800*1000L), false, false, curUser, curUser, anUserA, anUserB));
        kegiatansDB.add(new classKegiatan("MDC", "Mabar Dota",
                new Date(1525564800*1000L), false, false, curUser, curUser, anUserA, anUserB));
        kegiatansDB.add(new classKegiatan("MDC", "Lunch",
                new Date(1525824000*1000L), false, false, curUser, curUser, anUserA, anUserB));
        kegiatansDB.add(new classKegiatan("MDC", "Lomba",
                new Date(1525824000*1000L), false, false, curUser, curUser, anUserA, anUserB));
        kegiatansDB.add(new classKegiatan("MDC", "Hackjam",
                new Date(1525996800*1000L), false, false, curUser, curUser, anUserA, anUserB));
        kegiatansDB.add(new classKegiatan("MDC", "Puasa",
                new Date(1526342400*1000L), false, false, curUser, curUser, anUserA, anUserB));

        for (classKegiatan k: kegiatansDB.stream().filter(a -> isNow(a, new Date())).collect(Collectors.toList())
             ) {
            kegiatans.add(k);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        mAdapter = new CalendarPageAdapter(kegiatans);
        mRecyclerView.setAdapter(mAdapter);

        cv = (com.prolificinteractive.materialcalendarview.MaterialCalendarView) findViewById(R.id.kalender);
        cv.addDecorator(new NotSelectedEventDayDecorator(ResourcesCompat.getDrawable(getResources(), R.drawable.datedecorator_notselected_event, null)));
        cv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                TextView tanggalTxt = (TextView) findViewById(R.id.tanggalTxt);
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                tanggalTxt.setText(df.format(date.getCalendar().getTime()));
                kegiatans.clear();
                for (classKegiatan k: kegiatansDB.stream().filter(a -> isNow(a, date.getCalendar())).collect(Collectors.toList())
                        ) {
                    kegiatans.add(k);
                }
                mRecyclerView.setAdapter(new CalendarPageAdapter(kegiatans));
            }
        });
    }

    public boolean isNow(classKegiatan k, Date selectedDate){
        java.util.Calendar cl1 = java.util.Calendar.getInstance();
        java.util.Calendar cl2 = java.util.Calendar.getInstance();
        cl1.setTime(k.date);
        cl2.setTime(selectedDate);

        if (isSameDay(cl1, cl2)){
            return  true;
        }

        return false;
    }

    public boolean isNow(classKegiatan k, java.util.Calendar cl2){
        java.util.Calendar cl1 = java.util.Calendar.getInstance(); // creates a new calendar instance
        cl1.setTime(k.date);   // assigns calendar to given date
        if (isSameDay(cl1, cl2)){
            return  true;
        }

        return false;
    }

    public boolean isSameDay(java.util.Calendar cal1, java.util.Calendar cal2) {
        if (cal1 == null || cal2 == null)
            return false;
        return (cal1.get(java.util.Calendar.ERA) == cal2.get(java.util.Calendar.ERA)
                && cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR)
                && cal1.get(java.util.Calendar.DAY_OF_YEAR) == cal2.get(java.util.Calendar.DAY_OF_YEAR));
    }
}