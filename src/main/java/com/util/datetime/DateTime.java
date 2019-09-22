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
        String[] dateTimeStrings = str.trim().split(" ");
        String[] dateStrings = dateTimeStrings[0].split("/");
        int day = Integer.parseInt(dateStrings[0]);
        int month = Integer.parseInt(dateStrings[1]);
        int year = Integer.parseInt(dateStrings[2]);
        int hour = Integer.parseInt(dateTimeStrings[1].substring(0, 2));
        String minStr = dateTimeStrings[1].substring(2);
        if (minStr.charAt(0) == ':') {
            minStr = minStr.substring(1);
        }
        int min = Integer.parseInt(minStr);
        return new DateTime(year, month, day, hour, min);
    }

    /**
     * Adds argument datetime to this datetime.
     * @param c2    argument datetime
     */
    public void add(DateTime c2) {
        add(Calendar.YEAR, c2.get(Calendar.YEAR));
        add(Calendar.MONTH, c2.get(Calendar.MONTH) + 1); // Zero-based months
        add(Calendar.DATE, c2.get(Calendar.DATE));
        add(Calendar.HOUR_OF_DAY, c2.get(Calendar.HOUR_OF_DAY));
        add(Calendar.MINUTE, c2.get(Calendar.MINUTE));
        add(Calendar.SECOND, c2.get(Calendar.SECOND));
        add(Calendar.MILLISECOND, c2.get(Calendar.MILLISECOND));
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
