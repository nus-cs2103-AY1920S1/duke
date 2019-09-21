package utils;

import exceptions.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {
    private Date date;
    private String rawDate;
    private static final String ERROR_DATE_FORMAT = "Please enter the date in the format dd-MMM-yyyy HH:mm";
    private static final String ERROR_INVALID_DATES = "Values entered for the date may be incorrect!";

    /**
     * Create a new date time in the required format.
     * Also checks if the user entered a valid date.
     *
     * @param rawDate date time inputted by user
     * @throws DukeException in case date time is in invalid format
     */
    public StringToDate(String rawDate) throws DukeException {

        boolean isAcceptableDate = checkDateValues(rawDate);

        if (isAcceptableDate) {
            this.rawDate = rawDate;
            String dateFormat = "dd-MM-yyyy HH:mm";
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            try {
                this.date = formatter.parse(rawDate);
            } catch (ParseException e) {
                throw new DukeException(ERROR_DATE_FORMAT);
            }
        } else {
            throw new DukeException(ERROR_INVALID_DATES);
        }
    }

    public String getRawDate() {
        return this.rawDate;
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
