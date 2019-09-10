package utils;

import exceptions.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {
    private Date date;

    /**
     * Create a new date time in the required format.
     * Also checks if the user entered a valid date.
     *
     * @param rawDate date time inputted by user
     * @throws ParseException in case date time is in invalid format
     */
    public StringToDate(String rawDate) throws ParseException, DukeException {

        boolean isAcceptableDate = checkDateValues(rawDate);

        if (isAcceptableDate) {
            String dateFormat = "dd-MM-yyyy HH:mm";
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            this.date = formatter.parse(rawDate);
        } else {
            throw new DukeException("Values entered for the date may be incorrect!");
        }
    }


    /**
     * Processes the raw date to obtain day,
     * month and year of date entered by user.
     * Checks if said date details are within an acceptable range.
     *
     * @return true if date is acceptable range, false otherwise
     */
    private boolean checkDateValues(String rawDate) {
        String[] dateAndTime = rawDate.split(" ");
        String[] dateValues = dateAndTime[0].split("-");

        // convert date, month and year to integer
        int[] dateDetails = new int[3];
        for (int i = 0; i < dateValues.length; i++) {
            dateDetails[i] = Integer.parseInt(dateValues[i]);
        }

        int day = dateDetails[0];
        int month = dateDetails[1];
        int year = dateDetails[2];

        return day <= 31 && month <= 12 && year <= 2050;
    }

    /**
     * Show user the date.
     *
     * @return string representation of the date and time
     */
    @Override
    public String toString() {
        String dateFormat = "dd-MM-yyyy HH:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(this.date);
    }
}
