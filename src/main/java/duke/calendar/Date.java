package duke.calendar;

import duke.exception.DukeException;

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
     * @throws DukeException If cannot process date.
     */
    public Date(String unprocessedDate) throws DukeException {
        this.unprocessedDate = unprocessedDate;
        processDate();
    }

    /**
     * Processes the user input date.
     * If unprocessed date is empty, the date will not be processed.
     * @throws DukeException If date is in the wrong format or invalid.
     */
    public void processDate() throws DukeException {
        if (unprocessedDate.equals("")) {
            return;
        }
        String[] dateSplit = unprocessedDate.split("/");
        if (dateSplit.length < 3) {
            throw new DukeException("\u2639 OOPS!!! Please specify the date" +
                    " in the format date/month/year e.g. 2/12/2019.");
        }
        int monthNumber = Integer.parseInt(dateSplit[1]);
        if (isValidMonth(monthNumber)) {
            this.month = Month.of(monthNumber);
        } else {
            throw new DukeException("\u2639 OOPS!!! Please input a valid month.");
        }
        this.year = Integer.parseInt(dateSplit[2]);
        int inputDay = Integer.parseInt(dateSplit[0]);
        if (isValidDay(inputDay, monthNumber, year)) {
            this.day = inputDay;
        } else {
            throw new DukeException("\u2639 OOPS!!! Please input a valid day.");
        }
    }

    /**
     * Checks if input month is valid.
     * @param monthNumber Month number input by user.
     * @return Whether the input month is valid.
     */
    public boolean isValidMonth(int monthNumber) {
        if (monthNumber >= 1 && monthNumber <= 12) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if input day is valid.
     * @param day Day number input by user.
     * @param monthNumber Month number input by user.
     * @param year Year number input by user.
     * @return Whether the input day is valid.
     */
    public boolean isValidDay(int day, int monthNumber, int year) {
        switch(monthNumber) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            if (day >= 1 && day <= 31) {
                return true;
            } else {
                return false;
            }
        case 4:
        case 6:
        case 9:
        case 11:
            if (day >= 1 && day <= 30) {
                return true;
            } else {
                return false;
            }
        case 2:
            if (isLeapYear(year) && day >= 1 && day <= 29) {
                return true;
            } else if (day >= 1 && day <= 28) {
                return true;
            } else {
                return false;
            }
        default:
            return false;
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
        if (month == null) {
            return "";
        }
        if (day % 10 == 1 && day != 11) {
            return day + "st of " + month + " " + year;
        } else if (day % 10 == 2 && day != 12) {
            return day + "nd of " + month + " " + year;
        } else {
            return day + "th of " + month + " " + year;
        }
    }
}
