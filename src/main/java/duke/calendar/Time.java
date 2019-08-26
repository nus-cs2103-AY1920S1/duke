package duke.calendar;

import duke.exception.DukeException;

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
     * @throws DukeException If cannot process time.
     */
    public Time(String unprocessedTime) throws DukeException {
        this.unprocessedTime = unprocessedTime;
        processTime();
    }

    /**
     * Processes the user input time.
     * If unprocessed time is empty, the time will not be processed.
     * @throws DukeException If time is in the wrong format or invalid.
     */
    public void processTime() throws DukeException {
        if (unprocessedTime.equals("")) {
            return;
        }
        if (unprocessedTime.length() == 4) {
            int time = Integer.parseInt(unprocessedTime);
            int hour = time / 100;
            int minute = time % 100;
            if (hour > 23 || minute > 59) {
                throw new DukeException("\u2639 OOPS!!! Please input a valid time.");
            }
            if (hour > 11) {
                hour = hour % 12;
                isAfternoon = true;
            }
            if (hour == 0) {
                hour = 12;
            }
            StringBuilder timing = new StringBuilder();
            timing.append(hour);
            if (minute != 0) {
                if (minute < 10) {
                    timing.append(":" + "0" + minute);
                } else {
                    timing.append(":" + minute);
                }
            }
            processedTime = timing.toString();
        } else {
            throw new DukeException("\u2639 OOPS!!! Please input a valid time e.g. 1800.");
        }
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
        if (processedTime == null) {
            return "";
        }
        if (isAfternoon) {
            return processedTime + "pm";
        } else {
            return processedTime + "am";
        }
    }
}
