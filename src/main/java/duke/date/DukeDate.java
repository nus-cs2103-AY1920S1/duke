package duke.date;

import duke.module.AutoResponse;
import duke.exception.DukeDateFormatException;

import java.text.DecimalFormat;

/**
 * A date class to be used in the Duke application.
 * This class represents the time and date of the tasks.
 * <code>DukeDate</code> can be formatted into a String by {@link DukeDate#format()} or {@link DukeDate#toString()}.
 */
public class DukeDate {

    private int year;
    private Month month;
    private int day;
    private int hour;
    private int minute;
    /** Determines whether hour field is before noon or after noon. */
    private String amPmMarker;

    /**
     * Represents the twelve months.
     */
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

        /**
         * Returns the name of this month with only the first letter capitalized.
         *
         * @return Name of this month with the first letter capitalized.
         */
        @Override
        public String toString() {
            String month = super.toString();
            return month.charAt(0) + month.substring(1).toLowerCase();
        }
    }

    /**
     * Constructs a DukeDate instance to be used in a test class.
     * Not for use.
     */
    protected DukeDate() {
        this.year = 2020;
        this.month = Month.JANUARY;
        this.day = 1;
        this.hour = 12;
        this.minute = 0;
    }

    /**
     * Constructs a DukeDate object.
     *
     * @param year Year of this DukeDate.
     * @param month Month of this DukeDate as an integer.
     * @param day Day of this DukeDate.
     * @param hour Hour of this DukeDate to be inputted as 24 hour format.
     * @param minute Minute of this DukeDate.
     * @throws DukeDateFormatException When the given date or time is illegal.
     */
    public DukeDate(int year, int month, int day, int hour, int minute)
            throws DukeDateFormatException {
        if (year < 0) {
            throw new DukeDateFormatException(AutoResponse.ERROR_ILLEGAL_YEAR);
        }
        if (isInvalid(month, day)) {
            throw new DukeDateFormatException(AutoResponse.ERROR_DAY_MONTH_MISMATCH);
        }

        this.year = year;
        this.month = this.getMonth(month);
        this.day = day;
        this.setTime(hour, minute);
    }

    /**
     * Constructs a DukeDate object.
     *
     * @param year Year of this DukeDate.
     * @param month Month of this DukeDate as the Month enum.
     * @param day Day of this DukeDate.
     * @param hour Hour of this DukeDate to be inputted as 24 hour format.
     * @param minute Minute of this DukeDate.
     * @throws DukeDateFormatException When the given date or time is illegal.
     */
    public DukeDate(int year, Month month, int day, int hour, int minute)
            throws DukeDateFormatException {
        if (year < 0) {
            throw new DukeDateFormatException(AutoResponse.ERROR_ILLEGAL_YEAR);
        }
        if (isInvalid(month.getMonthInt(), day)) {
            throw new DukeDateFormatException(AutoResponse.ERROR_DAY_MONTH_MISMATCH);
        }

        this.year = year;
        this.month = month;
        this.day = day;
        this.setTime(hour, minute);
    }

    /**
     * Switches given integer (1 - 12) to the corresponding {@link Month}.
     *
     * @param monthInt Integer to be converted.
     * @return <code>Month</code> corresponding to monthInt field.
     * @throws DukeDateFormatException If monthInt <= 0 or monthInt >= 13.
     */
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
            throw new DukeDateFormatException(AutoResponse.ERROR_ILLEGAL_MONTH);
        }
    }

    /**
     * Checks if the given month and day is valid.
     *
     * @param month Month to be checked.
     * @param day Day to be checked.
     * @return True if given month and day is valid, false otherwise.
     * @throws DukeDateFormatException When the given fields is invalid.
     */
    private boolean isInvalid(int month, int day) throws DukeDateFormatException {
        if (day < 1) {
            throw new DukeDateFormatException(AutoResponse.ERROR_ILLEGAL_DAY);
        }
        switch (month) {
        case 2:
            return day > 29;
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return day > 31;
        case 4:
        case 6:
        case 9:
        case 11:
            return day > 30;
        default:
            throw new DukeDateFormatException(AutoResponse.ERROR_ILLEGAL_MONTH);
        }
    }

    /**
     * Formats time into AM/PM format.
     *
     * @param hour Hour of this DukeDate inputted as 24 hour format.
     * @param minute Minute of this DukeDate.
     * @throws DukeDateFormatException When given time is invalid.
     */
    private void setTime(int hour, int minute) throws DukeDateFormatException {
        if (hour > 23 || hour < 0 || minute > 59 || minute < 0) {
            throw new DukeDateFormatException(AutoResponse.ERROR_ILLEGAL_TIME);
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

    /**
     * Formats this DukeDate into a String.
     * The format is "dd Month, YYYY, hh:mm a"
     * <ul>
     *     <li>Month - the month written up to three letters..
     *     <li>a - the AM/PM marker
     * </ul>
     *
     * @return This DukeDate formatted into a String.
     */
    public String format() {
        DecimalFormat df = new DecimalFormat("00");
        return String.format("%d %s, %d, %d:%s %s",
                             this.day,
                             this.month.toString().substring(0, 3),
                             this.year,
                             this.hour,
                             df.format(this.minute),
                             this.amPmMarker);
    }

    /**
     * Returns same result as {@link DukeDate#format()}.
     *
     * @return {@link DukeDate#format()}.
     */
    @Override
    public String toString() {
        return this.format();
    }

}
