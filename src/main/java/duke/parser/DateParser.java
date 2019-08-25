package duke.parser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import duke.exception.InvalidDateInputException;

/**
 * Represents a Date Parser to parse in all String data given into a form readable by the user.
 */
public class DateParser {

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
    private Date inputParser;
    private String input;
    private Calendar calendar = Calendar.getInstance();

    /**
     * Reads the input and stores it in the parser.
     * @param input The input provided by the user.
     */
    public void readInput(String input) {
        this.input = input;
    }

    /**
     * Reads the month (provided an integer) and returns it as a String.
     * For example, 0 represents January, 1 represents February all the way until 11 represents December.
     * @param month The integer representation of the month.
     * @return The String representation of the month.
     */
    public String monthToString(int month) {
        if (month == 0) {
            return "January";
        } else if (month == 1) {
            return "February";
        } else if (month == 2) {
            return "March";
        } else if (month == 3) {
            return "April";
        } else if (month == 4) {
            return "May";
        } else if (month == 5) {
            return "June";
        } else if (month == 6) {
            return "July";
        } else if (month == 7) {
            return "August";
        } else if (month == 8) {
            return "September";
        } else if (month == 9) {
            return "October";
        } else if (month == 10) {
            return "November";
        } else {
            return "December";
        }
    }

    /**
     * Takes in an array of data which represents the day, month and year of the date.
     * Following which, checks if the day, month and year are valid.
     * @param dateData An array of data which contains the day, month and year of the date.
     * @throws InvalidDateInputException If the day, month or year is invalid.
     */
    private void checkDateData(String[] dateData) throws InvalidDateInputException {
        InvalidDateInputException error = new InvalidDateInputException("Please key in a valid date in the format:\n "
                + "dd/MM/yyyy");
        int day = Integer.parseInt(dateData[0]);
        int month = Integer.parseInt(dateData[1]);
        int year = Integer.parseInt(dateData[2]);

        if (year < 0 || month < 0 ||  month > 12 || day < 0 || day > 31) {
            throw error;
        } else if (day > 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                    || month == 10 || month == 12)) {
            throw error;
        } else if (month == 2 && ((day > 29 && year % 4 == 0) || day > 28)) {
            throw error;
        } else if (day > 30 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
            throw error;
        }
    }

    /**
     * Takes in a String which represents the hour and the minutes.
     * Following which, check if the data is valid or not.
     * @param time A String representation of the time in the format "hhmm".
     * @throws InvalidDateInputException If the hour or minutes is invalid.
     */
    private void checkTimeData(String time) throws InvalidDateInputException {
        InvalidDateInputException error = new InvalidDateInputException("Please key in a valid time in the format:\n "
                + "hhmm");
        int hours = Integer.parseInt(time.substring(0, 2));
        int mins = Integer.parseInt(time.substring(2));

        if (hours < 0  || hours > 23 || mins < 0 || mins > 60) {
            throw error;
        } else if (time.length() != 4) {
            throw error;
        }
    }

    /**
     * Checks if the Date and Time are valid in general.
     * This is done by splitting the Date and Time and checking them individually.
     * @throws InvalidDateInputException IF either the Date of Time is invalid.
     */
    private void checkDateTime() throws InvalidDateInputException {
        try {
            String[] dateTime = this.input.split(" ");
            String[] dateData = dateTime[0].split("/");
            checkDateData(dateData);
            checkTimeData(dateTime[1]);
        } catch (InvalidDateInputException error) {
            throw error;
        } catch (Exception e) {
            throw new InvalidDateInputException("Please key in the date in this format:\n dd/MM/yyyy hhmm");
        }
    }

    /**
     * Converts the time in "hhmm" format into a String.
     * For example: "1207" is converted to "12:07 pm".
     * @param hour The hour of the time.
     * @param minutes The minute of the time.
     * @param timeInString The String representation of the time in "hhmm" format to determine am or pm.
     * @return The String representation fot eh time in the format "hh:mm pm" or "hh:mm am".
     */
    private String timeToString(int hour, int minutes, String timeInString) {
        if (hour > 12) {
            return String.format("%02d:%02d pm", hour - 12, minutes);
        } else if (hour > 0 && hour < 12) {
            return String.format("%02d:%02d am", hour, minutes);
        } else if (timeInString.substring(0, 2).equals("12")) {
            return String.format("%02d:%02d pm", hour + 12, minutes);
        } else {
            return String.format("%02d:%02d am", hour + 12, minutes);
        }
    }

    /**
     * Converts the date from "dd/MM/yyyy" into the format "dd of mm yyyy".
     * @return The date in the format of "dd of mm yyyy".
     * @throws InvalidDateInputException If the date, month or year is invalid.
     */
    public String convertDateToString() throws InvalidDateInputException {

        checkDateTime();

        try {
            inputParser = formatter.parse(input);
            calendar.setTime(inputParser);
            int year = calendar.get(Calendar.YEAR);
            String month = monthToString(calendar.get(Calendar.MONTH));
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);
            return String.format("%02d of %s %d %s", day, month, year, timeToString(hour, minutes,
                    (input.split(" "))[1]));
        } catch (Exception e) {
            throw new InvalidDateInputException("Please key in the date in this format:\n dd/MM/yyyy hhmm");
        }
    }


}
