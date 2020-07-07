package duke.task;

import duke.logic.DukeException;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.HashMap;

/**
 * Represents a date, contains three integers that represents the day, month, and year.
 */
public class Date {
    private int day;
    private int month;
    private int year;
    private static final HashMap<Integer, String> MONTHMAP = new HashMap<>();
    static {
        MONTHMAP.put(1, "January");
        MONTHMAP.put(2, "February");
        MONTHMAP.put(3, "March");
        MONTHMAP.put(4, "April");
        MONTHMAP.put(5, "May");
        MONTHMAP.put(6, "June");
        MONTHMAP.put(7, "July");
        MONTHMAP.put(8, "August");
        MONTHMAP.put(9, "September");
        MONTHMAP.put(10, "October");
        MONTHMAP.put(11, "November");
        MONTHMAP.put(12, "December");
    }


    public Date(String inputDate) {
        try {
            assert inputDate != null : "the input date cannot be null";
            String[] tempStringArr = inputDate.split("/");
            this.day = Integer.parseInt((String) Array.get(tempStringArr, 0));
            this.month = Integer.parseInt((String) Array.get(tempStringArr, 1));
            this.year = Integer.parseInt((String) Array.get(tempStringArr, 2));
            assert this.day <= 31 : "day value cannot be more than 31";
            assert this.month <= 12 : "month value cannot be more than 12";
        } catch (ArrayIndexOutOfBoundsException ae) {
            //can we throw this to the user interface instead?
            System.out.println("Date must be specified in the form dd/mm/yyyy");
        }
    }

    /**
     * Returns a string description of the day.
     *
     * @return string description of day.
     */
    public String getDayAsString() {
        StringBuilder dayBuilder = new StringBuilder();
        dayBuilder.append(this.day);
        if (this.day == 1 || this.day == 21 || this.day == 31) {
            dayBuilder.append("st");
        } else if (this.day == 2 || this.day == 22) {
            dayBuilder.append("nd");
        } else if (this.day == 3 || this.day == 23) {
            dayBuilder.append("rd");
        } else {
            dayBuilder.append("th");
        }
        return dayBuilder.toString();
    }

    /**
     * Returns a string description of the month.
     *
     * @return string description of month.
     */
    public String getMonthAsString() {
        return MONTHMAP.get(this.month);
    }

    /**
     * Returns a string description of the date.
     *
     * @return string description of date.
     */
    @Override
    public String toString() {
        StringBuilder dateBuilder = new StringBuilder();
        dateBuilder.append(getDayAsString());
        dateBuilder.append(" of ");
        dateBuilder.append(getMonthAsString());
        dateBuilder.append(" ");
        dateBuilder.append(this.year);
        return dateBuilder.toString();
    }
}
