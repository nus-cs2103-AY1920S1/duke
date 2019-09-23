package task;

import exceptions.InvalidInputException;
import exceptions.MissingInputException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

public class DukeDate {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat();

    private static SimpleDateFormat outputFormat = new SimpleDateFormat("EEE, dd MMM YYYY");

    private static String INVALID_DATE_MESSAGE = "Input is invalid!\n Date should be in DD/MM/YYYY format.";

    Date date;

    /**
     * Constructs a DukeDate object.
     *
     * @param date {@link Date} object.
     */
    public DukeDate(Date date) {
        this.date = date;
    }

    /**
     * Calls constructor for a DukeDate object.
     *
     * @param dateString User input String.
     * @return DukeDate object.
     * @throws InvalidInputException when date is not valid.
     * @throws MissingInputException when date is not complete.
     */
    public static DukeDate processDate(String dateString) throws InvalidInputException {
        Date date;
        dateFormat.applyPattern("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException | DateTimeException e) {
            throw new InvalidInputException(INVALID_DATE_MESSAGE);
        }

        return new DukeDate(date);
    }

    /**
     * Formats DukeDate object.
     *
     * @return A string in the form Day, dd MMM YYYY format.
     */
    @Override
    public String toString() {
        return outputFormat.format(date);
    }

}