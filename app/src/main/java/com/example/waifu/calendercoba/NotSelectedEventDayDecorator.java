package com.example.waifu.calendercoba;

import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class NotSelectedEventDayDecorator implements DayViewDecorator {
    private Drawable drawable;

    public NotSelectedEventDayDecorator(Drawable drw){
        drawable = drw;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        for (classKegiatan k : Calendar.kegiatansDB)
        {
              java.util.Calendar cal1 = java.util.Calendar.getInstance();
              cal1.setTime(k.date);
              java.util.Calendar cal2 = day.getCalendar();
              if ((cal1.get(java.util.Calendar.ERA) == cal2.get(java.util.Calendar.ERA)
                      && cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR)
                      && cal1.get(java.util.Calendar.DAY_OF_YEAR) == cal2.get(java.util.Calendar.DAY_OF_YEAR))){
                  return true;
              }
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade d) {
        d.setBackgroundDrawable(drawable);
    }
}