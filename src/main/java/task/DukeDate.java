package task;

import exceptions.InvalidInputException;
import exceptions.MissingInputException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DukeDate {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static SimpleDateFormat outputFormat = new SimpleDateFormat("EEE, dd MMM YYYY");

    private static String INCOMPLETE_DATE_MESSAGE = "Date is incomplete!\n Date should be in DD/MM/YYYY format.";

    private static String INVALID_DATE_MESSAGE = "Date is invalid! \n It should be in DD/MM/YYYY format.";

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
    public static DukeDate processDate(String dateString) throws InvalidInputException, MissingInputException {
        Date date;

        if (dateString.length() < 3) {
            throw new MissingInputException(INVALID_DATE_MESSAGE);
        }

        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new InvalidInputException(INCOMPLETE_DATE_MESSAGE);
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