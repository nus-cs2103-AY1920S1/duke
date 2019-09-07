package duke.calendar;

import duke.exception.InvalidDateException;

import java.time.Month;

/**
 * Represents a date. A <code>Date</code> object corresponds to a date on a Gregorian calendar with a day,
 * month and year.
 */
public class Date {
    protected String unprocessedDate;
    protected int day;
    protected Month month;
    protected int year;

    /**
     * Constructor for <code>Date</code>.
     * @param unprocessedDate User unprocessed date input.
     * @throws duke.exception.InvalidDateException If cannot process date.
     */
    public Date(String unprocessedDate) throws InvalidDateException {
        this.unprocessedDate = unprocessedDate;
        processDate();
    }

    /**
     * Processes the user input date.
     * If unprocessed date is empty, the date will not be processed.
     * @throws InvalidDateException If date is in the wrong format or invalid.
     */
    public void processDate() throws InvalidDateException {
        boolean isEmptyDate = unprocessedDate.equals("");
        if (isEmptyDate) {
            return;
        }
        String[] dateSplit = unprocessedDate.split("/");
        boolean isWrongFormat = dateSplit.length > 3;
        if (isWrongFormat) {
            throw new InvalidDateException( "☹ OOPS!!! Please specify the date"
                    + " in the format date/month/year e.g. 2/12/2019.");
        }
        processMonth(dateSplit);
        processYear(dateSplit);
        processDay(dateSplit);
    }

    private void processMonth(String[] dateSplit) throws InvalidDateException {
        int monthNumber = Integer.parseInt(dateSplit[1]);
        if (isInvalidMonth(monthNumber)) {
            throw new InvalidDateException("☹ OOPS!!! Please input a valid month.");
        }
        this.month = Month.of(monthNumber);
    }

    private void processYear(String[] dateSplit) {
        this.year = Integer.parseInt(dateSplit[2]);
    }

    private void processDay(String[] dateSplit) throws InvalidDateException {
        int inputDay = Integer.parseInt(dateSplit[0]);
        int monthNumber = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]);
        if (isInvalidDay(inputDay, monthNumber, year)) {
            throw new InvalidDateException("☹ OOPS!!! Please input a valid day.");
        }
        this.day = inputDay;

    }

    /**
     * Checks if input month is valid.
     * @param monthNumber Month number input by user.
     * @return Whether the input month is valid.
     */
    public boolean isInvalidMonth(int monthNumber) {
        if (monthNumber >= 1 && monthNumber <= 12) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if input day is valid.
     * @param day Day number input by user.
     * @param monthNumber Month number input by user.
     * @param year Year number input by user.
     * @return Whether the input day is valid.
     */
    public boolean isInvalidDay(int day, int monthNumber, int year) {
        switch (monthNumber) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            if (day >= 1 && day <= 31) {
                return false;
            } else {
                return true;
            }
        case 4:
        case 6:
        case 9:
        case 11:
            if (day >= 1 && day <= 30) {
                return false;
            } else {
                return true;
            }
        case 2:
            if (isLeapYear(year) && day >= 1 && day <= 29) {
                return false;
            } else if (day >= 1 && day <= 28) {
                return false;
            } else {
                return true;
            }
        default:
            return true;
        }
    }

    /**
     * Checks if input year is a leap year.
     * @param year Year number input by user.
     * @return Whether the input year is a leap year.
     */
    public boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else if (year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns day number.
     * @return Day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns month e.g. JANUARY.
     * @return Month.
     */
    public Month getMonth() {
        return month;
    }

    /**
     * Returns year number.
     * @return Year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the unprocessed date.
     * @return Unprocessed date.
     */
    public String getUnprocessedDate() {
        return unprocessedDate;
    }

    @Override
    public String toString() {
        boolean isEmptyDate = month == null;
        if (isEmptyDate) {
            return "";
        }
        boolean dateEndsWithFirst = day % 10 == 1 && day != 11;
        boolean dateEndsWithSecond = day % 10 == 2 && day != 12;
        if (dateEndsWithFirst) {
            return day + "st of " + month + " " + year;
        } else if (dateEndsWithSecond) {
            return day + "nd of " + month + " " + year;
        } else {
            return day + "th of " + month + " " + year;
        }
    }
}
