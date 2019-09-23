package com.util.datetime;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTime extends GregorianCalendar {

    /**
     * Construct with date time attributes.
     * @param year      [0000 - 9999]
     * @param month     [00 - 12]
     * @param day       [00 - X]
     * @param hour      [00 - 23]
     * @param min       [00 - 59]
     */
    public DateTime(int year, int month, int day, int hour, int min) {
        super(year, month, day, hour, min);
    }

    /**
     * Parses input string and returns matching DateTime object if any.
     * @param str   input string
     * @return      DateTime object
     */
    public static DateTime parseString(String str) {
        DateTimeParse obj = new DateTimeParse(str);
        return new DateTime(obj.year, obj.month, obj.day, obj.hour, obj.min);
    }

    /**
     * Adds argument datetime to this datetime.
     * @param c2    argument datetime
     */
    public void add(DateTimeParse c2) {
        add(Calendar.YEAR, c2.year);
        add(Calendar.MONTH, c2.month);
        add(Calendar.DATE, c2.day);
        add(Calendar.HOUR_OF_DAY, c2.hour);
        add(Calendar.MINUTE, c2.min);
    }

    /**
     * Returns a duplicate of this object.
     * @return  duplicate
     */
    public DateTime clone() {
        return new DateTime(get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DATE),
                get(Calendar.HOUR_OF_DAY), get(Calendar.MINUTE));
    }

    @Override
    public String toString() {
        return get(Calendar.DAY_OF_MONTH) + "/" + get(Calendar.MONTH) + "/" + get(Calendar.YEAR)
                + " " + String.format("%tR", this).replaceAll(":", "");
    }
}
