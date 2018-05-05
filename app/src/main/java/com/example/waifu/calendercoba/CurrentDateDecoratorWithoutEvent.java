package com.example.waifu.calendercoba;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class CurrentDateDecoratorWithoutEvent implements DayViewDecorator {
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        java.util.Calendar cal1 = java.util.Calendar.getInstance();
        java.util.Calendar cal2 = day.getCalendar();
        if (cal1 == null || cal2 == null)
            return false;
        return (cal1.get(java.util.Calendar.ERA) == cal2.get(java.util.Calendar.ERA)
                && cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR)
                && cal1.get(java.util.Calendar.DAY_OF_YEAR) == cal2.get(java.util.Calendar.DAY_OF_YEAR));
    }

    @Override
    public void decorate(DayViewFacade view) {

    }
}
