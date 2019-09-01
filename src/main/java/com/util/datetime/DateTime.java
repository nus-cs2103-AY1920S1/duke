package com.util.datetime;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTime extends GregorianCalendar {
    public DateTime(int year, int month, int day, int hour, int min) {
        super(year, month, day, hour, min);
    }

    public static DateTime parseString(String str) {
        String[] dateTimeStrings = str.trim().split(" ");
        String[] dateStrings = dateTimeStrings[0].split("/");
        int day = Integer.parseInt(dateStrings[0]);
        int month = Integer.parseInt(dateStrings[1]);
        int year = Integer.parseInt(dateStrings[2]);
        int hour = Integer.parseInt(dateTimeStrings[1].substring(0,2));
        int min = Integer.parseInt(dateTimeStrings[1].substring(2));
        return new DateTime(year, month, day, hour, min);
    }

    @Override
    public String toString() {
        return get(Calendar.DAY_OF_MONTH) + "/" + get(Calendar.MONTH) + "/" + get(Calendar.YEAR)
                + " " + String.format("%tR", this).replaceAll(":", "");
    }
}
