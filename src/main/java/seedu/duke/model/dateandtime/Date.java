package seedu.duke.model.dateandtime;

/**
 * Defines a Date object containing a day, month and year attribute.
 */
public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    /**
     * Creates an instance of Date.
     * @param day Day of the month starting from 1 to the last day of the month.
     * @param month Numerical representation of a month from 1 to 12.
     * @param year Year beginning from 0 AD.
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Returns the String representation of the month from its numerical representation.
     * @param month Numerical representation of a month from 1 to 12.
     * @return String representation of the month (eg. January) converted from its numerical representation.
     */
    public static String getMonth(int month) {
        assert month >= 1 && month <= 12 : "Month for which String representation is requested is out of range.";
        return month == 1
                ? "January"
                : month == 2
                ? "February"
                : month == 3
                ? "March"
                : month == 4
                ? "April"
                : month == 5
                ? "May"
                : month == 6
                ? "June"
                : month == 7
                ? "July"
                : month == 8
                ? "August"
                : month == 9
                ? "September"
                : month == 10
                ? "October"
                : month == 11
                ? "November"
                : month == 12
                ? "December"
                : "";
    }

    /**
     * Returns whether a given year is a leap year.
     * @param year Integer year between 0-9999.
     * @return A boolean of whether or not the given year is a leap year.
     */
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        } else if (year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Date other) {
        if (this.year < other.year) {
            return -1;
        } else if (this.year > other.year) {
            return 1;
        } else if (this.month < other.month) {
            return -1;
        } else if (this.month > other.month) {
            return 1;
        } else if (this.day < other.day) {
            return -1;
        } else if (this.day > other.day) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Returns the String representation of a Date instance.
     * @return String representation of a Date instance in DD/MM/YYYY format.
     */
    public String toString() {
        assert day >= 1 && day <= 31 : "Day in Date object is out of range.";
        assert month >= 1 && month <= 12 : "Month in Date object is out of range.";
        assert year >= 0 : "Year in Date object is out of range.";

        return String.format("%02d", day)
                + "/" + String.format("%02d", month)
                + "/" + String.format("%04d", year);
    }

}
