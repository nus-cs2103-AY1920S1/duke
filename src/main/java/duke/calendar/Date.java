package duke.calendar;

import duke.exception.InvalidDateException;

import java.time.Month;

/**
 * Represents a date. A <code>Date</code> object corresponds to a time represented by a day, month and year.
 */
public class Date {
    protected String rawDate;
    protected int day;
    protected int year;
    protected Month month;
    protected boolean isNull = true;

    /**
     * Constructor for <code>Date</code>.
     * @param rawDate Unprocessed date.
     * @throws InvalidDateException If provided date is invalid.
     */
    public Date(String rawDate) throws InvalidDateException {
        this.rawDate = rawDate;
        if (rawDate != null) {
            isNull = false;
            processDate();
        }
    }

    /**
     * Extracts information about the day, month and year from the raw date.
     * @throws InvalidDateException If date is in the wrong format or invalid.
     */
    protected void processDate() throws InvalidDateException {
        assert isNull == false : "A null raw date should not be processed.";
        String[] dateComponents = rawDate.split("/");
        if (dateComponents.length < 3) {
            throw new InvalidDateException("☹ OOPS!!! Please specify a date in the "
                    + "form day/month/year. E.g. 2/12/2019");
        }
        assignYear(dateComponents[2]);
        assignMonth(dateComponents[1]);
        assignDay(dateComponents[0], dateComponents[1], dateComponents[2]);
    }

    private void assignYear(String rawYear) {
        int inputYear = Integer.parseInt(rawYear);
        year = inputYear;
    }

    private void assignMonth(String rawMonth) throws InvalidDateException {
        int inputMonthNum = Integer.parseInt(rawMonth);
        if (isInvalidMonth(inputMonthNum)) {
            throw new InvalidDateException("☹ OOPS!!! Please specify a valid month.");
        }
        this.month = Month.of(inputMonthNum);
    }

    private void assignDay(String rawDay, String rawMonth, String rawYear) throws InvalidDateException {
        int inputDay = Integer.parseInt(rawDay);
        int inputMonthNum = Integer.parseInt(rawMonth);
        int inputYear = Integer.parseInt(rawYear);
        if (isInvalidDay(inputDay, inputMonthNum, inputYear)) {
            throw new InvalidDateException("☹ OOPS!!! Please specify a valid day.");
        }
        day = inputDay;
    }

    /**
     * Checks if provided month is invalid.
     * @param inputMonthNum Number corresponding to specific month. E.g. 1 for January, 2 for February, etc.
     * @return False if 1 <= inputMonthNum <= 12
     */
    protected boolean isInvalidMonth(int inputMonthNum) {
        return inputMonthNum < 1 || inputMonthNum > 12;
    }

    /**
     * Checks if provided day is invalid.
     * @param inputDay Number corresponding to specific day
     * @param inputMonthNum Number corresponding to specific month. E.g. 1 for January, 2 for February, etc.
     * @param inputYear Number corresponding to year.
     * @return True if day is invalid for the given month and year.
     */
    protected boolean isInvalidDay(int inputDay, int inputMonthNum, int inputYear) {
        switch (inputMonthNum) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return !(inputDay >= 1 && inputDay <= 31);
        case 2:
            if (isLeapYear(inputYear)) {
                return !(inputDay >= 1 && inputDay <= 29);
            } else {
                return !(inputDay >= 1 && inputDay <= 28);
            }
        case 4:
        case 6:
        case 9:
        case 11:
            return !(inputDay >= 1 && inputDay <= 30);
        default:
            return true;
        }
    }

    /**
     * Checks if the provided year is a leap year.
     * @param inputYear Number corresponding to year.
     * @return True if the year is a leap year.
     */
    protected boolean isLeapYear(int inputYear) {
        if (inputYear % 400 == 0) {
            return true;
        } else if (inputYear % 100 == 0) {
            return false;
        } else if (inputYear % 4 == 0) {
            return true;
        } else {
            return true;
        }
    }

    /**
     * Checks if the provided raw date is null.
     * @return True if null was the input to the constructor.
     */
    public boolean isNull() {
        return isNull;
    }

    /**
     * Returns the provided raw date.
     * @return The raw date.
     */
    public String getRawDate() {
        return rawDate;
    }

    @Override
    public String toString() {
        if ((day % 10) == 1 && day != 11) {
            return day + "st of " + month.toString() + " " + year;
        } else if ((day % 10) == 2) {
            return day + "nd of " + month.toString() + " " + year;
        } else if ((day % 10) == 3) {
            return day + "rd of " + month.toString() + " " + year;
        } else {
            return day + "th of " + month.toString() + " " + year;
        }
    }
}
