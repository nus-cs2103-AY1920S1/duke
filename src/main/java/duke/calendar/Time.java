package duke.calendar;

import duke.exception.InvalidTimeException;

/**
 * Represents a time. A <code>Time</code> object corresponds to a specified hour and minutes.
 */
public class Time {
    protected String unprocessedTime;
    protected String processedTime;
    protected boolean isAfternoon = false;

    /**
     * Constructor for <code>Time</code>.
     * @param unprocessedTime User unprocessed time input.
     * @throws InvalidTimeException If cannot process time.
     */
    public Time(String unprocessedTime) throws InvalidTimeException {
        this.unprocessedTime = unprocessedTime;
        processTime();
    }

    /**
     * Processes the user input time.
     * If unprocessed time is empty, the time will not be processed.
     * @throws InvalidTimeException If time is in the wrong format or invalid.
     */
    public void processTime() throws InvalidTimeException {
        boolean isEmptyTime = unprocessedTime.equals("");
        if (isEmptyTime) {
            return;
        }
        boolean isIncorrectFormat = unprocessedTime.length() != 4;
        if (isIncorrectFormat) {
            throw new InvalidTimeException("☹ OOPS!!! Please input a valid time e.g. 1800.");
        }
        int time = Integer.parseInt(unprocessedTime);
        int hour = time / 100;
        int minute = time % 100;
        boolean isInvalidTime = hour > 23 || minute > 59;
        if (isInvalidTime) {
            throw new InvalidTimeException("☹ OOPS!!! Please input a valid time.");
        }
        boolean isPastNoon = hour > 11;
        if (isPastNoon) {
            hour = hour % 12;
            isAfternoon = true;
        }
        boolean isMidnight = hour == 0;
        if (isMidnight) {
            hour = 12;
        }
        this.processedTime = formatTime(hour, minute);
    }

    private String formatTime(int hour, int minute) {
        StringBuilder timing = new StringBuilder();
        timing.append(hour);
        boolean isNotExactHour = minute != 0;
        boolean isLessThanTenMinutes = minute < 10;
        if (isNotExactHour) {
            if (isLessThanTenMinutes) {
                timing.append(":" + "0" + minute);
            } else {
                timing.append(":" + minute);
            }
        }
        return timing.toString();
    }

    /**
     * Returns the unprocessed time.
     * @return Unprocessed time.
     */
    public String getUnprocessedTime() {
        return unprocessedTime;
    }

    @Override
    public String toString() {
        boolean isEmptyTime = processedTime == null;
        if (isEmptyTime) {
            return "";
        }
        if (isAfternoon) {
            return processedTime + "pm";
        } else {
            return processedTime + "am";
        }
    }
}
