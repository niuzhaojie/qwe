package com.example.niuzhaojie.myapplication.model;

import java.util.Date;

/**
 * Created by niuzhaojie on 16/11/14.
 */

public class DayModel {

    private String month;

    private Date date;

    private boolean isSelected;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
