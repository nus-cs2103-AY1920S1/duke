package oop;

import java.util.Scanner;

/**
 * Date class, to store the date information of a certain task.
 */
public class Date {
    /**
     * The day portion of the Date.
     */
    private String day;

    /**
     * The month portion of the Date.
     */
    private String month;

    /**
     * The year portion of the Date.
     */
    private String year;

    /**
     * The time portion of the Date.
     */
    private String time;

    /**
     * Constructs and initializes the attributes of a new Date object.
     * @param info The required information to retrieve date, month, year and time.
     */
    public Date(String info) {
        Scanner sc = new Scanner(info);
        String[] info1 = sc.next().split("[/]");
        this.day = getDate(info1[0]);
        this.month = getMonth(info1[1]);
        this.year = info1[2];
        this.time = getTime(sc.nextLine().trim());
        sc.close();
    }

    /**
     * Helps to format the date correctly.
     * @param date date information to be formatted.
     * @return The formatted date.
     */
    private String getDate(String date) {
        if (date.startsWith("1") && date.length() == 2) {
            return date + "th";
        } else if (date.endsWith("1")) {
            return date + "st";
        } else if (date.endsWith("2")) {
            return date + "nd";
        } else if (date.endsWith("3")) {
            return date + "rd";
        } else {
            return date + "th";
        }
    }

    /**
     * Helps to format the month correctly.
     * @param month month information to be formatted.
     * @return The formatted month.
     */
    private String getMonth(String month) {
        String[] arrayOfMonths = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
        return arrayOfMonths[Integer.parseInt(month) - 1];
    }

    /**
     * Helps to format the time correctly.
     * @param time month information to be formatted.
     * @return The formatted time.
     */
    private String getTime(String time) {
        String timing = "";
        if (time.startsWith("0")) {
            timing = time.substring(1, 2);
        } else {
            timing = time.substring(0, 2);
        }

        int t = Integer.parseInt(timing);
        if (t > 12) {
            return (t % 12) + "pm";
        } else if (t == 0) {
            return 12 + "am";
        } else {
            return t + "am";
        }
    }

    /**
     * A String representation of a Data object, printing its fields in a format.
     * @return Returns the formatted String representation of Data objects.
     */
    @Override
    public String toString() {
        return String.format("%s of %s %s, %s", day, month, year, time);
    }
}

