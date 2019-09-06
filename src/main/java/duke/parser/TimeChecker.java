package duke.parser;

/**
 * Represent a Checker to check the validity of the time provided by the user.
 */
public class TimeChecker {
    private int hours;
    private int minutes;
    private String timeString;

    /**
     * Constructs a Time Checker which parses the hours and minutes ,
     * denoted by the string input.
     * @param timeData the time input represented as a String.
     */
    public TimeChecker(String timeData) {
        this.timeString = timeData;
        this.hours = Integer.parseInt(timeData.substring(0, 2));
        this.minutes = Integer.parseInt(timeData.substring(2));
    }

    /**
     * Main method to check if the time given is valid or not.
     * @return true if the time given is invalid.
     */
    public boolean containsInvalidTime() {
        return containsWrongFormat() || containsInvalidHour()
                || containsInvalidMinutes();
    }

    /**
     * Checks if the String provided is in the correct format or not.
     * @return true if the number of characters is less than 4.
     */
    private boolean containsWrongFormat() {
        return timeString.length() != 4;
    }

    /**
     * Checks if the hour provided is valid or not.
     * @return true if hour is less than 0 or greater than 24.
     */
    private boolean containsInvalidHour() {
        return hours < 0 || hours > 24;
    }

    /**
     * Checks if the minute provided is valid or not.
     * @return true if the minutes are less than 0 or greater than 24.
     */
    private boolean containsInvalidMinutes() {
        return minutes < 0 || minutes > 59;
    }

}
