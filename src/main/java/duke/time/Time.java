package duke.time;

import duke.exception.DukeException;

/**
 * Class that represents the time.
 */
public class Time {

    /**
     * The time in number form.
     */
    protected int time;

    /**
     * The time formatted correctly as output.
     */
    protected String outputTime;

    /**
     * To indicate whether the format of the time is valid.
     */
    protected boolean validFormat = true;

    /**
     * Constructor that takes in the time to format it correctly.
     * @param time The time in number form.
     * @throws DukeException Error when the input is wrong.
     */
    public Time(int time) throws DukeException {
        String timeString = String.valueOf(time);
        if (timeString.length() > 4) {
            validFormat = false;
        }
        String format = "";
        if (time >= 1200) {
            format = "pm";
        } else {
            format = "am";
        }
        int hours = time / 100;
        hours = hours % 12;
        int minutes = time % 100;
        if (minutes >= 60) {
            validFormat = false;
        }
        String hour = String.valueOf(hours);
        String minute = "";
        if (minutes < 10) {
            minute = "." + "0" + minutes;
        } else if (minutes != 0) {
            minute = "." + minutes;
        }
        outputTime = hour + minute + format;
    }

    /**
     * To check if the time format is valid.
     * @return True if it is valid, false otherwise.
     */
    public boolean isValid() {
        return validFormat;
    }

    @Override
    public String toString() {
        return outputTime;
    }
}
