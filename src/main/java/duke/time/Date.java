package duke.time;

import duke.exception.DukeException;

/**
 * Class that represents a date.
 */
public class Date {

    /**
     * The day of the year.
     */
    private int day;

    /**
     * The month of the year.
     */
    private int month;

    /**
     * The year.
     */
    private int year;

    /**
     * The output of the day in String.
     */
    private String dayOutput;

    /**
     * The output of the month in String.
     */
    private String monthOutput;

    /**
     * The output of the year in String.
     */
    private String yearOutput;

    /**
     * To check if the format of the date is valid.
     */
    private boolean validFormat = true;

    /**
     * Constructor that takes in the day month and year to format it properly.
     * @param day The day.
     * @param month The month.
     * @param year The year.
     * @throws DukeException Error for when the input is not correct.
     */
    public Date(int day, int month, int year) throws DukeException {
        String dayString = String.valueOf(day);
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);
        if (dayString.length() > 2 || monthString.length() > 2 || yearString.length() != 4) {
            validFormat = false;
        }
        this.day = day;
        this.month = month;
        this.year = year;
        switch (day) {
        case 1:
            dayOutput = "1st";
            break;
        case 2:
            dayOutput = "2nd";
            break;
        case 3:
            dayOutput = "3rd";
            break;
        default:
            dayOutput = dayString + "th";
        }
        switch (month) {
        case 1:
            monthOutput = "January";
            break;
        case 2:
            monthOutput = "February";
            break;
        case 3:
            monthOutput = "March";
            break;
        case 4:
            monthOutput = "April";
            break;
        case 5:
            monthOutput = "May";
            break;
        case 6:
            monthOutput = "June";
            break;
        case 7:
            monthOutput = "July";
            break;
        case 8:
            monthOutput = "August";
            break;
        case 9:
            monthOutput = "September";
            break;
        case 10:
            monthOutput = "October";
            break;
        case 11:
            monthOutput = "November";
            break;
        case 12:
            monthOutput = "December";
            break;
        default:
            throw new DukeException("     Invalid month entered!");
        }
        yearOutput = yearString;
        if (day < 0) {
            throw new DukeException("     Negative date does not exist!");
        }
        if (monthOutput.equals("February")) {
            if (day > 28) {
                throw new DukeException("     February only has 28 days!");
            }
        } else if (monthOutput.equals("April") || monthOutput.equals("June") || monthOutput.equals("September")
                || monthOutput.equals("November")) {
            if (day > 30) {
                throw new DukeException("     That month only has 30 days!");
            }
        } else {
            if (day > 31) {
                throw new DukeException("     That month only has 31 days!");
            }
        }
    }

    /**
     * Used to check whether the format is valid or not.
     * @return True if it is valid, false otherwise.
     */
    public boolean isValid() {
        return validFormat;
    }

    @Override
    public String toString() {
        return this.dayOutput + " of " + monthOutput + " " + yearOutput;
    }
}
