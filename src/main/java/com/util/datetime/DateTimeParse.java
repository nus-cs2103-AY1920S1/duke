package com.util.datetime;

public class DateTimeParse {
    public int day;
    public int month;
    public int year;
    public int hour;
    public int min;

    /**
     * Parses a string into its day month year hour minute components
     * without validation into proper dates.
     * @param str   string to parse
     */
    public DateTimeParse(String str) {
        String[] dateTimeStrings = str.trim().split(" ");
        String[] dateStrings = dateTimeStrings[0].split("/");
        day = Integer.parseInt(dateStrings[0]);
        month = Integer.parseInt(dateStrings[1]);
        year = Integer.parseInt(dateStrings[2]);
        hour = Integer.parseInt(dateTimeStrings[1].substring(0, 2));
        String minStr = dateTimeStrings[1].substring(2);
        if (minStr.charAt(0) == ':') {
            minStr = minStr.substring(1);
        }
        min = Integer.parseInt(minStr);
    }
}
