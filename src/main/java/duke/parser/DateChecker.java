package duke.parser;

/**
 * Represent a Checker to check the validity of the date provided by the user.
 */
public class DateChecker {
    private int year;
    private int month;
    private int day;

    /**
     * Constructs a new DateChecker based on the date data given.
     * @param dateData an array containing the year, month and day stores as strings.
     */
    public DateChecker(String[] dateData) {
        this.day = Integer.parseInt(dateData[0]);
        this.month = Integer.parseInt(dateData[1]);
        this.year = Integer.parseInt(dateData[2]);
    }

    /**
     * Main method to check if the date given is invalid or not.
     * @return true if the date given contains an invalid year, month or day.
     */
    public boolean containsInvalidDate() {
        return containsInvalidYear() || containsInvalidMonth()
                || containsInvalidDay()
                || containsInvalidDayInMonth();
    }

    /**
     * Checks if the year given is invalid.
     * @return true if the year given is less than zero.
     */
    private boolean containsInvalidYear() {
        return year < 0;
    }

    /**
     * Checks if the month given is invalid.
     * @return true if the month given is less than 0 or greater than 12.
     */
    private boolean containsInvalidMonth() {
        return month < 0 || month > 12;
    }

    /**
     * Checks if the day given is invalid.
     * @return true if the day given is less than 0 or greater than 31.
     */
    private boolean containsInvalidDay() {
        return day < 0 || day > 31;
    }

    /**
     * Checks if the year given is a leap year.
     * @return true if the year can be divided by 4.
     */
    private boolean isLeapYear() {
        return year % 4 == 0;
    }

    /**
     * Checks if the day given fits in the month provided.
     * @return true if the day given is not a part of the month provided.
     */
    private boolean containsInvalidDayInMonth() {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12) {
            return day > 31;
        } else if (month == 2) {
            if (isLeapYear()) {
                return day > 29;
            } else {
                return day > 28;
            }
        } else {
            return day > 30;
        }
    }
}
