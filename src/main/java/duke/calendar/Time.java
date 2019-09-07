package duke.calendar;

import duke.exception.InvalidTimeException;

/**
 * Represents a time. A <code>Time</code> object corresponds to a specific hour and minute.
 */
public class Time {
    protected String rawTime;
    protected boolean isPastNoon = false;
    protected int hour;
    protected int minutes;
    protected boolean isNull = true;

    /**
     * Constructor for <code>Time</code>.
     * @param rawTime Unprocessed time
     * @throws InvalidTimeException If provided time is invalid.
     */
    public Time(String rawTime) throws InvalidTimeException {
        this.rawTime = rawTime;
        if (rawTime != null) {
            isNull = false;
            processTime();
        }
    }

    /**
     * Extracts information about the hour and minute from the raw time.
     * @throws InvalidTimeException If time is in the wrong format or invalid.
     */
    protected void processTime() throws InvalidTimeException {
        if (isInvalidTime(rawTime)) {
            throw new InvalidTimeException(
                "☹ OOPS!!! Please specify a valid time. "
                    + "Also ensure that you have specified the time in 24-hour clock convention E.g. 1800 ");
        }
        assignHour();
        assignMinutes();
    }

    private void assignHour() {
        hour = Integer.parseInt(rawTime.substring(0, 2));
        if (hour > 11) {
            isPastNoon = true;
            hour = hour % 12;
        }
        if (hour == 0) {
            hour = 12;
        }
    }

    private void assignMinutes() {
        minutes = Integer.parseInt(rawTime.substring(2));
    }

    /**
     * Checks if provided time is invalid.
     * Both the hour and minute component of the time must be valid for the time to be valid.
     * @param rawTime Unprocessed time with 4 digits, 2 to represent the hour and the other 2 to represent the minute.
     * @return False if the provided time is in the correct format and 0 <= hour <= 23 and 0 <= minute <= 59.
     */
    protected boolean isInvalidTime(String rawTime) {
        if (rawTime.length() < 4) {
            return true;
        } else {
            int inputHour  = Integer.parseInt(rawTime.substring(0, 2));
            int inputMinutes = Integer.parseInt(rawTime.substring(2));
            return inputHour > 23 || inputMinutes > 59;
        }
    }

    /**
     * Checks if the provided raw time is null.
     * @return True if null was the input to the constructor.
     */
    public boolean isNull() {
        return isNull;
    }

    /**
     * Returns the provided raw time.
     * @return The raw time.
     */
    public String getRawTime() {
        return rawTime;
    }

    @Override
    public String toString() {
        if (minutes == 0) {
            return hour + (isPastNoon ? "pm" : "am");
        } else {
            return hour + ":" + (minutes < 10 ? "0" + minutes : minutes) + (isPastNoon ? "pm" : "am");
        }
    }
}