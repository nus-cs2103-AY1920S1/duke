package duke.date;

import duke.exception.DukeDateFormatException;

import java.text.DecimalFormat;

public class DukeDate {

    private static final String ERROR_ILLEGAL_YEAR = "☹ OOPS!!! Year should be a number greater than 0";
    private static final String ERROR_ILLEGAL_MONTH = "☹ OOPS!!! Month should be a number between 1 and 12.";
    private static final String ERROR_ILLEGAL_DAY = "☹ OOPS!!! Date must be greater than 0 (and less than 31).";
    private static final String ERROR_DAY_MONTH_MISMATCH = "☹ OOPS!!! Given date does not exist in given month.";
    private static final String ERROR_ILLEGAL_TIME = "☹ OOPS!!! Hour must be between 0 and 23,\n"
            + "              and minute must be between 0 and 59.";

    private int year;
    private Month month;
    private int day;
    private int hour;
    private int minute;
    private String amPmMarker;

    public enum Month {
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        private int monthInt;

        Month(int monthInt) {
            this.monthInt = monthInt;
        }

        public int getMonthInt() {
            return this.monthInt;
        }

        @Override
        public String toString() {
            String month = super.toString();
            return month.charAt(0) + month.substring(1).toLowerCase();
        }
    }

    public DukeDate(int year, int month, int day, int hour, int minute)
            throws DukeDateFormatException {
        if (year < 0) {
            throw new DukeDateFormatException(ERROR_ILLEGAL_YEAR);
        }
        this.year = year;
        this.month = this.getMonth(month);
        if (!dayIsValid(month, day)) {
            throw new DukeDateFormatException(ERROR_DAY_MONTH_MISMATCH);
        }
        this.day = day;
        this.setTime(hour, minute);
    }

    public DukeDate(int year, Month month, int day, int hour, int minute)
            throws DukeDateFormatException {
        if (year < 0) {
            throw new DukeDateFormatException(ERROR_ILLEGAL_YEAR);
        }
        this.year = year;
        this.month = month;
        if (!dayIsValid(month.getMonthInt(), day)) {
            throw new DukeDateFormatException(ERROR_DAY_MONTH_MISMATCH);
        }
        this.day = day;
        this.setTime(hour, minute);
    }

    private Month getMonth(int monthInt) throws DukeDateFormatException {
        switch (monthInt) {
        case 1:
            return Month.JANUARY;
        case 2:
            return Month.FEBRUARY;
        case 3:
            return Month.MARCH;
        case 4:
            return Month.APRIL;
        case 5:
            return Month.MAY;
        case 6:
            return Month.JUNE;
        case 7:
            return Month.JULY;
        case 8:
            return Month.AUGUST;
        case 9:
            return Month.SEPTEMBER;
        case 10:
            return Month.OCTOBER;
        case 11:
            return Month.NOVEMBER;
        case 12:
            return Month.DECEMBER;
        default:
            throw new DukeDateFormatException(ERROR_ILLEGAL_MONTH);
        }
    }

    private boolean dayIsValid(int month, int day) throws DukeDateFormatException {
        if (day < 1) {
            throw new DukeDateFormatException(ERROR_ILLEGAL_DAY);
        }
        switch (month) {
        case 2:
            return day <= 29;
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return day <= 31;
        case 4:
        case 6:
        case 9:
        case 11:
            return day <= 30;
        default:
            throw new DukeDateFormatException(ERROR_ILLEGAL_MONTH);
        }
    }

    private void setTime(int hour, int minute) throws DukeDateFormatException {
        if (hour > 23 || hour < 0 || minute > 59 || minute < 0) {
            throw new DukeDateFormatException(ERROR_ILLEGAL_TIME);
        }
        if (hour >= 12) {
            this.amPmMarker = "PM";
            this.hour = hour - (hour / 13) * 12;
        } else {
            this.amPmMarker = "AM";
            this.hour = hour;
        }
        this.minute = minute;
    }

    public String format() {
        DecimalFormat df = new DecimalFormat("00");
        return String.format("%d %s, %d, %d:%s %s",
                             this.day,
                             this.month,
                             this.year,
                             this.hour,
                             df.format(this.minute),
                             this.amPmMarker);
    }

    @Override
    public String toString() {
        return this.format();
    }

}
