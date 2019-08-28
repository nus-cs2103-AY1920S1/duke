package duke.datetime;

/**
 * Represents a timing in the 24-hour clock format.
 */
public class Timing {
    private int hour;
    private int minute;
    private String timeString;
    private boolean meridiemFlag = false; //to check whether AM or PM

    /**
     * Class constructor the specifies the timing to be represented.
     *
     * @param timeString String representing timing, to be parsed.
     */
    public Timing(String timeString) {
        this.timeString = timeString;
        int timeInt = Integer.parseInt(timeString);
        this.minute = timeInt % 100;
        this.hour = timeInt / 100;

        if (this.hour >= 12) {
            meridiemFlag = true; //means it is PM not AM
        }
    }

    /**
     * Returns the initial string representing the timing before it was parsed.
     *
     * @return Initial string representation of the timing.
     */

    public String getTimeString() {
        return timeString;
    }

    /**
     * Returns string representation of this timing in 12-hour clock format.
     *
     * @return 12-hour clock representation of this timing
     */
    @Override
    public String toString() {
        String toReturn = (hour == 0 || hour == 12) ? "12" : hour % 12 + "";
        if (minute != 0) {
            toReturn = toReturn + "." + minute;
        }
        String meridiem = meridiemFlag ? "pm" : "am";
        return toReturn + meridiem;
    }
}
