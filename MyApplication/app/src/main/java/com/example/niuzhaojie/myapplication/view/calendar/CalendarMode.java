package com.example.niuzhaojie.myapplication.view.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by niuzhaojie on 16/11/11.
 */

public class CalendarMode {

    private String yearAndMonth;

    private List<Date> days = new ArrayList<>();


    public String getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(String yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public List<Date> getDays() {
        return days;
    }

    public void setDays(List<Date> days) {
        this.days = days;
    }
}
