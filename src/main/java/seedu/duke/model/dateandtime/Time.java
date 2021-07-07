package seedu.duke.model.dateandtime;

/**
 * Defines a Time object containing hour and minute as attributes.
 *
 */
public class Time implements Comparable<Time> {
    private int hour;
    private int minute;

    /**
     * Creates an instance of Time.
     * @param hour Hour component of the 24 hour clock.
     * @param minute Minute component of the 24 hour clock.
     */
    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public int compareTo(Time other) {
        if (this.hour < other.hour) {
            return -1;
        } else if (this.hour > other.hour) {
            return 1;
        } else if (this.minute < other.minute) {
            return -1;
        } else if (this.minute > other.minute) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Returns the String representation of a Time instance.
     * @return String representation of a Time instance in HHMM format.
     */
    @Override
    public String toString() {
        assert hour >= 0 && hour < 24 : "Hour of Time object is out of range.";
        assert hour >= 0 && hour <= 59 : "Minute of Time object is out of range.";
        return String.format("%02d", hour) + String.format("%02d", minute);
    }
}
