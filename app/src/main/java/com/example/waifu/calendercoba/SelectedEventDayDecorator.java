package com.example.waifu.calendercoba;

import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class SelectedEventDayDecorator implements DayViewDecorator {
    private Drawable drawable;

    public SelectedEventDayDecorator(Drawable drw){
        drawable = drw;
    }

    public boolean shouldDecorate(CalendarDay day) {
        if (Calendar.cv.getSelectedDate() == null){
            return false;
        }
        int a = 0;
        java.util.Calendar calx = Calendar.cv.getSelectedDate().getCalendar();
        if (!isSameDay(calx, day.getCalendar())){
            return false;
        }
        if (Calendar.kegiatansDB == null || Calendar.kegiatansDB.size() == 0){
            return false;
        }
        else {
            for (classKegiatan k:Calendar.kegiatansDB
                    ) {
                java.util.Calendar cal1 = java.util.Calendar.getInstance();
                cal1.setTime(k.date);
                java.util.Calendar cal2 = day.getCalendar();
                if (cal1 == null || cal2 == null)
                {
                    return false;
                }
                else   {
                    return (cal1.get(java.util.Calendar.ERA) == cal2.get(java.util.Calendar.ERA)
                            && cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR)
                            && cal1.get(java.util.Calendar.DAY_OF_YEAR) == cal2.get(java.util.Calendar.DAY_OF_YEAR));
                }
            }
        }
        return false;
    }
    public boolean isSameDay(java.util.Calendar cal1, java.util.Calendar cal2){
        if (cal1 == null || cal2 == null)
            return false;
        return (cal1.get(java.util.Calendar.ERA) == cal2.get(java.util.Calendar.ERA)
                && cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR)
                && cal1.get(java.util.Calendar.DAY_OF_YEAR) == cal2.get(java.util.Calendar.DAY_OF_YEAR));
    }

    @Override
    public void decorate(DayViewFacade d) {
        d.setBackgroundDrawable(drawable);
    }
}
