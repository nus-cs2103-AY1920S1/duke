package task;

import exceptions.InvalidInputException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DukeTime {

    public static final String TIME_FORMAT_MESSAGE = "Time should be given in HHMM format from 0000 to 2359";

    private static SimpleDateFormat inputFormat = new SimpleDateFormat();

    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mma");

    private Date time;

    /**
     * Constructs a new DukeTime object.
     *
     * @param time {@link Date} object.
     */
    public DukeTime(Date time) {
        this.time = time;
    }

    /**
     * Calls constructor for a DukeTime object.
     *
     * @param timeString User input String.
     * @return new {@DukeTime} object.
     * @throws InvalidInputException when time given is invalid.
     */
    public static DukeTime processTime(String timeString) throws InvalidInputException {
        Date time;
        inputFormat.applyPattern("HHmm");
        inputFormat.setLenient(false);
        if (timeString.length() <= 3) {
            throw new InvalidInputException(TIME_FORMAT_MESSAGE);
        }
        try {
            time = inputFormat.parse(timeString);
        } catch (ParseException e) {
            throw new InvalidInputException(TIME_FORMAT_MESSAGE);
        }
        return new DukeTime(time);

    }

    /**
     * Formats DukeTime object.
     *
     * @return A string in the form HH:MMa format.
     */
    @Override
    public String toString() {
        return outputFormat.format(time);
    }

}